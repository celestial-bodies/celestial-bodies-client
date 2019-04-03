package edu.cnm.deepdive.celestialbodies.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.celestialbodies.model.entity.User;
import java.util.List;

@Dao
public interface UserDao {

  @Insert
  List<User> insert(User... users);

  @Delete
  List<User> delete(User...users);

  @Query("SELECT * FROM User")
  public List<User> findAll();


}