package edu.cnm.deepdive.celestialbodies.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.celestialbodies.model.entity.Star;
import java.util.List;

/**
 * Declares basic CRUD operations for {@link Star} instances in the local database, using Room
 * annotations.
 */
@Dao
public interface StarDao {

  /**
   * For inserting multiple {@link Star} instances into the local database. Any primary or unique
   * key constraint violations will result in the existing * records being retained.
   *
   * @param stars {@link Star} instances(s)
   * @return inserted stars.
   */
  @Insert
  List<Long> insert(List<Star> stars);

  /**
   * Deletes one or more {@link Star} instances from local database.
   *
   * @param displays instances of {@link Star} to be deleted from database.
   * @return number of records deleted.
   */
  @Delete
  int delete(Star... displays);

  /**
   * Selects and returns all {@link Star} instances in the local database, sorting the result in
   * ascending id order.
   *
   * @return all {@link Star} instances in local database.
   */
  @Query("SELECT * FROM Star")
  List<Star> findAll();

  /**
   * Deletes all instances of {@link Star} from the local database.
   */
  @Query("DELETE FROM Star")
  void deleteAll();

}
