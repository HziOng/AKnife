package org.aknife.connection.processor;

import org.aknife.business.map.annotation.InjectResource;
import org.aknife.resource.util.ExcelUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @ClassName PostProcessor
 * @Author HeZiLong
 * @Data 2021/1/26 17:43
 */
public class ResourcePostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        try {
            Field[] hideFields = bean.getClass().getDeclaredFields();
            for (Field field : hideFields){
                field.setAccessible(true);
                if(field.isAnnotationPresent(InjectResource.class)){
                    String path = field.getAnnotation(InjectResource.class).path();
                    if (Map.class.isAssignableFrom(field.getType())){
                        Type t = field.getGenericType();
                        if (t instanceof ParameterizedType) {
                            ParameterizedType pt = (ParameterizedType) t;
                            Class keyClass = (Class) pt.getActualTypeArguments()[0];
                            Class valueClass = (Class) pt.getActualTypeArguments()[1];
                            field.set(bean, ExcelUtil.getBeanMappingID(path, valueClass));
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
