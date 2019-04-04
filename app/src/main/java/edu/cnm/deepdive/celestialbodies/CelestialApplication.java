package edu.cnm.deepdive.celestialbodies;


import android.app.Application;
import com.facebook.stetho.Stetho;

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
