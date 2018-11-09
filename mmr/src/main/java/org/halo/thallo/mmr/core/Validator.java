package org.halo.thallo.mmr.core;

/**
 * Created by lihong on 17-8-15.
 */
public interface Validator<E> {
    void validate(E dataObject);
}
