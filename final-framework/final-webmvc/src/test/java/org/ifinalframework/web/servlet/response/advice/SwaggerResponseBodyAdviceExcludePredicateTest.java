/*
 * Copyright 2020-2024 the original author or authors.
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

package org.ifinalframework.web.servlet.response.advice;

import org.springframework.http.server.ServerHttpRequest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * SwaggerResponseBodyAdviceExcludePredicateTest
 *
 * @author iimik
 * @since 1.6.0
 **/
@ExtendWith(MockitoExtension.class)
class SwaggerResponseBodyAdviceExcludePredicateTest {

    @Mock
    private ServerHttpRequest serverHttpRequest;
    @Test
    void test1() throws URISyntaxException {

        Mockito.when(serverHttpRequest.getURI()).thenReturn(new URI("/swagger/12"));

        SwaggerResponseBodyAdviceExcludePredicate predicate = new SwaggerResponseBodyAdviceExcludePredicate();
        Assertions.assertTrue(predicate.exclude(null,null,null,null,serverHttpRequest,null));

    }
}