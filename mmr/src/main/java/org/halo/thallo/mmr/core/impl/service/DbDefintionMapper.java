package org.halo.thallo.mmr.core.impl.service;

import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.ValueType;

/**
 * Created by dell01 on 2017/10/28.
 */
public class DbDefintionMapper {
 public String columnDefintion(Attribute attr) {
     return attr.getName() + " " + defintionType(attr);
 }

 public String defintionType(Attribute attr) {
     switch (attr.getValueType()) {
         case STRING:
           return "varchar(" + attr.getLength() +")";
         case DATE:
             return "datetime";
         case BOOL:
             return "bit";
         case LONG:
             return "bigint";
         case BINNARY:
             return "blob";
         case TEXT:
             return "text";
         default:
             throw new RuntimeException("Unkonw Value Type Exception");
     }
 }
}
