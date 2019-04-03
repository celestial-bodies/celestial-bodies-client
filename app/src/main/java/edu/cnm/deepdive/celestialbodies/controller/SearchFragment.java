package edu.cnm.deepdive.celestialbodies.controller;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import edu.cnm.deepdive.celestialbodies.R;
import edu.cnm.deepdive.celestialbodies.service.DisplayWebService.GetFromWikiSkyTask;
import edu.cnm.deepdive.celestialbodies.service.DisplayWebService.Star;
import edu.cnm.deepdive.celestialbodies.service.DisplayWebService.StarResponse;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the
 * {@link SearchFragment.OnFragmentInteractionListener} interface to handle interaction events. Use
 */
public class SearchFragment extends Fragment {



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
    Button searchButton = view.findViewById(R.id.search_button);
    Button exitButton = view.findViewById(R.id.navigation_search);

    searchButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(getActivity(), "Search Started", Toast.LENGTH_LONG).show();

      }
    });

//    exitButton.setOnClickListener(new OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        MainActivity mainActivity = new MainActivity();
//        assert SearchFragment.this.getFragmentManager() != null;
//        SearchFragment.this.getFragmentManager().beginTransaction()
//            .replace(R.id.fragment_container,  )
//            .commit();
//      }
//    });


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
      Star[] stars = starResponse.getResponse().getStar();
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
}
