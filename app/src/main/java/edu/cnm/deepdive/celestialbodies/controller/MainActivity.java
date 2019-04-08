package edu.cnm.deepdive.celestialbodies.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import edu.cnm.deepdive.celestialbodies.R;
import edu.cnm.deepdive.celestialbodies.service.FragmentService;
import edu.cnm.deepdive.celestialbodies.service.GoogleSignInService;

/**
 * Primary controller class of the Celestial Bodies client app.  This activity configures and then
 * responds to clicks in a {@link BottomNavigationView} to hide and show one of 3 main {@link
 * android.support.v4.app.Fragment} instances. This activiy also responds to clicks on a single
 * options {@link MenuItem} to display a {@link android.widget.ListView} of star items captured.
 */
public class MainActivity extends AppCompatActivity {

  private TextView mTextMessage;

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


  @SuppressLint("ClickableViewAccessibility")
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    BottomNavigationView navigation = findViewById(R.id.navigation);
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

    //TODO remove this!
    //Shortcut to retrieve stars from server
    //new StarDetailsTask().execute();
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

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_options, menu);
    return true;
  }

  public boolean onOptionsItemSelected(MenuItem item) {
    boolean handled = true;
    switch (item.getItemId()) {
      case R.id.action_history:
        loadFragment(new HistoryFragment(), "HistoryFragment");
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

