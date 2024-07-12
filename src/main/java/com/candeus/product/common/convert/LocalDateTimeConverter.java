package com.candeus.product.common.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author codenj
 * @since 2023/10/5
 */
@Component
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {
    private final String[] patterns = new String[]{"yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss"};

    @Override
    public LocalDateTime convert(String source) {
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }
        if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, patterns[0]);
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, patterns[1]);
        } else {
            throw new IllegalArgumentException(
                    String.format("Invalid LocalDateTime value '%s'.The format must be '%s' or '%s'.",
                            source, patterns[0], patterns[1]));
        }
    }

    private LocalDateTime parseDate(String dateStr, String format) {
        return LocalDateTime.from(DateTimeFormatter.ofPattern(format).parse(dateStr));
    }
}
