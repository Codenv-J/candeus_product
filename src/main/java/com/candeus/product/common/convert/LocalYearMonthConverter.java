package com.candeus.product.common.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/**
 * The type Local year month converter.
 *
 * @author codenj
 * @since 2023 /10/5
 */
@Component
public class LocalYearMonthConverter implements Converter<String, YearMonth> {
    private final String pattern = "yyyy-MM";

    @Override
    public YearMonth convert(String source) {
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }
        if (source.matches("^\\d{4}-\\d{1,2}$")) {
            return parseDate(source);
        } else {
            throw new IllegalArgumentException(
                    String.format("Invalid YearMonth value '%s'.The format must be '%s'.", source, pattern));
        }
    }

    private YearMonth parseDate(String dateStr) {
        return YearMonth.from(DateTimeFormatter.ofPattern(pattern).parse(dateStr));
    }
}
