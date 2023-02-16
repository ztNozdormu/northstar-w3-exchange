package com.w3.exchange.common.enums;

import java.util.*;
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

     private final String desc;

     ExchangeType(Integer value, String desc) {
          this.value = value;
          this.desc = desc;
     }

     /**
      * 转换为字典 key-value结构
      * @return
      */
     public static List<Map<Integer,String>> convertToDictMap() {
          List<Map<Integer,String>> mapList = new ArrayList<>();
          Arrays.stream(ExchangeType.values()).forEach(exchangeType -> {
               Map<Integer,String> map = new HashMap<>();
               map.put(exchangeType.value,exchangeType.desc);
               mapList.add(map);
          });
          return mapList;
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
               return instance.desc;
          } catch (Exception e) {
               return "";
          }
     }
}
