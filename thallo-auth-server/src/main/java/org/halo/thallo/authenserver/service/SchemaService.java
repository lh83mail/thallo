package org.halo.thallo.authenserver.service;

import org.halo.thallo.authenserver.entity.SchemaEntity;
import org.halo.thallo.authenserver.model.Schema;
import org.springframework.data.domain.Page;
/**
 * Created by lihong on 17-4-14.
 */
public interface SchemaService {
    /**
     * 保存或修改Schema
     * @param schema
     */
    Schema saveSchema(Schema schema);

    /**
     * 获取Schema定义
     * @param id
     * @return
     */
    Schema getSchema(String id);

    /**
     * 删除指定的Schema定义
     * @param id
     */
    void deleteSchema(String id);

    /**
     * 查询Schema定义
     * @param filter
     * @param page
     * @param limit
     * @return
     */
    Page<Schema> querySchemas(String filter, int page, int limit);
}
