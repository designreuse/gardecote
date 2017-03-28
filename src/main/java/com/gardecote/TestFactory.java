package com.gardecote;

import com.gardecote.business.service.qDocService;
import com.gardecote.entities.qDoc;
import com.gardecote.entities.qDocPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dell on 18/03/2017.
 */
public class TestFactory {
    @Autowired
    private static ApplicationContext context;

    public TestFactory() {
    }

    public static java.util.Collection generateCollection() {
        Date dateDepart=null;
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateDepart = sdfmt1.parse("2017-03-01 00:00:00.0000000");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        qDocService docservice=(qDocService) context.getBean("qDocServiceImpl");
// Creates the collection
        java.util.Vector collection = new java.util.Vector();

// Adds the values in the bean and adds it into the collection
        collection.add(docservice.findById(new qDocPK("V3SWA",dateDepart)));


// returns the collection of beans.
        return collection;
    }

    public static qDoc[] generateArrayList() {
        qDoc[] list = new qDoc[1];
        Date dateDepart=null;
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateDepart = sdfmt1.parse("2017-03-01 00:00:00.0000000");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        qDocService docservice=(qDocService) context.getBean("qDocServiceImpl");
// Creates the collection
        java.util.Vector collection = new java.util.Vector();

// Adds the values in the bean and adds it into the collection
        list[0]= (docservice.findById(new qDocPK("V3SWA",dateDepart)));;


// returns the collection of beans.
        return list;
    }
    // Creates the Arraylist





}
