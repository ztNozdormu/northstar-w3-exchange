package com.w3.exchange.common.enums;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * 交易所枚举类型
 */
public enum ExchangeType {
     /**
      * 币安交易所
      */
     BINANCE(1,"币安"),
     /**
      * 欧易交易所
      */
     OKX(2,"欧易"),
     /**
      * 芝麻开门交易所
      */
     GATE(3,"芝麻开门");

     private final int value;

     private final String name;

     ExchangeType(Integer value, String name) {
          this.value = value;
          this.name = name;
     }


     /**
      * 通过指定的value获取对应的枚举信息
      *
      * @param value
      * @return
      */
     public static ExchangeType getInstance(int value) {
          ExchangeType[] values = values();
          Optional<ExchangeType> ExchangeTypeOptional = Stream.of(values).filter(e -> e.value == value).findFirst();
          if (ExchangeTypeOptional.isPresent()) {
               return ExchangeTypeOptional.get();
          }
          throw new IllegalArgumentException("非法的枚举value:" + value);
     }

     public Integer value() {
          return this.value;
     }

     public static String getNameByValue(Integer value) {
          try {
               ExchangeType instance = getInstance(value);
               return instance.name;
          } catch (Exception e) {
               return "";
          }
     }
}
