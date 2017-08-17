package com.gardecote.data.repository.jpa;

import com.gardecote.entities.User;
import com.gardecote.entities.qZone;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by lehbib on 27/06/2017.
 */
public interface userRepository extends PagingAndSortingRepository<User, String> {

}
