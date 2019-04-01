package edu.cnm.deepdive.celestialbodies.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.celestialbodies.model.entity.StarDisplay;
import java.util.List;

@Dao
public interface StarDisplayDao {

  @Insert
  List<StarDisplay> insert(StarDisplay... displays);

  @Delete
  List<StarDisplay> delete(StarDisplay...displays);

  @Query("SELECT * FROM StarDisplay")
  List<StarDisplay> findAll();
}
