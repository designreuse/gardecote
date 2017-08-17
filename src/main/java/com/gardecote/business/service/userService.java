package com.gardecote.business.service;

import com.gardecote.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by lehbib on 27/06/2017.
 */
public interface userService {
    /**
     * Loads an entity from the database using its Primary Key

     * @return entity
     */
    public Page<User> findAllUsers(int p, int size);

    User findById(Integer id) ;

    /**
     * Loads all entities.
     * @return all entities
     */
    List<User> findAll();

    /**
     * Saves the given entity in the database (create or update)
     * @param entity
     * @return entity
     */
    User save(User entity);

    /**
     * Updates the given entity in the database
     * @param entity
     * @return
     */
    User update(User entity);

    /**
     * Creates the given entity in the database
     * @param entity
     * @return
     */
    User create(User entity);

    /**
     * Deletes an entity using its Primary Key
     * @param id
     */
    void delete(Integer id);

}
