package com.candeus.product.tool;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampUtil {

    // 私有化构造器，防止实例化
    private TimestampUtil() {}

    // 日期时间格式化器
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // 获取当前时间戳的线程安全方法
    public static Timestamp getCurrentTimestamp() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 将当前时间格式化为字符串
        String formattedNow = now.format(FORMATTER);
        // 将格式化的字符串解析为 LocalDateTime 对象
        LocalDateTime parsedDateTime = LocalDateTime.parse(formattedNow, FORMATTER);
        // 转换为 java.sql.Timestamp 对象并返回
        return Timestamp.valueOf(parsedDateTime);
    }
}
