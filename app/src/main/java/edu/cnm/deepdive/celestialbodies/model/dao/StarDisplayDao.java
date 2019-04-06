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

@Dao
public interface StarDisplayDao {

  @Insert
  List<Long> insert(StarDisplay... displays);

  @Delete
  int delete(StarDisplay...displays);

  @Query("SELECT * FROM StarDisplay")
  List<StarDisplay> findAll();

//  @Query("SELECT * FROM StarDisplay WHERE time = :time")
//  StarDisplay findFirstByTime(Date time);
//
//  @Query("SELECT * FROM StarDisplay ORDER BY time DESC")
//  List<StarDisplay> findTime();
//
//  @Query("SELECT * FROM StarDisplay ORDER BY time DESC LIMIT 1")
//  StarDisplay findLast();


}
