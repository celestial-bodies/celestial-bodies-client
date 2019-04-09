package edu.cnm.deepdive.celestialbodies.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.celestialbodies.model.entity.User;
import java.util.List;

/**
 * Declares basic CRUD operations for {@link User} instances in the local database, using Room
 * annotations.
 */
@Dao
public interface UserDao {

  /**
   * Inserts one or more {@link User} instances into the local database. Any primary or unique key
   * constraint violations will result in the existing records being retained.
   *
   * @param users {@link User} instance(s) to be inserted.
   * @return inserted record ID(s).
   */
  @Insert
  List<Long> insert(User... users);

  /**
   * Deletes one or more {@link User} instances from local database.
   *
   * @param users instances of {@link User} to be deleted from database.
   * @return number of records deleted.
   */
  @Delete
  int delete(User... users);

  /**
   * Selects and returns all {@link User} instances in the local database, sorting the result in
   * ascending id order.
   *
   * @return all {@link User} instances in local database.
   */
  @Query("SELECT * FROM User")
  List<User> findAll();


}
