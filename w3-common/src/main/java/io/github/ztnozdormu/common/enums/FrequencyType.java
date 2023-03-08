package io.github.ztnozdormu.common.enums;

import java.util.*;
import java.util.stream.Stream;

/**
 * K线周期枚举
 * 1m，3m，5m，15m，30m，1h，2h，4h，6h，8h，12h，1d，3d，1w，1M
 */
public enum FrequencyType {
    /**
     * 1分钟线
     */
    MIN_1("1m", "1分钟线"),
    /**
     * 3分钟线
     */
    MIN_3("3m", "3分钟线"),
    /**
     * 5分钟线
     */
    MIN_5("5m", "5分钟线"),
    /**
     * 15分钟线
     */
    MIN_15("15m", "15分钟线"),
    /**
     * 30分钟线
     */
    MIN_30("30m", "30分钟线"),
    /**
     * 1小时线
     */
    H_1("1h", "1小时线"),
    /**
     * 2小时线
     */
    H_2("2h", "2小时线"),
    /**
     * 4小时线
     */
    H_4("4h", "4小时线"),
    /**
     * 6小时线
     */
    H_6("6h", "6小时线"),
    /**
     * 8小时线
     */
    H_8("8h", "8小时线"),
    /**
     * 12小时线
     */
    H_12("12h", "12小时线"),
    /**
     * 日线
     */
    ONE_DAY("1d", "日线"),
    /**
     * 三日线
     */
    THREE_DAY("3d", "三日线"),
    /**
     * 周线
     */
    ONE_WEEK("1w", "周线"),
    /**
     * 月线
     */
    ONE_MONTH("1M", "月线");

    private final String value;

    private final String desc;

    FrequencyType(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 转换为字典 key-value结构
     *
     * @return
     */
    public static List<Map<String, String>> convertToDictMap() {
        List<Map<String, String>> mapList = new ArrayList<>();
        Arrays.stream(FrequencyType.values()).forEach(FrequencyType -> {
            Map<String, String> map = new HashMap<>();
            map.put(FrequencyType.value, FrequencyType.desc);
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
    public static FrequencyType getInstance(String value) {
        FrequencyType[] values = values();
        Optional<FrequencyType> optional = Stream.of(values).filter(e ->
                e.value.equals(value)).findFirst();
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new IllegalArgumentException("非法的枚举value:" + value);
    }

    public String value() {
        return this.value;
    }

    public static String getNameByValue(String value) {
        try {
            FrequencyType instance = getInstance(value);
            return instance.desc;
        } catch (Exception e) {
            return "";
        }
    }

    public static void main(String[] args) {
        FrequencyType frequencyType = FrequencyType.getInstance("1m");
        System.out.println(frequencyType);
    }
}
