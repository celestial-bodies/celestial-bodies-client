package edu.cnm.deepdive.celestialbodies.model.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import java.util.Date;


@Entity(
    foreignKeys = @ForeignKey(
        entity = User.class,
        parentColumns = "user_id", childColumns = "user_id",
        onDelete = ForeignKey.CASCADE
    )
)

public class StarDisplay {
//do we need display, user_id, log
//need catalog_id, magnitude, constellation_id
  @ColumnInfo(name = "display_id", index = true) //star's name
  private long displayId;

  @ColumnInfo(name = "user_id", index = true)
  private long userId;

  @ColumnInfo(name = "time", index = true)
  private Date time;

  @ColumnInfo(name = "latitude", index = true)
  private float latitude;

  @ColumnInfo(name = "longitude", index = true)
  private float longitude;

  @ColumnInfo(name = "ascension", index = true)
  private float ascension;

  @ColumnInfo(name = "declination", index = true)
  private float declination;

  @ColumnInfo(name = "log", index = true)
  private String log;

  public long getDisplayId() {
    return displayId;
  }

  public void setDisplayId(long displayId) {
    this.displayId = displayId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public float getLatitude() {
    return latitude;
  }

  public void setLatitude(float latitude) {
    this.latitude = latitude;
  }

  public float getLongitude() {
    return longitude;
  }

  public void setLongitude(float longitude) {
    this.longitude = longitude;
  }

  public float getAscension() {
    return ascension;
  }

  public void setAscension(float ascension) {
    this.ascension = ascension;
  }

  public float getDeclination() {
    return declination;
  }

  public void setDeclination(float declination) {
    this.declination = declination;
  }

  public String getLog() {
    return log;
  }

  public void setLog(String log) {
    this.log = log;
  }
}