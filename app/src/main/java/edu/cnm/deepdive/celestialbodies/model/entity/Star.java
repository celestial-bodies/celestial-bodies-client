package edu.cnm.deepdive.celestialbodies.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import java.util.Calendar;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

@Entity

public class Star {

  @PrimaryKey
  @NonNull
  @Attribute
  private String id;

//  @NonNull
//  private Calendar time = Calendar.getInstance();


  @Element
  private String catId;

  @Element
  private String de;

  @Element
  private String mag;

  @Element
  private String ra;

//  @NonNull
//  public Calendar getTime() {
//    return time;
//  }
//
//  public void setTime(@NonNull Calendar time) {
//    this.time = time;
//  }

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
