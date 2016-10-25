package com.gardecote.entities;

import javax.persistence.Entity;

/**
 * Created by Dell on 09/10/2016.
 */

public class qTypeLic {
  private char codelic;
  private char codeaut;
  private String descr;

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
