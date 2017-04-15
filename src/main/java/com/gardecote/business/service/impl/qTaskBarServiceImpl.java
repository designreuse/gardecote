package com.gardecote.business.service.impl;

import com.gardecote.business.service.qTaskBarService;
import com.gardecote.data.repository.jpa.qProgressBarRepository;
import com.gardecote.entities.qTaskProgressBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dell on 13/04/2017.
 */
@Service
@Transactional
public class qTaskBarServiceImpl implements qTaskBarService {
    @Autowired
    private qProgressBarRepository qProgressBarRepository;

    @Override
    public qTaskProgressBar findById(String idact) {
        qTaskProgressBar prog=qProgressBarRepository.findOne(idact);
        return qProgressBarRepository.findOne(idact);
    }

    @Override
    public qTaskProgressBar save(qTaskProgressBar entity) {
        return qProgressBarRepository.save(entity);
    }

    @Override
    public qTaskProgressBar update(qTaskProgressBar entity) {
        return qProgressBarRepository.save(entity);
    }

    @Override
    public qTaskProgressBar create(qTaskProgressBar entity) {
        return qProgressBarRepository.save(entity);
    }

    @Override
    public void delete(String idCapture) {
        qProgressBarRepository.delete(idCapture);
    }
}
