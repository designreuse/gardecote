package com.gardecote.entities;

import java.util.Comparator;

/**
 * Created by Dell on 12/12/2016.
 */
public class qCompareByEspece implements Comparator<qEspeceTypee> {
    @Override
    public int compare(qEspeceTypee o1, qEspeceTypee o2) {
        String qespeceID1=o1.getQespeceId().toString();
        String qespeceID2=o2.getQespeceId().toString();
        return qespeceID1.compareTo(qespeceID2);
    }
}
