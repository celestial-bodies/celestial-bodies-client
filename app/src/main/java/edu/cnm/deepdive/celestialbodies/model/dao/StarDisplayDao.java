package edu.cnm.deepdive.celestialbodies.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.celestialbodies.model.entity.StarDisplay;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;

/**
 * Declares basic CRUD operations for {@link StarDisplay} instances in the local database, using
 * Room annotations.
 */
@Dao
public interface StarDisplayDao {

  /**
   * Inserts one or more {@link StarDisplay} instances into the local database. Any primary or
   * unique key constraint violations will result in the existing records being retained.
   *
   * @param displays {@link StarDisplay} instance(s) to be inserted.
   * @return inserted star ID(s).
   */
  @Insert
  List<Long> insert(StarDisplay... displays);

  /**
   * Deletes one or more {@link StarDisplay} instances from local database.
   *
   * @param displays instances of {@link StarDisplay} to be deleted from database.
   * @return number of records deleted.
   */
  @Delete
  int delete(StarDisplay... displays);

  /**
   * Selects and returns all {@link StarDisplay} instances in the local database, sorting the result in
   * descending id order.
   *
   * @return all {@link StarDisplay} instances in local database.
   */
  @Query("SELECT * FROM StarDisplay")
  List<StarDisplay> findAll();

}
