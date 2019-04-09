package edu.cnm.deepdive.celestialbodies;


import android.app.Application;
import com.facebook.stetho.Stetho;

/**
 * Extends {@link Application}, in order to initialize <a href="http://facebook.github.io/stetho/">Stetho</a>
 * inspection and set up access to this instance via the singleton pattern. At runtime, any instance
 * of an {@link Application} subclass is a de facto singleton; the common {@link #getInstance()}
 * implementation is used here to enable access to the singleton by other classes in the app.
 */
public class CelestialApplication extends Application {

  private static CelestialApplication instance = null;

  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
    Stetho.initializeWithDefaults(this);
  }

  public static CelestialApplication getInstance() {
    return instance;
  }


}
