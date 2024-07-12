package com.candeus.product.tool;


import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: 云测平台
 * @description
 * @author: codenj
 * @create: 2024-05-10 18:10
 **/
public class SpringBeanUtil {
    /**
     * 根据给定的源对象和目标类型，创建一个目标类型的实例，并将源对象的属性值复制到新实例中。
     *
     * @param source 源对象，其属性值将被复制。
     * @param target 目标类型的Class对象，用于创建新实例并复制属性值。
     * @return 一个新创建的目标类型的实例，其属性值已从源对象复制。
     * @throws RuntimeException 如果在创建目标实例或复制属性时发生异常。
     */
    public static <T> T copyProperties(Object source, Class<T> target) {
        try {
            // 创建目标类型的实例
            T t = target.getConstructor().newInstance();
            // 将源对象的属性值复制到目标对象
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            // 抛出运行时异常，封装任何发生的异常
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据源列表中每个元素的属性，创建并返回一个新列表，新列表中的每个元素都是目标类型的一个实例。
     * 该方法通过调用 copyProperties 方法，将源列表中的每个元素的属性复制到目标类型的新实例中。
     *
     * @param sourceList 源列表，包含需要复制属性的元素。这些元素的类型可以任意，但必须至少包含与目标类型相匹配的属性。
     * @param target 目标类型，指定新列表中元素的类型。
     * @return 返回一个列表，其中包含根据源列表中元素属性创建的目标类型的新实例。
     */
    public static <T> List<T> copyProperties(List<?> sourceList, Class<T> target) {
        // 创建目标类型列表，用于存放复制后的元素
        ArrayList<T> targetList = new ArrayList<>();
        // 遍历源列表，为每个元素创建目标类型的实例，并添加到目标列表中
        sourceList.forEach(source -> {
            T t = copyProperties(source, target); // 复制属性到目标类型实例
            targetList.add(t); // 将目标类型实例添加到目标列表
        });
        return targetList;
    }
}
