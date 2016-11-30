package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
/**
 * Created by Dell on 25/10/2016.
 */
@Entity

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_CONCESSION", discriminatorType=DiscriminatorType.STRING, length=20)
@Table(name="qTypeConcession4", schema="dbo", catalog="GCM1" )
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qTypeConcession.countAll", query="SELECT COUNT(x) FROM qTypeConcession x" )
} )
public class qTypeConcession implements Serializable {
    @Id
 //   @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "qtypeconcessionpk",unique=true, nullable = false)
     private Integer qtypeconcessionpk;


    @Column(name="prefixNum", nullable=false, length=2)
    private enumPrefix     prefixNum    ;

    public qTypeConcession() {
        super();
    }

    public qTypeConcession(Integer qtypeconcessionpk,enumPrefix prefixNum) {
        this.qtypeconcessionpk=qtypeconcessionpk;
        this.prefixNum = prefixNum;
    }

    public Integer getQtypeconcessionpk() {
        return qtypeconcessionpk;
    }

    public void setQtypeconcessionpk(Integer libelle) {
        this.qtypeconcessionpk = libelle;
    }

    public enumPrefix getPrefixNum() {
        return prefixNum;
    }

    public void setPrefixNum(enumPrefix prefixNum) {
        this.prefixNum = prefixNum;
    }


}
