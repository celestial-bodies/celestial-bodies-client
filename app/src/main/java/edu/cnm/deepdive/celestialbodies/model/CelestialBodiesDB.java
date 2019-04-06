package edu.cnm.deepdive.celestialbodies.model;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.Nullable;
import edu.cnm.deepdive.celestialbodies.CelestialApplication;
import edu.cnm.deepdive.celestialbodies.model.CelestialBodiesDB.Converters;
import edu.cnm.deepdive.celestialbodies.model.dao.StarDao;
import edu.cnm.deepdive.celestialbodies.model.dao.StarDisplayDao;
import edu.cnm.deepdive.celestialbodies.model.dao.UserDao;
import edu.cnm.deepdive.celestialbodies.model.entity.Star;
import edu.cnm.deepdive.celestialbodies.model.entity.StarDisplay;
import edu.cnm.deepdive.celestialbodies.model.entity.User;
import java.util.Calendar;

@Database(entities = {StarDisplay.class, User.class, Star.class},
    version = 1,
    exportSchema = true)

@TypeConverters(Converters.class)
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

  public static class Converters {


    /**
     * Converts a Long value containing the number of milliseconds since the start of the Unix epoch
     * (1970-01-01 00:00:00.000 UTC) to an instance of {@link Calendar}, and returns the latter.
     *
     * @param milliseconds date-time as a number of milliseconds since the start of the Unix epoch.
     * @return date-time as a {@link Calendar} instance.
     */

    @Nullable
    @TypeConverter
    public static Calendar calendarFromLong(@Nullable Long milliseconds) {
      if (milliseconds != null) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        return calendar;
      }
      return null;
    }

    /**
     * Converts a {@link Calendar} date-time value number of milliseconds since the start of the
     * Unix epoch (1970-01-01 00:00:00.000 UTC), and returns the latter.
     *
     * @param calendar date-time as a {@link Calendar} instance.
     * @return date-time as a number of milliseconds since the start of the Unix epoch.
     */
    @Nullable
    @TypeConverter
    public static Long longFromCalendar(@Nullable Calendar calendar) {
      return (calendar != null) ? calendar.getTimeInMillis() : null;
    }
  }
}
