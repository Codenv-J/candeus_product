package com.candeus.product.tool;

import org.springframework.util.DigestUtils;

import java.util.Base64;

/**
 * 基于spring 编码
 *
 * @author codenj
 * @since 2023/4/16
 */
public class EncodeUtils {
    private static final String SALT = "*#&%$^";

    public static String generateMD5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    public static String generateSaltMD5(String str) {
        String base = str + "/" + SALT;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    public static String generateBase64(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    public static String base64decode(String str) {
        return new String(Base64.getDecoder().decode(str));
    }

    /**
     * token由两大部分组成：
     * 第一部分：base64的用户id，可以直接解码，获取到用户id；
     * 第二部分：id+时间戳+盐的md5码再转base64；
     * <p>
     * 如何防止token被伪造：假设token泄露，想研究出token的构成，从而进行伪造。
     * <p>
     * 假设第一部分被猜到是用户id，如果想伪造其他用户的token，需要使用其他用户的id+第二部分,
     * 但是第二部分中带有原用户的id，所以伪造失败。
     * 原用户修改密码后token会刷新（利用时间戳），想伪造原用户的id，因为不知道第二部分的构成，伪造失败。
     * <p>
     * 假设想要破解第二部分，进行解码后获得的是md5码，是加盐的，即解码后的内容几乎不可能被破解，
     * 所以无法得知第二部分的构成，伪造失败。
     */
    public static String generateToken(Long userId) {
        String id = String.valueOf(userId);
        String token = generateSaltMD5(id + System.currentTimeMillis());
        return generateBase64(id) + "." + generateBase64(token);
    }

    public static String generatePassword(String password) {
        return generateSaltMD5(password);
    }


}
