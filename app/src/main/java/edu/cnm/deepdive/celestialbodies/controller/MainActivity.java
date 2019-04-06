package edu.cnm.deepdive.celestialbodies.controller;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.media.MediaRecorder.VideoSource.CAMERA;

import android.Manifest;
import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import edu.cnm.deepdive.celestialbodies.R;
import edu.cnm.deepdive.celestialbodies.service.FragmentService;
import edu.cnm.deepdive.celestialbodies.service.GoogleSignInService;


public class MainActivity extends AppCompatActivity implements CaptureFragment.OnFragmentInteraction {


  private static final int REQUEST_CAMERA = 0;
  private TextView mTextMessage;
  private boolean cameraPermission;

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      FragmentService fragmentService = FragmentService.getInstance();
      boolean handled = true;
      switch (item.getItemId()) {
        case R.id.navigation_dashboard:
          loadFragment(new DashboardFragment(), "DashboardFragment");
          break;
        case R.id.navigation_capture:
          loadFragment(new CaptureFragment(), "CaptureFragment");
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
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == CAMERA) {
      if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
        boolean needRationale =
            ActivityCompat.shouldShowRequestPermissionRationale(this, permission.CAMERA);
        // TODO Present rationale.
        cameraPermission = false;

      } else {
        cameraPermission = true;
      }
    }
  }

  @Override
  public void onCaptureClicked() {
    showCameraPreview();
  }

  private void checkPermissions() {
    if (ContextCompat.checkSelfPermission(this, permission.CAMERA)
        != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(this, new String[]{permission.CAMERA},
          REQUEST_CAMERA);
    } else {
      cameraPermission = true;
    }
  }


  private void showCameraPreview() {
    getSupportFragmentManager().beginTransaction()
        .add(R.id.bn_capture, CameraPreviewFragment.newInstance())
        .commit();
  }

  @SuppressLint("ClickableViewAccessibility")
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    findViewById(R.id.activity_main).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        for (Fragment f : manager.getFragments()) {
          transaction.remove(f);
        }

        transaction.commit();


      }
    });
    checkPermissions();
  }

  /**
   * <code>loadFragment</code> creates a {@link FragmentManager} to support
   */
  private void loadFragment(Fragment fragment, String tag) {
    FragmentManager manager;
    manager = getSupportFragmentManager();
    manager.beginTransaction()
        .add(R.id.fragment_container, fragment, tag)
        .commit();

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_options, menu);
    return true;
  }

  public boolean onOptionsItemSelected(MenuItem item) {
    boolean handled = true;
    switch (item.getItemId()) {
      case R.id.action_settings:
        getActionBar();
        break;
      case R.id.sign_out:
        signOut();
        break;
      default:
        handled = super.onOptionsItemSelected(item);
    }
    return handled;
  }

  private void signOut() {
    GoogleSignInService.getInstance().getClient()
        .signOut().addOnCompleteListener(this, (task) -> {
      GoogleSignInService.getInstance().setAccount(null);
      Intent intent = new Intent(this, LoginActivity.class);
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(intent);
    });
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
