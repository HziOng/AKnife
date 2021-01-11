package org.aknife.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.aknife.message.codec.GameMessageDecoder;
import org.aknife.message.codec.GameMessageEncoder;
import org.aknife.util.annotation.Operating;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName GameServerInitializer
 * @Author HeZiLong
 * @Data 2021/1/11 10:24
 */
public class GameServerInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * 要扫描的包名
     */
    private final String BASE_PACKAGE = "org.aknife";
    private final String RESOURCE_PATTERN = "/**/*.class";

    /**
     * 存储协议类型和调用的service方法的映射关系
     */
    private ConcurrentHashMap<Integer, Method> protocolMap = new ConcurrentHashMap();

    private ConcurrentHashMap<Integer, Method> classMap = new ConcurrentHashMap();

    /**
     * Spring中的IOC容器
     */
    private ApplicationContext ioc = null;


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        initSpring();
        initProtocol();
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast("decoder", new GameMessageDecoder());
        pipeline.addLast("encoder", new GameMessageEncoder());
        pipeline.addLast("controlHandler", new GameServerControlHandler(protocolMap, ioc));

    }

    /**
     * 初始化Spring容器
     */
    private void initSpring(){
        try {
            ioc = new ClassPathXmlApplicationContext("spring.xml");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 初始化协议code-协议类型
     */
    public void initClass(){

    }


    /**
     * 初始化协议内容
     * @throws Exception
     */
    private void initProtocol() throws Exception {

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
                Service classAnnotation = clazz.getAnnotation(Service.class);
                if (classAnnotation != null) {
                    Method[] hideMethods = clazz.getMethods();
                    System.out.println(clazz.getName());
                    for (Method method : hideMethods){
                        Operating methodAnnotation = method.getAnnotation(Operating.class);
                        if(methodAnnotation != null){
                            protocolMap.put(methodAnnotation.value(), method);
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
