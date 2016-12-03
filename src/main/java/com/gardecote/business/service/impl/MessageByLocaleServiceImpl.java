package com.gardecote.business.service.impl;

import com.gardecote.business.service.MessageByLocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Created by Dell on 30/11/2016.
 */
@Service
public class MessageByLocaleServiceImpl implements MessageByLocaleService {
    @Autowired
    private MessageSource messageSource;

    @Override
    public String getMessage(String id) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(id,null,locale);
    }
}
