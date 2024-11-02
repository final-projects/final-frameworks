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

package org.ifinalframework.boot.autoconfigure.app;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * ApplicationProperties.
 *
 * @author iimik
 * @version 1.2.1
 * @since 1.2.1
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "final.application")
public class ApplicationProperties {

    /**
     * 应用名称
     */
    private String name;

    /**
     * 项目编码
     */
    private String project;

    /**
     * 应用编码
     */
    private String code;

    /**
     * 服务ID
     */
    private String service;

}
