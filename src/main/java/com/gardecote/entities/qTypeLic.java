package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dell on 09/10/2016.
 */
@Entity
@Table(name="qTypeLic33", schema="dbo", catalog="GCM4" )
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qTypeLic.countAll", query="SELECT COUNT(x) FROM qTypeLic x" )
} )

public class qTypeLic implements Serializable {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(name="id_typelic", nullable=false)
  private Integer idTypeLic;

  public qTypeLic() {
  }

  private String codelic;

  private String codeaut;
  private String descr;

  public qTypeLic(String codelic, String codeaut, String descr) {
    this.codelic = codelic;
    this.codeaut = codeaut;
    this.descr = descr;
  }

  public Integer getIdTypeLic() {
    return idTypeLic;
  }

  public void setIdTypeLic(Integer idTypeLic) {
    this.idTypeLic = idTypeLic;
  }

  public String getCodelic() {
    return codelic;
  }

  public void setCodelic(String codelic) {
    this.codelic = codelic;
  }

  public String getCodeaut() {
    return codeaut;
  }

  public void setCodeaut(String codeaut) {
    this.codeaut = codeaut;
  }

  public String getDescr() {
    return descr;
  }

  public void setDescr(String descr) {
    this.descr = descr;
  }
}
