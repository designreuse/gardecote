package com.gardecote.entities;

import com.gardecote.data.repository.jpa.*;
import org.springframework.context.ApplicationContext;

import javax.persistence.Id;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dell on 31/10/2016.
 */
public class qDistributeur implements Serializable {

    public qDistributeur() {
    }


   public qCarnet distribuer(qCarnet qcarnet,qConcession qconcession,qNavire qnavire, qUsine qusine){
        qcarnet.setQnavire(qnavire);
        qcarnet.setQusine(qusine);
       qcarnet.setQconcession(qconcession);
       return qcarnet;
    }

}


