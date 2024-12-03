package org.ifinalframework.auto.service.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.ifinalframework.auto.service.annotation.AutoService.AutoServices;

/**
 * 在{@code META-INF}/{@link #path()}目录下生成名为{@link #value()}的文件，用于{@link java.util.ServiceLoader}加载， 内容格式下如下.
 * <ul>
 *     <li>JDK标准格式：{@linkplain Class#getCanonicalName() 目标服务全类名}</li>
 *     <li>命名扩展格式：{@linkplain #name() 服务名}={@linkplain Class#getCanonicalName() 目标服务全类名} </li>
 * </ul>
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
@Repeatable(AutoServices.class)
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface AutoService {

    /**
     * return auto service target class.
     *
     * @return auto service target class.
     */
    Class<?> value();

    /**
     * return the service name like dubbo spi.
     *
     * @return the service name.
     */
    String name() default "";

    /**
     * return service file path, default is {@literal services}.
     *
     * @return service file path.
     */
    String path() default "services";

    /**
     * return {@code true} if don't need be processed, such as a annotation like {@link AutoProcessor}.
     *
     * @return whether need to processed.
     */
    boolean ignore() default false;

    /**
     * AutoServices.
     */
    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.CLASS)
    @interface AutoServices {

        AutoService[] value();

    }

}
