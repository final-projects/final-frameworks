package org.ifinalframework.auto.spring.factory.annotation;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark the type is a spring configuration element.
 *
 * <p>{@link SpringAutoConfiguration}将标记了该注解的元素写到 {@code META-INF/spring.factories}文件中 {@code key} 为 {@link
 * EnableAutoConfiguration} 下，为{@code spring} 的内置组件功能。</p>
 *
 * @author likly
 * @version 1.0.0
 * @since 1.0.0
 */
@Target({ElementType.TYPE, ElementType.PACKAGE})
@Retention(RetentionPolicy.SOURCE)
@SpringFactory(EnableAutoConfiguration.class)
public @interface SpringAutoConfiguration {

}
