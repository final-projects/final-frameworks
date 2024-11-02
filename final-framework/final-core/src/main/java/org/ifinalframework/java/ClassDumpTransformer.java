/*
 * Copyright 2020-2021 the original author or authors.
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

package org.ifinalframework.java;

import org.springframework.lang.Nullable;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author iimik
 * @version 1.0.0
 * @since 1.0.0
 */
public class ClassDumpTransformer implements ClassFileTransformer {


    private final Set<Class<?>> classes;

    private final Map<Class<?>, byte[]> dumpResult;

    public ClassDumpTransformer(Set<Class<?>> classes) {
        this.classes = classes;
        this.dumpResult = new LinkedHashMap<>();
    }

    @Override
    @Nullable
    public byte[] transform(final ClassLoader loader, final String className, final Class<?> classBeingRedefined,
                            final ProtectionDomain protectionDomain, final byte[] classfileBuffer) {

        if (classes.contains(classBeingRedefined)) {
            dumpResult.put(classBeingRedefined, classfileBuffer);
        }
        return null;
    }

    public Map<Class<?>, byte[]> getDumpResult() {
        return dumpResult;
    }
}
