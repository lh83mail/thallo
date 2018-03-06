package org.halo.thallo.mmr.core.model.scope;

/**
 * 变量区
 */
public interface VariableScope {
    Object get(String name);
    Object get(String name, Object defaultValue);
}
