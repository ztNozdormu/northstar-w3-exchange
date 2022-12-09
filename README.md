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
       参考资料:
         第三方行情服务商参考
         https://zhuanlan.zhihu.com/p/379256098 
         币安交易API 
         binance-official-api-docs https://github.com/binance/binance-spot-api-docs
         https://binance-docs.github.io/apidocs/spot/en/#general-api-information
         https://github.com/binance/binance-connector-java
         欧易交易所API
         https://www.okx.com/docs-v5/zh/#overview 
  ```
### 第三方相关业务系统
  * 数据源到任意数据源数据同步中间件
  ```aidl
     https://gitee.com/mydataharbor/mydataharbor?utm_source=oschina&utm_medium=link-mid&utm_campaign=new_wh
  ```
  * 
### 项目结构
```
northstar-w3-exchange
├── w3-common -- 公共模块
├── w3-cex -- 中心化交易所交易相关业务模块
├    ├── w3-binance -- 币安交易所
├    ├── w3-okx -- 欧易交易所
├    ├── xxx --xxx组件
├    └── xxx -- xxx组件
├── w3-dex -- 去中心化交易相关业务模块
├    ├── w3-uni -- uni
├    └── w3-panacake -- panacake
├── w3-third -- 第三方服务数据提供商
├    ├── w3-1 -- 第三方1
└──  └── w3-2 -- 第三方2
```
### 开发日志
2022-12-05
  w3-binance模块,封装基础功能,配置类定义
2022-12-06
  w3-okx模块构建,资料收集学习
2022-12-07
  OKX market相关接口文档阅读
    1. market获取系统服务时间接口
2022-12-08
  1.OKX market接口实现
  2.rust池化技术 原理:https://zhuanlan.zhihu.com/p/480956704 案例:https://my.oschina.net/u/4090830/blog/5605669
  3.rust解析json案例
  4.worklist服务集成
  5.机器学习框架Neuronika，速度堪比PyTorch https://view.inews.qq.com/a/20210618A04XPX00
2022-12-09
Java中的 “Pandas”:
https://blog.csdn.net/cyj972628089/article/details/126127748
    