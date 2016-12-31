package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dell on 09/10/2016.
 */
@Entity
@Table(name="qTypeLic21", schema="dbo", catalog="GCM1" )
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

  private char codelic;

  private char codeaut;
  private String descr;

  public qTypeLic(char codelic, char codeaut, String descr) {
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

  public char getCodelic() {
    return codelic;
  }

  public void setCodelic(char codelic) {
    this.codelic = codelic;
  }

  public char getCodeaut() {
    return codeaut;
  }

  public void setCodeaut(char codeaut) {
    this.codeaut = codeaut;
  }

  public String getDescr() {
    return descr;
  }

  public void setDescr(String descr) {
    this.descr = descr;
  }
}
