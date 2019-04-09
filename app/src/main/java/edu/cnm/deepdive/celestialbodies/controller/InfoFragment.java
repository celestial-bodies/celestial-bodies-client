package edu.cnm.deepdive.celestialbodies.controller;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import edu.cnm.deepdive.celestialbodies.R;
import edu.cnm.deepdive.celestialbodies.model.CelestialBodiesDB;
import edu.cnm.deepdive.celestialbodies.model.entity.Star;
import edu.cnm.deepdive.celestialbodies.model.entity.StarDetail;
import edu.cnm.deepdive.celestialbodies.service.GoogleSignInService;
import edu.cnm.deepdive.celestialbodies.service.ServerWebService;
import edu.cnm.deepdive.celestialbodies.view.InfoAdapter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import retrofit2.Response;

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

  private TextView textSearch;
  private Button cancelInfo;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,

      @Nullable Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_info, container, false);

    ListView listView = view.findViewById(R.id.list_info);
    ViewGroup infoHeader = (ViewGroup) inflater.inflate(R.layout.info_header, listView,
        false);
    //ViewGroup infoCategories = (ViewGroup) inflater.inflate(R.layout.info_categories, listView, false);
    listView.addHeaderView(infoHeader, null, false);
    //listView.addHeaderView(infoCategories, null, false);
    cancelInfo = view.findViewById(R.id.cancel_button_info);
    cancelInfo.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

        getActivity().onBackPressed();


      }
    });
    starsList2 = new LinkedList<>();

    adapter2 = new InfoAdapter(Objects.requireNonNull(getContext()), starsList2);
    listView.setAdapter(adapter2);

//
    //adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, starList);
    //listView.setAdapter(adapter);

    //Create a listener for the listitems to get details
    listView.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view1, int position, long id) {
        //This is the star they clicked on
        Star clickedStar = starsList2.get(position);
        //Call the async task and show details in dialog
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.info_dialog);


        // set the custom dialog components - text, image and button
        textSearch = (TextView) dialog.findViewById(R.id.text_info);
        textSearch.setText("Request: HD23822\n"
            + "Status:0 \n"
            + "\n"
            + "Verbiage: OK \n"
            + "\n"
            + "Object ID= S413751 \n"
            + "\n"
            + "Type ID: 1 Star \n"
            + "\n"
            + "Name HD: 23822 \n"
            + "\n"
            + "CatID HD 23822\n"
            + "\n"
            + "Constellation ID: 78, Taurus \n"
            + "\n"
            + "RA unit (Hour): 3.8158168 \n"
            + "\n"
            + "De unit (Degree): 23.857127\n"
            + "\n"
            + "Mag: 6.473");



        Button dialogButton = (Button) dialog.findViewById(R.id.infoButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) {

            dialog.dismiss();
          }
        });

        //new StarDetailsTask().execute(225043l);
        //new StarQueryTask().execute();
        dialog.show();
      }
    });


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

  public class StarDetailsTask extends AsyncTask<Long, Void, StarDetail> {

    @Override
    protected StarDetail doInBackground(Long... id) {
      try {
        Response<StarDetail> starDetailResponse = ServerWebService.getINSTANCE()
            .getStarByHdid(getString(R.string.auth_header,
                GoogleSignInService.getInstance().getAccount().getIdToken()), id[0])
            .execute();
        StarDetail star = starDetailResponse
            .body();
        return star;
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    }

    @Override
    protected void onPostExecute(StarDetail starDetail) {
      textSearch.setText(starDetail.getComp() + " ");
    }
  }


}
