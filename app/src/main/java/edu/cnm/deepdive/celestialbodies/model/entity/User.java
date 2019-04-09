package edu.cnm.deepdive.celestialbodies.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import java.util.Calendar;

/**
 * Encapsulates the desired information to be collected regarding a user of the star app.  Room and
 * GSon annotations are used to specify entity &amp; attribute mapping for database persistence,
 * and property mapping for JSON serialization/deserialization mapping.
 */
@Entity(indices = @Index(value = "user_id", unique = true))
public class User {

  @ColumnInfo(name = "user_id")
  @PrimaryKey(autoGenerate = true)
  private long userId;

  @ColumnInfo(name = "name", index = true)
  private String name;

  @ColumnInfo(name = "favorites", index = true)
  private String favorites;

  @ColumnInfo(name = "city", index = true)
  private String city;

  @ColumnInfo(name = "email", index = true)
  private String email;

  private Calendar timestamp = Calendar.getInstance();

  /**
   * Returns the autogenerated primary key of this instance.
   *
   * @return primary key value.
   */
  public long getUserId() {
    return userId;
  }

  /**
   * Sets the primary key of this instance. This method is invoked by Room to set the autogenerated
   * value of a new instance, and when loading an existing instance from the database.
   *
   * @param id primary key value.
   */
  public void setUserId(long id) {
    this.userId = id;
  }

  /**
   * Returns the name of the user using the app.
   *
   * @return name String value of user name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the String value of name of user.
   *
   * @param name String value name of user
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns String values of stars clicked on by the user.
   *
   * @return favorites favorite stars viewed by user.
   */
  public String getFavorites() {
    return favorites;
  }

  /**
   * Sets String values of favorite stars viewed by the user.
   *
   * @param favorites favorite stars viewed by the user.
   */
  public void setFavorites(String favorites) {
    this.favorites = favorites;
  }

  /**
   * Returns nearest city location that user viewed a star
   *
   * @return city
   */
  public String getCity() {
    return city;
  }

  /**
   * Sets the city in which user viewed the star.
   *
   * @param city user's city
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Returns user's email
   *
   * @return email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the user's email.
   *
   * @param email user's email.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets a timestamp using the default time zone and locale. The * <code>Calendar</code> returned
   * is based on the current time in the default time zone.
   *
   * @return timestamp of type Calendar.
   */
  public Calendar getTimestamp() {
    return timestamp;
  }

  /**
   * Sets timestamp
   *
   * @param timestamp timestamp of type Calendar.
   */
  public void setTimestamp(Calendar timestamp) {
    this.timestamp = timestamp;
  }

}
