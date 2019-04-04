package edu.cnm.deepdive.celestialbodies.controller;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import edu.cnm.deepdive.celestialbodies.R;
import edu.cnm.deepdive.celestialbodies.model.CelestialBodiesDB;
import edu.cnm.deepdive.celestialbodies.model.entity.StarDisplay;
import java.util.LinkedList;
import java.util.List;

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

}
