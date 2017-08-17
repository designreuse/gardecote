package com.gardecote.business.service.impl;

import com.gardecote.business.service.userService;
import com.gardecote.data.repository.jpa.userRepository;
import com.gardecote.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lehbib on 27/06/2017.
 */
@Service
@Transactional
public class userServiceImpl implements userService {
    @Autowired
    private userRepository userRepo;

    @Override

    public Page<User> findAllUsers(int p, int size) {

        //	Iterable<qLic> entities = codauthJpaRepository.findAll(new PageRequest(p, size));
        Page<User> entities = userRepo.findAll(new PageRequest(p, size));

        return entities;
    }
    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User save(User entity) {
        return null;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public User create(User entity) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
