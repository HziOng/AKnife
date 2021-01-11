package org.aknife.util.annotation;

import java.lang.annotation.*;

/**
 * 放置在业务类中的方法上,表示该操作对应的协议类型
 * @ClassName Operating
 * @Author HeZiLong
 * @Data 2021/1/11 15:49
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Operating {
    int value();
}
