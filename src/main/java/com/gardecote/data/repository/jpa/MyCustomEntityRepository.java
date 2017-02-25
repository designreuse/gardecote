package com.gardecote.data.repository.jpa;

import javax.persistence.Entity;

/**
 * Created by Dell on 23/02/2017.
 */
public interface MyCustomEntityRepository  {
    void detach(Entity ent);
}
