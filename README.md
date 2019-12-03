
**基于SpingCloud全家桶搭建的分布式脚手架**
- 前后端分离
- 现只提供后端代码，希望有人愿意一起维护一个前端ui
 
###  技术选型

| 技术                   |   说明                   | 
| :---:                 |   :---:                  | 
| Spring Cloud Greenwich.SR1          |   高性能分布式微服务        |     
| Spring Boot  2.1.4.RELEASE         |   容器+MVC框架            |     
| Spring Security       |   认证和授权框架           |
| Spring Cloud stream      |   消息中间件将使用rabbitmq           |
| Swagger-UI            |   文档生产工具             |
| RabbitMq              |   消息队列                |
| Druid                 |   sql监控                |
| Redis                 |   分布式缓存              |
| mongodb                 |   分布式缓存              |
| quartz                |   定时任务                | e
| MyBatis               |   ORM框架,多数据源,读写分离 |
| MyBatis-plus 3.0          |   MyBatis 的增强工具      |
| Maven                 |   项目管理                |
| Logback               |   记录日志                |
| Lombok                |   简化对象封装工具          |
---

**项目结构简介**
- belong-common -----------------------------------公共依赖包
   - belong-common-auth ----------------------------security鉴权包
   - belong-common-core ---------------------------系统常量字典等
   - belong-common-database --------------------------数据源
   - belong-common-dozer ---------------------------对象属性转换工具
   - belong-common-exception ----------------------------全局异常管理
   - belong-common-log -----------------------------日志记录工厂
   - belong-common-mongodb --------------------------mongodb工具类
   - belong-common-mybatisplus ----------------------mybatisplus增强工具
   - belong-common-redis ------------------------redis工具类
   - belong-common-stream ----------------------------消息中间件，用于服务之间消息交流
   - belong-common-swagger ------------------------通过注解形式开启Swagger文档
   - belong-common-util ---------------------------系统util工具包
- belong-config----------------------------------配置中心 端口:[520]
- belong-eureka----------------------------------注册中心 端口:[13141，13142，13142]
- belong-geteway-----------------------------------应用网关 端口:[9527]
- belong-service -----------------------------------服务提供者管理
    - belong-service-gen ---------------------------代码生成服务
    - belong-service-management ---------------------------后端管理接口服务提供
    - belong-service-mobile ---------------------------手机端接口服务提供
    - belong-service-wechat ---------------------------微信端接口服务提供
        - belong-service-wechat-account ---------------------------微信端公众号接口服务提供
        - belong-service-wechat-applet ---------------------------微信端小程序接口服务提供
            - belong-service-wechat-applet-info ---------------------------微信端小程序个人中心接口服务提供
        - belong-service-wechat-base ---------------------------公共模块
- belong-service-api--------------------------------------远程调用接口提供
    - belong-service-management-api ---------------------------后端管理远程接口服务提供
    - belong-service-mobile-api ---------------------------手机端远程接口服务提供
    - belong-service-wechat-api ---------------------------微信端远程接口服务提供
    

- 启动顺序 
    - belong-eureka     注册中心
    - belong-config        配置中心
    - belong-service       服务
    - belong-geteway      网关

###  host配置
- 127.0.0.1 	belongMysqlMaster.com
- 127.0.0.1 	belongMysqlSlave.com
- 127.0.0.1 	belongRedis.com
- 127.0.0.1 	belongEureka13141.com
- 127.0.0.1 	belongEureka13142.com
- 127.0.0.1 	belongEureka13143.com
- 127.0.0.1 	belongConfig.com