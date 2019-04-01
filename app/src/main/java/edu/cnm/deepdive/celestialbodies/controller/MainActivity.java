package edu.cnm.deepdive.celestialbodies.controller;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import edu.cnm.deepdive.celestialbodies.R;
import edu.cnm.deepdive.celestialbodies.service.FragmentService;

public class MainActivity extends AppCompatActivity implements SensorEventListener  {

  private SensorManager sensorManager;
  private Sensor sensor;
  private TextView textView;
  private ImageView image;
  private SearchFragment searchFragment;
//  private InfoFragment infoFragment;
//  private DashboardFragment dashboardFragment;

  private TextView mTextMessage;

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      FragmentService fragmentService = FragmentService.getInstance();
      boolean handled = true;
      switch (item.getItemId()) {
        case R.id.navigation_info:
          loadFragment(new InfoFragment(), "InfoFragment");
          break;
        case R.id.navigation_dashboard:
          loadFragment(new DashboardFragment(), "DashboardFragment");
          break;
        case R.id.navigation_search:
          loadFragment(new SearchFragment(), "SearchFragment");
          break;
        default:
          handled = false;
      }
      return handled;
    }
  };



  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sensor);


    //declaring Sensor Manager and sensor type
    sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    //locate views
    textView = (TextView) findViewById(R.id.txt);
   image = (ImageView) findViewById(R.id.img);

    mTextMessage = (TextView) findViewById(R.id.message);
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

  }

  @Override
  public void onAccuracyChanged(Sensor arg0, int arg1) {
  }

  @Override
  public void onSensorChanged(SensorEvent event) {
    float x = event.values[0];
    float y = event.values[1];
    if (Math.abs(x) > Math.abs(y)) {
      if (x < 0) {
        image.setImageResource(R.drawable.right);
        textView.setText("You tilt the device right");
      }
      if (x > 0) {
        image.setImageResource(R.drawable.left);
        textView.setText("You tilt the device left");
      }
    } else {
      if (y < 0) {
        image.setImageResource(R.drawable.up);
        textView.setText("You tilt the device up");
      }
      if (y > 0) {
        image.setImageResource(R.drawable.down);
        textView.setText("You tilt the device down");
      }
    }
    if (x > (-2) && x < (2) && y > (-2) && y < (2)) {
      image.setImageResource(R.drawable.center);
      textView.setText("Not tilt device");
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
  }

  @Override
  protected void onPause() {
    super.onPause();
    //unregister Sensor listener
    sensorManager.unregisterListener(this);
  }

  /**
   * <code>loadFragment</code> creates a {@link FragmentManager} to support
   * @param fragment
   * @param tag
   */
  private void loadFragment(Fragment fragment, String tag) {
    FragmentManager manager;
    manager = getSupportFragmentManager();
    manager.beginTransaction()
        .add(R.id.fragment_container, fragment, tag)
        .commit();

  }
}



//
//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_main);
//

//
//
//  }

//}
