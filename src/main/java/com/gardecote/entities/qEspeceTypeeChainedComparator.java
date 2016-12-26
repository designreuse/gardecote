package com.gardecote.entities;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Dell on 12/12/2016.
 */
public class qEspeceTypeeChainedComparator implements Comparator<qEspeceTypee> {
    private List<Comparator<qEspeceTypee>> listComparators;

    @SafeVarargs
    public qEspeceTypeeChainedComparator(Comparator<qEspeceTypee>... comparators) {
        this.listComparators = Arrays.asList(comparators);
    }

    @Override
    public int compare(qEspeceTypee emp1, qEspeceTypee emp2) {
        for (Comparator<qEspeceTypee> comparator : listComparators) {
            int result = comparator.compare(emp1, emp2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}
