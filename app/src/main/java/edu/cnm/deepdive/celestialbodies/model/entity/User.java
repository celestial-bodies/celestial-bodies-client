package edu.cnm.deepdive.celestialbodies.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(indices = @Index(value = "user_id", unique = true))

public class User {


  @ColumnInfo(name = "user_id")
  @PrimaryKey(autoGenerate = true)
  private long userId;

  @ColumnInfo(name = "name", index = true)
  private String name;

  @ColumnInfo(name = "favorites",index = true)
  private String favorites;

  @ColumnInfo(name = "city", index = true)
  private String city;

  @ColumnInfo(name = "zip_code", index = true)
  private long zipCode;

  @ColumnInfo(name = "email", index = true)
  private String email;

  public long getId() {
    return userId;
  }

  public void setId(long id) {
    this.userId = id;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFavorites() {
    return favorites;
  }

  public void setFavorites(String favorites) {
    this.favorites = favorites;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public long getZipCode() {
    return zipCode;
  }

  public void setZipCode(long zipCode) {
    this.zipCode = zipCode;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
