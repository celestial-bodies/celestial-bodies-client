package edu.cnm.deepdive.celestialbodies.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.celestialbodies.model.entity.Star;
import edu.cnm.deepdive.celestialbodies.model.entity.StarDisplay;
import java.util.List;

@Dao
public interface StarDao {

  @Insert
  List<Long> insert(Star... displays);

  @Insert
  List<Long> insert(List<Star> stars);

  @Delete
  int delete(Star...displays);

  @Query("DELETE FROM Star")
  void deleteAll();

  @Query("SELECT * FROM Star")
  List<Star> findAll();

}
