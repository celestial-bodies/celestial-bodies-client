package edu.cnm.deepdive.celestialbodies.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import java.util.Calendar;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Encapsulates the attributes of a single Star instance.  Room and GSon annotations * are used to
 * specify entity &amp; attribute mapping for database persistence, and property mapping * for JSON
 * serialization/deserialization mapping.
 */
@Entity
public class Star {

  @PrimaryKey
  @NonNull
  @Attribute
  private String id;

  @NonNull
  private Calendar time = Calendar.getInstance();


  @Element
  private String catId;

  @Element
  private String de;

  @Element
  private String mag;

  @Element
  private String ra;

  /**
   * Gets a calendar using the default time zone and locale. The * <code>Calendar</code> returned is
   * based on the current time * in the default time zone with the default
   *
   * @return time a Calendar.
   */
  @NonNull
  public Calendar getTime() {
    return time;
  }

  /**
   * Sets a calendar using the default time zone and locale. The * <code>Calendar</code> returned is
   * based on the current time * in the default time zone with the default
   *
   * @param time Calendar date and time.
   */
  public void setTime(@NonNull Calendar time) {
    this.time = time;
  }

  /**
   * Gets a String representation of the id of a Star entity.
   *
   * @return catId a star CatId.
   */
  public String getCatId() {
    return catId;
  }

  /**
   * Sets a String representation of the id of a Star entity.
   *
   * @param catId a star CatId.
   */
  public void setCatId(String catId) {
    this.catId = catId;
  }

  /**
   * Gets a String representation of the declination of a Star entity.
   *
   * @return de a star declination.
   */
  public String getDe() {
    return de;
  }

  /**
   * Sets a String representation of the declination of a Star entity.
   *
   * @param de a star declination.
   */
  public void setDe(String de) {
    this.de = de;
  }

  /**
   * Gets a String representation of the visual magnitude of a Star entity.
   *
   * @return mag a star visual magnitude.
   */
  public String getMag() {
    return mag;
  }

  /**
   * Sets a String representation of the magnitude of a Star entity.
   *
   * @param mag a star visual magnitude.
   */
  public void setMag(String mag) {
    this.mag = mag;
  }

  /**
   * Returns the primary key of this instance.
   *
   * @return primary key value.
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the primary key of this instance, and after loading an existing instance from the
   * database.
   *
   * @param id primary key value.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gets a String representation of the right ascension of a Star entity.
   *
   * @return ra a right ascension instance of a Star entity.
   */
  public String getRa() {
    return ra;
  }

  /**
   * Sets a String representation of the right ascension of a Star entity.
   *
   * @param ra a right ascension instance of a Star entity.
   */
  public void setRa(String ra) {
    this.ra = ra;
  }

  @Override
  public String toString() {
    return "ClassPojo [catId = " + catId + ", de = " + de + ", mag = " + mag + ", id = " + id
        + ", ra = " + ra + "]";
  }
}
