package com.candeus.product.common.result;

import lombok.Data;

/**
 * 带分页的返回结果
 *
 * @author codenj
 * @since 2023/5/3
 */

@Data
public class PageResult extends Result {
    /**
     * 总记录数
     */
    private long total;
    /**
     * 页码
     */
    private long pageNumber;
    /**
     * 页数
     */
    private long pages;
    /**
     * 数据
     */
    private Object data;

    public PageResult(long total, long pageNumber, long pages, Object data) {
        this.total = total;
        this.pageNumber = pageNumber;
        this.pages = pages;
        this.data = data;
    }

    public PageResult(int code, String msg, long total, long pageNumber, long pages, Object data) {
        super(code, msg);
        this.total = total;
        this.pageNumber = pageNumber;
        this.pages = pages;
        this.data = data;
    }
}
