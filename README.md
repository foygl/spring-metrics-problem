# Issue: Failure auto-configuring metrics for StatsD

There is an issue introduced in Spring Boot 1.4.0.RELEASE which means you get a failure when auto-configuring metrics for StatsD. Downgrading to Spring Boot 1.3.7.RELEASE makes this problem go away.

If we switch to creating our own MetricWriter the problem goes away.

## Running example

`./gradlew clean bootRun`

Expected: Should run, log a message and exit.

Actual: Fails with the following exception:

```
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'org.springframework.boot.actuate.autoconfigure.MetricExportAutoConfiguration': Bean instantiation via constructor failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.boot.actuate.autoconfigure.MetricExportAutoConfiguration$$EnhancerBySpringCGLIB$$59d137ad]: Constructor threw exception; nested exception is org.springframework.beans.factory.BeanCurrentlyInCreationException: Error creating bean with name 'org.springframework.boot.actuate.autoconfigure.MetricExportAutoConfiguration': Requested bean is currently in creation: Is there an unresolvable circular reference?
        at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:279) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1143) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1046) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:510) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:482) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:306) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframeans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:230) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:302) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:197) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:776) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:861) ~[spring-context-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:541) ~[spring-context-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:759) [spring-boot-1.4.0.RELEASE.jar:1.4.0.RELEASE]
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:369) [spring-boot-1.4.0.RELEASE.jar:1.4.0.RELEASE]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:313) [spring-boot-1.4.0.RELEASE.jar:1.4.0.RELEASE]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1185) [spring-boot-1.4.0.RELEASE.jar:1.4.0.RELEASE]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1174) [spring-boot-1.4.0.RELEASE.jar:1.4.0.RELEASE]
        at com.example.metricsfail.Application.main(Application.java:13) [main/:na]
Caused by: org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.boot.actuate.autoconfigure.MetricExportAutoConfiguration$$EnhancerBySpringCGLIB$$59d137ad]: Constructor threw exception; nested exception is org.springframework.beans.factory.BeanCurrentlyInCreationException: Error creating bean with n                                ame 'org.springframework.boot.actuate.autoconfigure.MetricExportAutoConfiguration': Requested bean is currently in creation: Is there an unresolvable circular reference?
        at org.springframework.beans.BeanUtils.instantiateClass(BeanUtils.java:154) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:122) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:271) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        ... 17 common frames omitted
Caused by: org.springframework.beans.factory.BeanCurrentlyInCreationException: Error creating bean with name 'org.springframework.boot.actuate.autoconfigure.MetricExportAutoConfiguration': Requested bean is currently in creation: Is there an unresolvable circular reference?
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.beforeSingletonCreation(DefaultSingletonBeanRegistry.java:347) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:223) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:302) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:197) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:372) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1123) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.AbstractAutowbleBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1018) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:510) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:482) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:306) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:230) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:302) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:207) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.findAutowireCandidates(DefaultListableBeanFactory.java:1229) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveMultipleBeans(DefaultListableBeanFactory.java:1145) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1049) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory$DependencyObjectProvider.getIfAvailable(DefaultListableBeanFactory.java:1576) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        apringframework.boot.actuate.autoconfigure.MetricExportAutoConfiguration.<init>(MetricExportAutoConfiguration.java:76) ~[spring-boot-actuator-1.4.0.RELEASE.jar:1.4.0.RELEASE]
        at org.springframework.boot.actuate.autoconfigure.MetricExportAutoConfiguration$$EnhancerBySpringCGLIB$$59d137ad.<init>(<generated>) ~[spring-boot-actuator-1.4.0.RELEASE.jar:1.4.0.RELEASE]
        at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) ~[na:1.8.0_66]
        at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62) ~[na:1.8.0_66]
        at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) ~[na:1.8.0_66]
        at java.lang.reflect.Constructor.newInstance(Constructor.java:422) ~[na:1.8.0_66]
        at org.springframework.beans.BeanUtils.instantiateClass(BeanUtils.java:142) ~[spring-beans-4.3.2.RELEASE.jar:4.3.2.RELEASE]
        ... 19 common frames omitted
```
