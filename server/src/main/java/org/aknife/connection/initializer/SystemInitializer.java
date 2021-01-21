package org.aknife.connection.initializer;

import lombok.extern.java.Log;
import org.aknife.constant.PacketFixedConsts;
import org.aknife.connection.annotation.Operating;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName SystemInitializer
 * @Author HeZiLong
 * @Data 2021/1/12 15:30
 */
@Log
public class SystemInitializer {

    // 存储协议类型和调用的service方法的映射关系
    /**
     * 协议中独自运行的方法
     */
    private ConcurrentHashMap<Integer, Method> protocolUniqueMap = new ConcurrentHashMap();
    /**
     * 协议中需要跨用户的方法
     */
    private ConcurrentHashMap<Integer, Method> protocolCommonMap = new ConcurrentHashMap();


    /**
     * Spring中的IOC容器
     */
    private ApplicationContext ioc = null;

    /**
     * 要扫描的包名
     */
    private final String BASE_PACKAGE = "org.aknife";
    private final String RESOURCE_PATTERN = "/**/*.class";
    private final String APPLICATIONCONTEXTPATH = "applicationContext.xml";

    public SystemInitializer() {
        initSpring();
        initProtocol();
    }

    /**
     * 初始化Spring容器
     */
    private void initSpring(){
        try {
            ioc = new ClassPathXmlApplicationContext(APPLICATIONCONTEXTPATH);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    /**
     * 初始化协议内容
     * @throws Exception
     */
    private void initProtocol() {

        //spring工具类，可以获取指定路径下的全部类
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        try {
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    ClassUtils.convertClassNameToResourcePath(BASE_PACKAGE) + RESOURCE_PATTERN;
            Resource[] resources = resourcePatternResolver.getResources(pattern);
            //MetadataReader 的工厂类
            MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            for (Resource resource : resources) {
                //用于读取类信息
                MetadataReader reader = readerFactory.getMetadataReader(resource);
                //扫描到的class
                String classname = reader.getClassMetadata().getClassName();
                Class<?> clazz = Class.forName(classname);
                //判断是否有指定主解
                Controller classAnnotation = clazz.getAnnotation(Controller.class);
                if (classAnnotation != null) {
                    Method[] hideMethods = clazz.getMethods();
                    for (Method method : hideMethods){
                        Operating methodAnnotation = method.getAnnotation(Operating.class);
                        if(methodAnnotation != null){
                            Class[] paramClass = method.getParameterTypes();
                            if(paramClass.length <= 1){
                                log.info("运行方法参数不符合规范");
                                continue;
                            }
                            int packetCode = PacketFixedConsts.getCodeByClass(paramClass[1]);
                            if ("unique".equals(methodAnnotation.type())){
                                protocolUniqueMap.put(packetCode, method);
                            }else {
                                protocolCommonMap.put(packetCode, method);
                            }
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public ConcurrentHashMap<Integer, Method> getProtocolUniqueMap() {
        return protocolUniqueMap;
    }

    public ConcurrentHashMap<Integer, Method> getProtocolCommonMap() {
        return protocolCommonMap;
    }

    public ApplicationContext getIoc() {
        return ioc;
    }

}
