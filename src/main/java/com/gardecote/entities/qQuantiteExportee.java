package com.gardecote.entities;

/**
 * Created by Dell on 25/10/2016.
 */
public class qQuantiteExportee {
    private enumZonOrientation enumZonOrientationPeche;
    private Integer qte;

    public qQuantiteExportee(enumZonOrientation enumZonOrientationPeche, Integer qte) {
        this.enumZonOrientationPeche = enumZonOrientationPeche;
        this.qte = qte;
    }

    public enumZonOrientation getEnumZonOrientationPeche() {
        return enumZonOrientationPeche;
    }

    public void setEnumZonOrientationPeche(enumZonOrientation enumZonOrientationPeche) {
        this.enumZonOrientationPeche = enumZonOrientationPeche;
    }

    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }
}
