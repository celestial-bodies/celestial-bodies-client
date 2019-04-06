package edu.cnm.deepdive.celestialbodies.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.celestialbodies.model.entity.User;
import java.util.List;

/**
 * DAO representing User entity class. Declares methods by which
 * data w
 */

@Dao
public interface UserDao {

  @Insert
  List<Long> insert(User... users);

  @Delete
  int delete(User...users);

  @Query("SELECT * FROM User")
  public List<User> findAll();


}
