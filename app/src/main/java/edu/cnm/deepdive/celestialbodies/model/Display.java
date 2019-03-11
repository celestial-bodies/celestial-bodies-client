package edu.cnm.deepdive.celestialbodies.model;


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

public class Display {

  @ColumnInfo(name = "display_id", index = true)
  private long displayId;

  @ColumnInfo(name = "user_id", index = true)
  private long userId;

  @ColumnInfo(name = "date", index = true)
  private Date date;

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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
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