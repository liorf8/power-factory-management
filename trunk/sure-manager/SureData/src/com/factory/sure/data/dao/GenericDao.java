package com.factory.sure.data.dao;

import java.io.Serializable;
import java.util.List;



public interface GenericDao <T, PK extends Serializable> {

    /**
     * Find all instances
     */
    List<T> findAll();

    /** Retrieve an object that was previously persisted to the database using
     *   the indicated id as primary key
     */
    T read(PK id);

    /** Save changes made to a persistent object.  */
    void saveOrUpdate(T transientObject);

    /** Remove an object from persistent storage in the database */
    void delete(T persistentObject);
}
