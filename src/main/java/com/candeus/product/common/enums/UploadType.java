package com.candeus.product.common.enums;

import java.util.Arrays;
import java.util.List;

/**
 * 文件上传业务类型
 *
 * @author codenj
 * @date 2023/11/26
 **/
public enum UploadType {

    ANY("any", 100, null, "任意文件不限制上传"),
    IMAGE("image", 20, Arrays.asList("jpg", "jpeg", "png"), "仅图片格式上传"),
    EXCEL("excel", 100, Arrays.asList("xls", "xlxs"), "仅Excel表格上传"),
    WORD("word", 100, Arrays.asList("doc", "docx"), "仅Word文档上传"),
    PDF("pdf", 100, Arrays.asList("pdf"), "仅PDF上传"),
    HEAD("head", 10, Arrays.asList("jpg", "jpeg", "png"), "用户头像上传");
    // 更多自定义即可

    private String type;
    private Integer maxSizeMb;
    /**
     * 文件后缀都使用小写
     */
    private List<String> extensions;
    private String desc;

    UploadType(String type, Integer maxSizeMb, List<String> extensions, String desc) {
        this.type = type;
        this.maxSizeMb = maxSizeMb;
        this.extensions = extensions;
        this.desc = desc;
    }

    public static UploadType getUploadType(String type) {
        for (UploadType uploadType : values()) {
            if (uploadType.getType().equals(type)) {
                return uploadType;
            }
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public Integer getMaxSizeMb() {
        return maxSizeMb;
    }

    public List<String> getExtensions() {
        return extensions;
    }

    public String getDesc() {
        return desc;
    }

}
