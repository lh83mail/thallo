package org.halo.thallo.authenserver.service.impl;

import org.halo.thallo.authenserver.dao.SchemaRepository;
import org.halo.thallo.authenserver.entity.SchemaEntity;
import org.halo.thallo.authenserver.model.Schema;
import org.halo.thallo.authenserver.service.SchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by lihong on 17-4-15.
 */
@Service
public class SchemaServiceImpl implements SchemaService {
    private SchemaRepository schemaRepository;

    @Override
    public Schema saveSchema(Schema schema) {
        SchemaEntity entity = SchemaEntity.copyFrom(schema);
        entity = schemaRepository.save(entity);
        return entity;
    }

    @Override
    public Schema getSchema(String id) {
        return schemaRepository.findOne(id);
    }

    @Override
    public void deleteSchema(String id) {
        SchemaEntity schema = schemaRepository.findOne(id);
        if (schema == null) {
            return;
        }
        schemaRepository.delete(schema);
    }

    @Override
    public Page<Schema> querySchemas(String filter, int page, int limit) {
        SchemaEntity schema = new SchemaEntity();
        schema.setDescreption(filter);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<SchemaEntity> schemaExample = Example.of(schema, matcher);

        Page<SchemaEntity> result =  schemaRepository.findAll(schemaExample, new PageRequest(page, limit));
        return result.map(s-> s);
    }

    @Autowired
    public void setSchemaRepository(SchemaRepository schemaRepository) {
        this.schemaRepository = schemaRepository;
    }
}
