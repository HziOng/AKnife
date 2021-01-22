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
    private static ConcurrentHashMap<Integer, Method> protocolMap = new ConcurrentHashMap();

    /**
     * Spring中的IOC容器
     */
    private static ApplicationContext ioc = null;

    /**
     * 要扫描的包名
     */
    private static final String BASE_PACKAGE = "org.aknife";
    private static final String RESOURCE_PATTERN = "/**/*.class";
    private static final String APPLICATION_CONTEXT_PATH = "applicationContext.xml";

    static  {
        initSpring();
        initProtocol();
    }

    /**
     * 初始化Spring容器
     */
    private static void initSpring(){
        try {
            ioc = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_PATH);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    /**
     * 初始化协议内容
     * @throws Exception
     */
    private static void initProtocol() {

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
                            protocolMap.put(packetCode, method);
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public static ConcurrentHashMap<Integer, Method> getProtocolMap() {
        return protocolMap;
    }

    public static ApplicationContext getIoc() {
        return ioc;
    }

}
