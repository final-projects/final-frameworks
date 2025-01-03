# final-framework

![CI](https://github.com/final-projects/final-framework/actions/workflows/ci.yml/badge.svg)
[![codecov](https://codecov.io/github/ifinal-io/final-frameworks/graph/badge.svg?token=31vxLbJ33g)](https://codecov.io/github/ifinal-io/final-frameworks)
[![GitHub](https://img.shields.io/github/license/final-projects/final-framework)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Maven Central](https://img.shields.io/maven-central/v/org.ifinalframework/final-framework?label=maven&color=success)](https://mvnrepository.com/search?q=org.ifinal.finalframework)
![Sonatype Nexus (Releases)](https://img.shields.io/nexus/r/org.ifinalframework/final-framework?server=https://s01.oss.sonatype.org)
![Sonatype Nexus (Snapshot)](https://img.shields.io/nexus/s/org.ifinalframework/final-framework?server=https://s01.oss.sonatype.org)
![GitHub Repo stars](https://img.shields.io/github/stars/final-projects/final-framework)
![GitHub top language](https://img.shields.io/github/languages/top/final-projects/final-framework)
[![GitHub language count](https://img.shields.io/github/languages/count/final-projects/final-framework)](https://github.com/likly/final-framework)

![final-framework](src/images/final-framework@2x.png)

## 概述

`final-framework`致力于提供简单、灵活且功能强大的`java`开发脚手架。

## 模块

* `final-annotation`: 定义核心注解。
* `final-framework`: 基础框架
* `final-data`: 数据操作
  * `final-data-mybatis`: 通用mybatis
* `final-boot`: 快速集成starters
* `final-auto`: 注解处理器

## 依赖

| 组件            | 当前版本     | 最新版本                                                                                                                                                                                                   |
|---------------|----------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `spring-boot` | `3.4.1`  | [![Maven Central](https://img.shields.io/maven-central/v/org.springframework.boot/spring-boot-starter-parent?label=maven&color=success)](https://mvnrepository.com/search?q=org.springframework.boot) |
| `mybatis` | `3.5.19` | [![Maven Central](https://img.shields.io/maven-central/v/org.mybatis/mybatis?label=maven&color=success)](https://mvnrepository.com/search?q=org.mybatis) |


## 特性

### 资源导入

默认导入以下路径的资源：

* classpath:spring-config-*.xml
* classpath*:config/spring-config-*.xml
* classpath*:spring/spring-config-*.xml

可以使用`spring.application.import-resource.use-default`来取消默认资源的导入；
可以使用`spring.application.import-resource.locations`来指定自定义的导入资源。

## 致谢

* 感谢<a href="https://www.jetbrains.com/">jetbrains</a>提供的免费授权。
