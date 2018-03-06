package org.halo.thallo.mmr.core.runtime;

/**
 * 分页条件
 */
public class PageRequest {
    private int pageSize;
    private int page;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
