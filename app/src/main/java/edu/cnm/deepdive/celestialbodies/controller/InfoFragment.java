package edu.cnm.deepdive.celestialbodies.controller;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import edu.cnm.deepdive.celestialbodies.R;
import edu.cnm.deepdive.celestialbodies.model.CelestialBodiesDB;
import edu.cnm.deepdive.celestialbodies.model.entity.Star;
import edu.cnm.deepdive.celestialbodies.model.entity.StarDetail;
import edu.cnm.deepdive.celestialbodies.model.entity.StarDisplay;
import edu.cnm.deepdive.celestialbodies.service.GoogleSignInService;
import edu.cnm.deepdive.celestialbodies.service.ServerWebService;
import edu.cnm.deepdive.celestialbodies.service.ServerWebService.InstanceHolder;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

  private List<StarDisplay> starList;
  private ArrayAdapter adapter;

  public InfoFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_info, container, false);


    ListView listView = view.findViewById(R.id.star_name_list);

    starList = new LinkedList<>();

    adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, starList);
    listView.setAdapter(adapter);

    //Create a listener for the listitems to get details
    listView.setOnItemClickListener((parent, view1, position, id) -> {
      //This is the star they clicked on
      StarDisplay clickedStar = starList.get(position);
      //Call the async task and show details in dialog
    });

    new StarQueryTask().execute();

        return view;
  }

  private class StarQueryTask extends AsyncTask<Void,Void,List<StarDisplay>> {

    @Override
    protected void onPostExecute(List<StarDisplay> starDisplays) {
      starList.clear();
      starList.addAll(starDisplays);
      adapter.notifyDataSetChanged();
    }

    @Override
    protected List<StarDisplay> doInBackground(Void... voids) {
      return CelestialBodiesDB.getInstance().getStarDisplayDao().findAll();
    }
  }

  //Modify this tast to retrieve details about one star
  public static class StarDetailsTask extends AsyncTask<String, Void, StarDetail>{

    @Override
    protected StarDetail doInBackground(String... strings) {
      try {
        List<StarDetail> stars = InstanceHolder.INSTANCE
            .getStars(GoogleSignInService.getInstance().getAccount().getIdToken()).execute().body();
        return stars.get(0);
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    }
  }

}
