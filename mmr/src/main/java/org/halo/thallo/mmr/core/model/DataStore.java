package org.halo.thallo.mmr.core.model;

import org.halo.thallo.mmr.core.service.MMRException;

import java.util.Map;

/**
 * Created by lihong on 17-10-10.
 */
public interface DataStore {
    DataObject persist() throws MMRException;
    DataObject load() throws MMRException;
}
