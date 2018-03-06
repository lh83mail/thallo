package org.halo.thallo.mmr.core.runtime;

import java.util.List;
import java.util.Map;

public class PagedData<T> {
    private List<Map<String, Object>> data;
    private int totalRecords;

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }
}
