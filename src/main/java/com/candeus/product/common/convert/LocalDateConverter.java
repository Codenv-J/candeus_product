package com.candeus.product.common.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Local date converter.
 *
 * @author codenj
 * @since 2023 /10/5
 */
@Component
public class LocalDateConverter implements Converter<String, LocalDate> {
    private final String pattern = "yyyy-MM-dd";

    @Override
    public LocalDate convert(String source) {
        System.out.println(source);
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }
        if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return parseDate(source);
        } else {
            throw new IllegalArgumentException(
                    String.format("Invalid LocalDate value '%s'.The format must be '%s'.", source, pattern));
        }
    }


    private LocalDate parseDate(String dateStr) {
        return LocalDate.from(DateTimeFormatter.ofPattern(pattern).parse(dateStr));
    }
}
