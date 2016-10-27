package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dell on 09/10/2016.
 */
@Entity
public class qTypeLic implements Serializable {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(name="id_typelic", nullable=false)
  private Integer idTypeLic;

   private char codelic;

  private char codeaut;
  private String descr;

  public qTypeLic(char codelic, char codeaut, String descr) {
    this.codelic = codelic;
    this.codeaut = codeaut;
    this.descr = descr;
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
