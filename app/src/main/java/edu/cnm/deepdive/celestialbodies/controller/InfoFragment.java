package edu.cnm.deepdive.celestialbodies.controller;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import edu.cnm.deepdive.celestialbodies.R;
import edu.cnm.deepdive.celestialbodies.model.CelestialBodiesDB;
import edu.cnm.deepdive.celestialbodies.model.entity.Star;
import edu.cnm.deepdive.celestialbodies.view.InfoAdapter;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

//import android.widget.ArrayAdapter;

/**
 * Populates a {@link android.webkit.WebView} with a list of detailed information about a star
 * chosen from a history list of stars which was formed when the user pressed the capture button,
 * see {@link CaptureFragment}. The star is chosen by clicking on the list of stars at which point
 * the server databae is queried for the relevant star's information.
 */
public class InfoFragment extends Fragment {

  private List<Star> starsList2;
  private InfoAdapter adapter2;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,

      @Nullable Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_info, container, false);

    ListView listView = view.findViewById(R.id.list_info);
    ViewGroup infoHeader = (ViewGroup) inflater.inflate(R.layout.info_header, listView,
        false);
    ViewGroup infoCategories = (ViewGroup) inflater.inflate(R.layout.info_categories, listView,
        false);
    listView.addHeaderView(infoHeader, null, false);
    listView.addHeaderView(infoCategories, null, false);

    starsList2 = new LinkedList<>();

    adapter2 = new InfoAdapter(Objects.requireNonNull(getContext()), starsList2);
    listView.setAdapter(adapter2);

//
//    adapter2 = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, starList2);
//    listView.setAdapter(adapter2);

    //Create a listener for the listitems to get details
    //listView.setOnItemClickListener((parent, view1, position, id) -> {
    //This is the star they clicked on
    //StarDisplay clickedStar = starList2.get(position);
    //Call the async task and show details in dialog
    //});

    new InfoQueryTask().execute();
    return view;
  }


  @SuppressLint("StaticFieldLeak")
  private class InfoQueryTask extends AsyncTask<Void, Void, List<Star>> {

    @Override
    protected void onPostExecute(List<Star> stars) {
      starsList2.clear();
      starsList2.addAll(stars);
      adapter2.notifyDataSetChanged();
    }

    @Override
    protected List<Star> doInBackground(Void... voids) {
      return CelestialBodiesDB.getInstance().getStarDao().findAll();
    }
  }

  //Modify this task to retrieve details about one star
//  public static class StarDetailsTask extends AsyncTask<String, Void, StarDetail>{
//
//    @Override
//    protected StarDetail doInBackground(String... strings) {
//      try {
//        List<StarDetail> stars = InstanceHolder.INSTANCE
//            .getStars(GoogleSignInService.getInstance().getAccount().getIdToken()).execute().body();
//        return stars.get(0);
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//      return null;
//    }
//  }

}
