package org.halo.thallo.authenserver.dao;

import org.halo.thallo.authenserver.entity.SchemaEntity;
import org.halo.thallo.authenserver.model.Attribue;
import org.halo.thallo.authenserver.model.Schema;
import org.halo.thallo.authenserver.service.SchemaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by lihong on 17-4-15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(value = {
        "spring.cloud.discovery.enabled=false",
        "spring.profiles.active=test"
})
public class RunTest {
    @Autowired
    private SchemaService schemaService;

    @Autowired
    private MongoTemplate mongoTemplate;

    private String schemaId = "__$_UserSchemaId";

    @Before
    public void setUp() {
        // 清理数据
        mongoTemplate.dropCollection(SchemaEntity.class);
    }

    @Test
    public void testSaveSchema() {
        Schema schema = new Schema();
        schema.setId(schemaId);
        schema.setDescreption("用户模型");
        schema.setAttributes(Arrays.asList(
                new Attribue("name", "姓名", "string", "未定义", "用户姓名"),
                new Attribue("age", "年龄", "int", 32, "年龄")
        ));

        schema =  schemaService.saveSchema(schema);
        assertNotNull("应该返回Schema对象", schema);
        assertEquals("ID应该是USER", schemaId, schema.getId());
        assertNotNull("属性列表不应该为null", schema.getAttributes());

        // 更新
        List<Attribue> attributes = new ArrayList<>();
        for (Attribue attribue : schema.getAttributes()) {
            attributes.add(attribue);
        }

        schema = new Schema();
        schema.setId(schemaId);
        schema.setDescreption("用户模型对象");
        attributes.add(new Attribue("birthday", "生日", "date", new Date(), "生日"));
        schema.setAttributes(attributes);

        schema = schemaService.saveSchema(schema);
        assertNotNull("应该返回Schema对象", schema);
        assertEquals("应该包含有3个属性对象",  3, schema.getAttributes().size());
    }

    @Test
    public void testGetSchema() {
        String schemaId = prepareOneSchema();
        Schema schema = schemaService.getSchema(schemaId);
        assertNotNull("应该返回Schema对象", schema);
    }

    @Test
    public void testDeleteSchema() {
       String schemaId =  prepareOneSchema();
        schemaService.deleteSchema(schemaId);
        Schema schema = schemaService.getSchema(schemaId);
        assertNull("schema应该是null", schema);
    }

    @Test
    public void querySchemas() {
        // prepare data
        for (int i = 0; i < 3; i++) {
            SchemaEntity schema = new SchemaEntity();
            schema.setId("schemaid" + i);
            schema.setDescreption("查询测试_" + i);
            mongoTemplate.save(schema);
        }

        assertFalse("实现查询", true);

//        Page<Schema> page = schemaService.querySchemas(0, 20);
//        assertNotNull("应该有数据", page.getContent());
//        assertEquals("记录应该有3条", 3, page.getTotalElements());
    }


    /**
     * 确保库里已经有了一条数据
     * @return
     */
    private String prepareOneSchema() {
        Schema schema = new Schema();
        schema.setId(UUID.randomUUID().toString());
        schema.setDescreption("用户模型");
        schema.setAttributes(Arrays.asList(
                new Attribue("name", "姓名", "string", "未定义", "用户姓名"),
                new Attribue("age", "年龄", "int", 32, "年龄")
        ));
        schema =  schemaService.saveSchema(schema);
        assertNotNull("应该返回Schema对象", schema);
        return schema.getId();
    }
}
