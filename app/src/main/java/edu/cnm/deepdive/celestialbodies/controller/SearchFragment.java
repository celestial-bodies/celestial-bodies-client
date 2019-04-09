package edu.cnm.deepdive.celestialbodies.controller;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import edu.cnm.deepdive.celestialbodies.R;
import edu.cnm.deepdive.celestialbodies.service.DisplayWebService.GetFromWikiSkyTask;
import edu.cnm.deepdive.celestialbodies.service.DisplayWebService.StarResponse;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the
 * {@link SearchFragment.OnFragmentInteractionListener} interface to handle interaction events. Use
 */
public class SearchFragment extends Fragment {

  private TextView textSearch;
  private Button cancelSearch;

  private OnFragmentInteractionListener mListener;

  public SearchFragment() {
    // Required empty public constructor
  }



//  @Override
//  public void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    if (getArguments() != null) {
//      mParam1 = getArguments().getString(ARG_PARAM1);
//      mParam2 = getArguments().getString(ARG_PARAM2);
//    }
//  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
  View view = inflater.inflate(R.layout.fragment_search, container, false);
    cancelSearch = view.findViewById(R.id.cancel_search);
    cancelSearch.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

        Fragment fragment = getFragmentManager().findFragmentByTag("SearchFragment");
        if (fragment != null) {
          getFragmentManager().beginTransaction().remove(fragment).commit();
        }

      }
    });


    Button searchButton = view.findViewById(R.id.search_button);


    searchButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.search_dialog);


        // set the custom dialog components - text, image and button
        TextView textSearch = (TextView) dialog.findViewById(R.id.text_search);
        textSearch.setText("Constellation: Orion \n"
            + "\n"
            + " α Ori / 58 Ori\n"
            + "\n"
            + " HIP 27989 HR 2061 \n"
            + "\n"
            + " Variable: α Ori; Range 0.28 - 0.57\n"
            + "\n"
            + " Spectral Type: M2Ib\n"
            + "\n"
            + " Distance: 499 light years \n"
            + "\n"
            + " Magnitude: 0.45 \n"
            + "\n"
            + " Luminosity: 13427.6");
        //ImageView image = (ImageView) dialog.findViewById(R.id.image);
        // image.setImageResource(R.drawable.ic_launcher);

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) {
            dialog.dismiss();
          }
        });

        dialog.show();
      }
    });



  return view;

  }


  // TODO: Rename method, update argument and hook method into UI event
  public void onButtonPressed(Uri uri) {
    if (mListener != null) {
      mListener.onFragmentInteraction(uri);
    }

    //EXAMPLE
    new StarGetter().execute(/*Right Ascension, etc*/);


  }

  public class StarGetter extends GetFromWikiSkyTask{

    @Override
    protected void onPostExecute(StarResponse starResponse) {
      //Star[] stars = starResponse.getStar();
    }
  }






  /**
   * This interface must be implemented by activities that contain this fragment to allow an
   * interaction in this fragment to be communicated to the activity and potentially other fragments
   * contained in that activity.
   * <p>
   * See the Android Training lesson <a href= "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnFragmentInteractionListener {

    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }

//  public class StarDetailsTask extends AsyncTask<Long, Void, StarDetail> {
//
//    @Override
//    protected StarDetail doInBackground(Long... id) {
//      try {
//        StarDetail star = InstanceHolder.INSTANCE
//            .getStarByHdid(GoogleSignInService.getInstance().getAccount().getIdToken(), id[0])
//            .execute()
//            .body();
//        return star;
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//      return null;
//    }
//
//    @Override
//    protected void onPostExecute(StarDetail starDetail) {
//      textSearch.setText(starDetail.getComp() + " ");
//    }
//  }
}
