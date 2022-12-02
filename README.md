# northstar-w3-exchange
> web3交易所功能聚合模块
> WEB3 EXCHANGE CLIENT & Third party data service provider Aggregation of functions
> 
## 交易所客户端 & 第三方服务数据提供商
  * web3交易所
    1. cex交易所
      * binance
      * okx
      * gate
    2. dex交易所
      * uni swap
      * panacake swap
  * 第三方数据服务提供商
  ```
       参考:
         https://zhuanlan.zhihu.com/p/379256098
         币安交易API binance-official-api-docs https://github.com/binance/binance-spot-api-docs
         https://binance-docs.github.io/apidocs/spot/en/#general-api-information
         https://github.com/binance/binance-connector-java
  ```
### 项目结构
```
northstar-w3-exchange
├── w3-common -- 公共模块
├── w3-cex -- 中心化交易所交易相关业务模块
├    ├── w3-binance -- 币安交易所
├    ├── w3-okx -- 欧易交易所
├    ├── dante-module-upms-logic -- UPMS 基础管理及共享代码模块组件
├    └── dante-module-upms-rest -- UPMS 基础管理接口模块组件
├── w3-dex -- 去中心化交易相关业务模块
├    ├── w3-uni -- uni
├    └── w3-panacake -- panacake
├── w3-third -- 第三方服务数据提供商
├    ├── w3-1 -- 第三方1
└──  └── w3-2 -- 第三方2
```

