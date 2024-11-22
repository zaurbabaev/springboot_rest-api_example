package com.rest.api.utils;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValidateUtils {

    private Object value;

    boolean required;

    Integer maxLength;

    String fieldName;

    String regex;

    boolean onlyNumber;

    boolean isInteger;

    Long max;

    Long min;

    String message;

    final String ONLY_NUMBER = "[0-9]+";

    public Map validate() {
        Map<String, String> errors = new HashMap<>();


        // check field is required
        if (required && ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(fieldName)) {
            errors.put(fieldName, fieldName + " is required");
        }

        // check max length of field
        if (!ObjectUtils.isEmpty(maxLength) && !ObjectUtils.isEmpty(value)
                && value.toString().length() > maxLength
                && !ObjectUtils.isEmpty(fieldName)) {
            errors.put(fieldName, fieldName + " must has 0 -" + maxLength + "characters");
        }


        // check field name is only number
        if (onlyNumber && !ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(fieldName)) {
            Pattern patternOnlyNumber = Pattern.compile(ONLY_NUMBER);
            Matcher matcher = patternOnlyNumber.matcher(value.toString());
            if (!matcher.matches()) {
                errors.put(fieldName, fieldName + " must contain only number");
            }
        }
        // check field name is integer
        if (isInteger && !ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(fieldName)) {
            try {
                Integer.parseInt(value.toString());
            } catch (Exception e) {
                errors.put(fieldName, fieldName + " must is integer number");
            }
        }

        // check max and min of filed value
        if (!ObjectUtils.isEmpty(max) && !ObjectUtils.isEmpty(value)
                && !ObjectUtils.isEmpty(min) && !ObjectUtils.isEmpty(fieldName)) {
            try {
                Long valueCheck = Long.valueOf(value.toString());
                if (valueCheck < min || valueCheck > max) {
                    errors.put(fieldName, fieldName + " must range from " + min + " to " + max);
                }
            } catch (Exception e) {
                errors.put(fieldName, fieldName + " is valid data type");
            }
        }

        return errors;
    }

}
