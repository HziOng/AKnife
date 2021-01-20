package org.aknife.resource.annotation;

import java.lang.annotation.*;

/**
 * @ClassName ExcelCell
 * @Author HeZiLong
 * @Data 2021/1/20 15:00
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelCell {
    int col();
    Class<?> Type() default String.class;
}