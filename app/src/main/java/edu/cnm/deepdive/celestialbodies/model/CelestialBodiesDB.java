package edu.cnm.deepdive.celestialbodies.model;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import edu.cnm.deepdive.celestialbodies.CelestialApplication;
import edu.cnm.deepdive.celestialbodies.model.dao.StarDao;
import edu.cnm.deepdive.celestialbodies.model.dao.StarDisplayDao;
import edu.cnm.deepdive.celestialbodies.model.dao.UserDao;
import edu.cnm.deepdive.celestialbodies.model.entity.Star;
import edu.cnm.deepdive.celestialbodies.model.entity.StarDisplay;
import edu.cnm.deepdive.celestialbodies.model.entity.User;

@Database(entities = {StarDisplay.class, User.class, Star.class},
    version = 1,
    exportSchema = true)
public abstract class CelestialBodiesDB extends RoomDatabase {

  private static final String DB_NAME = "celestial_bodies_db";

  /**
   * Returns the single instance of {@zaryn927 ConsumptionDao} for the current application context.
   *
   * @return single {@zaryn927 ConsumptionDao} instance reference.
   */
  public synchronized static CelestialBodiesDB getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * Returns an instance of a Room-generated implementation of {@zaryn927 ConsumptionDao}.
   *
   * @return data access object for CRUD operations involving {@zaryn927 Consumption} instances.
   */
  public abstract StarDisplayDao getStarDisplayDao();

  public abstract StarDao getStarDao();

  public abstract UserDao getUserDao();

  private static class InstanceHolder {

    private static final CelestialBodiesDB INSTANCE = Room.databaseBuilder(
        CelestialApplication.getInstance().getApplicationContext(), CelestialBodiesDB.class,
        DB_NAME)
        .build();

  }
}
