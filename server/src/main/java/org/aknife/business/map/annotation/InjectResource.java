package org.aknife.business.map.annotation;

import java.lang.annotation.*;

/**
 * 用于注入资源
 * @ClassName MyResource
 * @Author HeZiLong
 * @Data 2021/1/26 10:42
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface InjectResource {

    String path();
}
