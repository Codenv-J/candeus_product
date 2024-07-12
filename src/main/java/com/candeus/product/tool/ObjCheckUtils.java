package com.candeus.product.tool;



import com.candeus.product.common.exception.CustomException;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * 校验工具
 *
 * @author codenj
 * @since 2023 /3/9
 */
public class ObjCheckUtils {

    /**
     * If the object is null, return true, else return false.
     *
     * @param obj the object
     * @return the boolean
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }


    /**
     * If one is null, return true, else return false.
     *
     * @param objects the objects
     * @return the boolean
     */
    public static boolean isNull(Object... objects) {
        for (Object obj : objects) {
            if (isNull(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * If the object is not null, return true, else return false.
     *
     * @param obj the object
     * @return the boolean
     */
    public static boolean nonNull(Object obj) {
        return obj != null;
    }

    /**
     * If all is not null, return true, else return false.
     *
     * @param objects the objects
     * @return the boolean
     */
    public static boolean nonNull(Object... objects) {
        return !isNull(objects);
    }

    /**
     * If the string is null or empty,
     * or the content is all space symbols,
     * or the content is "null",
     * return true, else return false.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isBlank(String str) {
        return isNull(str) || str.isEmpty() || str.trim().isEmpty() || "null".equals(str);
    }

    /**
     * Is blank boolean.
     *
     * @param strings the strings
     * @return the boolean
     */
    public static boolean isBlank(String... strings) {
        for (String str : strings) {
            if (isBlank(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is not blank boolean.
     *
     * @param str the strings
     * @return the boolean
     */
    public static boolean nonBlank(String str) {
        return !isBlank(str);
    }

    /**
     * Is not blank boolean.
     *
     * @param strings the strings
     * @return the boolean
     */
    public static boolean nonBlank(String... strings) {
        return !isBlank(strings);
    }

    /**
     * check Array,Collection,Map
     *
     * @param obj the object
     * @return the boolean
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }

        // else
        return false;
    }

    /**
     * If one is empty, the result is true;
     * If all is non empty,the result is false.
     *
     * @param objs the objects
     * @return the boolean
     */
    public static boolean isEmpty(Object... objs) {
        for (Object obj : objs) {
            if (isEmpty(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Non empty boolean.
     *
     * @param obj the object
     * @return the boolean
     */
    public static boolean nonEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * Is not empty boolean.
     *
     * @param objs the objects
     * @return the boolean
     */
    public static boolean nonEmpty(Object... objs) {
        return !isEmpty(objs);
    }

    /**
     * @param <T>     the type parameter
     * @param obj     the obj
     * @param message the message
     * @return the t
     * @throws CustomException if {@code obj} is {@code null}
     */
    public static <T> T requireNonNull(T obj, String message) {
        if (obj == null) {
            throw new CustomException(message);
        }
        return obj;
    }


    public static String CheckNormUserNonNull(String name, String password){

        // 验证邮箱和密码长度
        int nameMinLength = 1; // 设置用户名最小长度
        int nameMaxLength = 8; // 设置用户名最大长度
        int passwordMinLength = 6; // 设置密码最小长度
        int passwordMaxLength = 20; // 设置密码最大长度

        if (name.length() < nameMinLength || name.length() > nameMaxLength) {
            return "用户名长度应在" + nameMinLength + "~" + nameMaxLength + "个字符之间";
        }
        if (password.length() < passwordMinLength || password.length() > passwordMaxLength) {
            return "密码长度应在" + passwordMinLength + "~" + passwordMaxLength + "个字符之间";
        }
        // 密码的字符限制
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$"; // 至少一个小写字母、一个大写字母和一个数字
        if (!password.matches(passwordRegex)) {
            return "密码必须包含小写字母、大写字母和数字";
        }
        return "true";
    }
}
