# Spring Boot Issue 15178

## Link:
https://github.com/spring-projects/spring-boot/issues/15178

## Description:
If you hit "Run" then the following exception occures:
```
org.springframework.beans.factory.BeanDefinitionStoreException: Failed to read candidate component class: file [D:\dev\oss\okb\target\classes\de\thd\okb\component\MailHelper.class]; nested exception is java.lang.ArrayIndexOutOfBoundsException: 257
	at org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider.scanCandidateComponents(ClassPathScanningCandidateComponentProvider.java:454) ~[spring-context-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider.findCandidateComponents(ClassPathScanningCandidateComponentProvider.java:316) ~[spring-context-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.context.annotation.ClassPathBeanDefinitionScanner.doScan(ClassPathBeanDefinitionScanner.java:275) ~[spring-context-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.context.annotation.ComponentScanAnnotationParser.parse(ComponentScanAnnotationParser.java:132) ~[spring-context-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.context.annotation.ConfigurationClassParser.doProcessConfigurationClass(ConfigurationClassParser.java:287) ~[spring-context-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.context.annotation.ConfigurationClassParser.processConfigurationClass(ConfigurationClassParser.java:242) ~[spring-context-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.context.annotation.ConfigurationClassParser.parse(ConfigurationClassParser.java:199) ~[spring-context-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.context.annotation.ConfigurationClassParser.parse(ConfigurationClassParser.java:167) ~[spring-context-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.context.annotation.ConfigurationClassPostProcessor.processConfigBeanDefinitions(ConfigurationClassPostProcessor.java:315) ~[spring-context-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.context.annotation.ConfigurationClassPostProcessor.postProcessBeanDefinitionRegistry(ConfigurationClassPostProcessor.java:232) ~[spring-context-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanDefinitionRegistryPostProcessors(PostProcessorRegistrationDelegate.java:275) ~[spring-context-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(PostProcessorRegistrationDelegate.java:95) ~[spring-context-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.invokeBeanFactoryPostProcessors(AbstractApplicationContext.java:691) ~[spring-context-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:528) ~[spring-context-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:140) ~[spring-boot-2.1.0.RELEASE.jar:2.1.0.RELEASE]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:775) [spring-boot-2.1.0.RELEASE.jar:2.1.0.RELEASE]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:397) [spring-boot-2.1.0.RELEASE.jar:2.1.0.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:316) [spring-boot-2.1.0.RELEASE.jar:2.1.0.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1260) [spring-boot-2.1.0.RELEASE.jar:2.1.0.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1248) [spring-boot-2.1.0.RELEASE.jar:2.1.0.RELEASE]
	at de.thd.okb.OkbApplication.main(OkbApplication.java:38) [classes/:na]
Caused by: java.lang.ArrayIndexOutOfBoundsException: 257
	at org.springframework.asm.ClassReader.readLabel(ClassReader.java:2440) ~[spring-core-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.asm.ClassReader.createLabel(ClassReader.java:2456) ~[spring-core-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.asm.ClassReader.readTypeAnnotations(ClassReader.java:2522) ~[spring-core-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.asm.ClassReader.readCode(ClassReader.java:1686) ~[spring-core-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.asm.ClassReader.readMethod(ClassReader.java:1275) ~[spring-core-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.asm.ClassReader.accept(ClassReader.java:679) ~[spring-core-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.asm.ClassReader.accept(ClassReader.java:391) ~[spring-core-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.core.type.classreading.SimpleMetadataReader.<init>(SimpleMetadataReader.java:65) ~[spring-core-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.core.type.classreading.SimpleMetadataReaderFactory.getMetadataReader(SimpleMetadataReaderFactory.java:103) ~[spring-core-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.core.type.classreading.CachingMetadataReaderFactory.getMetadataReader(CachingMetadataReaderFactory.java:123) ~[spring-core-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	at org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider.scanCandidateComponents(ClassPathScanningCandidateComponentProvider.java:430) ~[spring-context-5.1.2.RELEASE.jar:5.1.2.RELEASE]
	... 20 common frames omitted
```

## Contact:
Thomas Lang   
thomas.lang@th-deg.de

## Please note:
* the database is not implemented
* thymeleaf templates are empty
* no tests are provided
* if you start this app, the exception is immediately thrown
* if you downgrade to spring boot 2.0.6, everything works
* if you comment the `forEach` Lambda Parts in the `MailHelper` component class everything works
