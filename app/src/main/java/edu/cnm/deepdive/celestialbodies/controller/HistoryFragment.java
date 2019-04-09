package edu.cnm.deepdive.celestialbodies.controller;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import edu.cnm.deepdive.celestialbodies.R;
import edu.cnm.deepdive.celestialbodies.model.CelestialBodiesDB;
import edu.cnm.deepdive.celestialbodies.model.entity.Star;
import edu.cnm.deepdive.celestialbodies.view.HistoryAdapter;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * <code>HistoryFragment</code> displays the users previous star search that populate after
 * each successful completion of {@link CaptureFragment}. The user is able to scroll through each
 * query to see their past searches.
 * Future applications will allow the user to will allow the user to delete older scores or will
 * auto-delete.
 *
 * @author Kevin Simms &amp; Deep Dive Coding Java + Android Bootcamp cohort 6
 * @version 1.0
 */
public class HistoryFragment extends Fragment {

  private List<Star> starsList;
  private HistoryAdapter adapter;
  private Button cancelHistory;

  /**
   * <code>onCreateView</code> loads and displays the layout for {@link HistoryFragment} by
   * inflating the resource files of fragment_history, list_history, header and categories to
   * provide a {@link ListView} of the users history. Resource files header makes a stylized header.
   * Resource file categories displays the titles for the two columns, Date and Score.
   * In order for this to be successful this <code>onCreateView</code> uses access to
   * {@link HistoryAdapter} which provides a link from the database to the UI.
   *
   * @param inflater inflates xml files as a display
   * @param container contains access as group to the display files
   * @param savedInstanceState saves the current instance of the fragments
   * @return a stylized display for the user.
   */

  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,

      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_history, container, false);

    ListView listView = view.findViewById(R.id.list_history);
    ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header, listView,
        false);
    ViewGroup categories = (ViewGroup) inflater.inflate(R.layout.categories, listView,
        false);
    listView.addHeaderView(header, null, false);
    listView.addHeaderView(categories, null, false);

    starsList = new LinkedList<>();

    adapter = new HistoryAdapter(Objects.requireNonNull(getContext()), starsList);
    listView.setAdapter(adapter);
    cancelHistory = view.findViewById(R.id.cancel_history);
    cancelHistory.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

        Fragment fragment = getFragmentManager().findFragmentByTag("HistoryFragment");
        if (fragment != null) {
          getFragmentManager().beginTransaction().remove(fragment).commit();
        }

      }
    });
    new StarQueryTask().execute();
    return view;
  }

  public void createContextMenu(ContextMenu menu, int position, Star star) {
    getActivity().getMenuInflater().inflate(R.menu.main_options, menu);

  }
  /**
   * <code>StarQueryTask</code> takes a query of the local database and displays the scores in
   * descending order by date. The categories are provided by the entity classes {@link Star}
   * and {@link}. The query method is provided by.
   */
  @SuppressLint("StaticFieldLeak")
  private class StarQueryTask extends AsyncTask<Void, Void, List<Star>> {

    @Override
    protected void onPostExecute(List<Star> stars) {
      starsList.clear();
      starsList.addAll(stars);
      adapter.notifyDataSetChanged();
    }

    @Override
    protected List<Star> doInBackground(Void... voids) {
      return CelestialBodiesDB.getInstance().getStarDao().findAll();
    }


  }


}

