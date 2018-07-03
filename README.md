# 技术栈
* Eureka - 服务注册与发现。
* Zuul - 网关，暴漏给app、web端的统一端口。
* Feign - 模板化的 HTTP 客户端。
* Spring Cloud OAuth2 - 安全控制。

# 应用架构
该项目包含 6 个模块
* eureka - 服务注册与发现
* gateway - 网关
* common - 公共模块
* api - 内部feign接口
* auth - 登录与鉴权
* base - 基础模块，包含数据字典，统一操作日志管理等

# 端口与返回码约定
在开发环境下和测试环境下，端口约定是有意义的，特别是多人工作模式下。

| 模块名                  | 说明           | 返回码    | 端口号|  
| - | - | - | - | 
| springcloud-gateway     | 网关           | 无        | 80    |
| springcloud-eureka      | 服务注册与发现 | 无        | 8000  |
| springcloud-auth        | 鉴权           | 401-999   | 8010  |
| springcloud-base        | 基础模块       | 1001-1500 | 8010  |