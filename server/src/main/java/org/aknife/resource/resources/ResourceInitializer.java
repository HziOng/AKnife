package org.aknife.resource.resources;

import lombok.extern.java.Log;
import org.aknife.business.map.annotation.InjectResource;
import org.aknife.business.map.entity.GameMapResource;
import org.aknife.connection.annotation.Operating;
import org.aknife.constant.PacketFixedConsts;
import org.aknife.resource.util.ExcelUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 资源初始化
 * @ClassName ResourceIninializer
 * @Author HeZiLong
 * @Data 2021/1/26 10:47
 */
@Log
public class ResourceInitializer {

    private static final String BASE_PACKAGE = "org.aknife";

    private static final String RESOURCE_PATTERN = "/**/*.class";

    public static void initResource(ApplicationContext ioc){

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
                //判断是否有注解
                Component classAnnotation = clazz.getAnnotation(Component.class);
                if (classAnnotation != null) {
                    Field[] hideFields = clazz.getFields();
                    for (Field field : hideFields){
                        InjectResource fieldAnnotation = field.getAnnotation(InjectResource.class);
                        if(fieldAnnotation != null){
                            String path = fieldAnnotation.path();
                            field.setAccessible(true);
                            if (List.class.isAssignableFrom(field.getType())){
                                Type t = field.getGenericType();
                                if (t instanceof ParameterizedType) {
                                    ParameterizedType pt = (ParameterizedType) t;
                                    Class keyClass = (Class) pt.getActualTypeArguments()[0];
                                    Class valueClass = (Class) pt.getActualTypeArguments()[1];
                                    field.set(ioc.getBean(clazz), ExcelUtil.getBeanMappingID(path, valueClass));
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
