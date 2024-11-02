/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ifinalframework.data.mapping;

import org.springframework.core.Ordered;
import org.springframework.data.mapping.PersistentProperty;

import org.ifinalframework.core.lang.Default;
import org.ifinalframework.core.lang.Final;
import org.ifinalframework.core.lang.Transient;
import org.ifinalframework.data.annotation.Column;
import org.ifinalframework.data.annotation.Keyword;
import org.ifinalframework.data.annotation.ReadOnly;
import org.ifinalframework.data.annotation.Reference;
import org.ifinalframework.data.annotation.Virtual;
import org.ifinalframework.data.annotation.WriteOnly;

import jakarta.validation.constraints.NotNull;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

/**
 * @author iimik
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Property extends PersistentProperty<Property>, Ordered {

    /**
     * return {@code true} if the {@linkplain #getType()} is a {@linkplain Enum enum}, otherwise {@code true}.
     *
     * @return {@code true} if the {@linkplain #getType()} is a {@linkplain Enum enum}, otherwise {@code true}.
     */
    default boolean isEnum() {
        return getType().isEnum();
    }

    /**
     * return the column name of property mapped
     *
     * @return column name
     * @see Column#name()
     * @see Column#value()
     */
    String getColumn();

    /**
     * return the column writer
     *
     * @return the column writer
     * @see Column#insert()
     */
    String getInsert();

    /**
     * return the column update sql fragment
     *
     * @return this column update sql fragment
     * @see Column#update()
     */
    String getUpdate();

    /**
     * return the column reader
     *
     * @return column reader
     * @see Column#select()
     */
    String getReader();

    /**
     * return {@code true} if this property annotated by {@link Default}.
     *
     * @return true if don't have {@link Transient} annotation and have {@link Default} annotation.
     * @see Default
     */
    boolean isDefault();

    /**
     * return {@code true} if this property annotated by {@link Final}.
     *
     * @return {@code true} if the {@link Property} annotated by {@link Final} annotation.
     * @see Final
     */
    boolean isFinal();

    /**
     * return {@code true} if this property annotated by {@link Reference}.
     *
     * @return {@code true} if this property annotated by {@link Reference}.
     * @see #isAssociation()
     */
    boolean isReference();

    /**
     * return {@code true} if this property is annotated by {@link Virtual}.
     *
     * @return {@code true} if the {@link Property} annotated by {@link Virtual} annotation.
     * @see Virtual
     */
    boolean isVirtual();

    /**
     * return {@code true} if this property is annotated by {@link ReadOnly}.
     *
     * @return {@code true} if this {@link Property} is annotated by {@link ReadOnly} annotation.
     * @see ReadOnly
     */
    boolean isReadOnly();

    /**
     * return {@code true} if this property is annotated by {@link WriteOnly}.
     *
     * @return {@code true} if this {@link Property} is annotated by {@link WriteOnly} annotation.
     * @see WriteOnly
     */
    boolean isWriteOnly();

    /**
     * return {@code true} if this property is a sql keyword.
     *
     * @return {@code true} if this property is a sql keyword.
     * @see Keyword
     */
    boolean isKeyword();

    default boolean isWriteable() {
        return !isTransient() && !isVirtual() && !isReadOnly() && !isDefault();
    }

    default boolean isModifiable() {
        return !isTransient() && !isVirtual() && !isReadOnly() && !isFinal();
    }

    Set<String> getReferenceProperties();

    String getReferenceColumn(Property property);

    /**
     * return java type of this property.
     * <ul>
     *     <li>return {@link Map} if the property is {@linkplain #isMap() map};</li>
     *     <li>return {@link #getComponentType()} if this property is {@linkplain #isCollectionLike() collection};</li>
     *     <li>otherwise return {@link #getType()}.</li>
     * </ul>
     *
     * @return the property java type
     */
    default Class<?> getJavaType() {
        if (isMap()) {
            return Map.class;
        } else if (isCollectionLike()) {
            return getComponentType();
        } else {
            return getType();
        }
    }

    Type getGenericType();

    default Object get(@NotNull Object target) {

        try {
            return getRequiredGetter().invoke(target);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    default void set(@NotNull Object target, Object value) {

        try {
            getRequiredSetter().invoke(target, value);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

}
