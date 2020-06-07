# thallo
脚手架项目

# 依赖服务(需要自行安装配置)
| 名称 | 作用 | 官网 | 下载地址 |
|-- |-- |-- |-- |
| Nacos | 服务、配置中心  | https://nacos.io/zh-cn |  https://github.com/alibaba/nacos/releases/download/1.2.1/nacos-server-1.2.1.tar.gz |
| Zookeeper | 任务调度注册中心 |  https://zookeeper.apache.org | https://www.apache.org/dyn/closer.lua/zookeeper/zookeeper-3.6.1/apache-zookeeper-3.6.1-bin.tar.gz |
| Redis | 缓存 | https://redis.io/ | http://download.redis.io/releases/redis-6.0.4.tar.gz |
| rabbitmq | 队列服务 | https://www.rabbitmq.com/ | https://github.com/rabbitmq/rabbitmq-server/releases/download/v3.8.4/rabbitmq-server-generic-unix-3.8.4.tar.xz |
| MySQL | 数据持久化 | https://www.mysql.com/ | |


# 项目结构

* common  基础抽象和共用代码
* gateway 网关服务
* authnz  认证首选服务
* transMgr 事务管理服务器
* 

# 使用的开源项目
* https://cloud.spring.io/spring-cloud-static/spring-cloud-config/2.0.2.RELEASE/single/spring-cloud-config.html
* [Spring Framework](https://docs.spring.io/spring/docs/5.0.12.BUILD-SNAPSHOT/spring-framework-reference/)
* [Spring Cloud Security](https://cloud.spring.io/spring-cloud-static/spring-cloud-security/2.0.1.RELEASE/single/spring-cloud-security.html)
* [Spring Boot](https://docs.spring.io/spring-boot/docs/2.0.7.RELEASE/reference/htmlsingle)
* [Spring Security](https://docs.spring.io/spring-security/site/docs/5.0.10.RELEASE/reference/htmlsingle/)
