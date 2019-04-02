package edu.cnm.deepdive.celestialbodies;


import android.app.Application;

public class CelestialApplication extends Application {

  private static CelestialApplication instance = null;

  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
  }

  public static CelestialApplication getInstance() {
    return instance;
  }


}
