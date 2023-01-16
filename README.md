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
         策略参考:
         https://www.tradingview.com/
         回测数据来源
         https://www.163.com/dy/article/HACPOOKH0539O6LM.html
  ```
### 第三方相关业务系统
  * 数据源到任意数据源数据同步中间件
  * https://github.com/JMagician  java调用智能合约
  ```
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
### tips
1.rust池化技术 原理:https://zhuanlan.zhihu.com/p/480956704 案例:https://my.oschina.net/u/4090830/blog/5605669
2.机器学习框架Neuronika，速度堪比PyTorch https://view.inews.qq.com/a/20210618A04XPX00
3. https://my.oschina.net/u/4865736/blog/4877966  DolphinDB + Tushare 数据回测方案
4. https://zhuanlan.zhihu.com/p/499932095 回测案例
5. 交易验证/闪兑/网格交易/赚币/交易大数据暂未封装
### 开发日志
* 2022-12-05
  w3-binance模块,封装基础功能,配置类定义
* 2022-12-06
  w3-okx模块构建,资料收集学习
* 2022-12-07
  OKX market相关接口文档阅读
    market获取系统服务时间接口
* 2022-12-08
    OKX market接口实现
* 2022-12-09
   1. Java中的 “Pandas”(https://blog.csdn.net/cyj972628089/article/details/126127748)
* 2022-12-12
   1. 公共数据接口模块封装
* 2022-12-13
   1. 账户/子账户/资金相关接口封装
* 2022-12-14
    1. 交易相关接口封装
    2. 交易验证/闪兑/网格交易/赚币/交易大数据暂未封装
* 2022-12-15
    1. 测试案例
    2. (1)回测相关准备 (2)接入交易应用
* 2023-01-13
    JDK9后版本升级注意事项：
    ```
     https://goodluckpeng.blog.csdn.net/article/details/127768286?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-127768286-blog-126178828.pc_relevant_3mothn_strategy_and_data_recovery&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-127768286-blog-126178828.pc_relevant_3mothn_strategy_and_data_recovery&utm_relevant_index=1
    ``` 
* 2013-01-16
 1. 集成第三方行情服务商CryptoCompare 主要是币种排名接口
  
  

    