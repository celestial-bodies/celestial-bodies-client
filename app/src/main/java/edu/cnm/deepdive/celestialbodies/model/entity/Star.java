package edu.cnm.deepdive.celestialbodies.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity

public class Star {

  @PrimaryKey
  @NonNull
  private String id;

  private String catId;

  private String de;

  private String mag;

  private String ra;

  public String getCatId() {
    return catId;
  }

  public void setCatId(String catId) {
    this.catId = catId;
  }

  public String getDe() {
    return de;
  }

  public void setDe(String de) {
    this.de = de;
  }

  public String getMag() {
    return mag;
  }

  public void setMag(String mag) {
    this.mag = mag;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRa() {
    return ra;
  }

  public void setRa(String ra) {
    this.ra = ra;
  }

  @Override
  public String toString() {
    return "ClassPojo [catId = " + catId + ", de = " + de + ", mag = " + mag + ", id = " + id
        + ", ra = " + ra + "]";
  }
}
