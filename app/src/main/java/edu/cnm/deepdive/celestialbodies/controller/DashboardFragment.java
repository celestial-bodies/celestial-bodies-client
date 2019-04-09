package edu.cnm.deepdive.celestialbodies.controller;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import edu.cnm.deepdive.celestialbodies.R;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the
 * {@link DashboardFragment.OnFragmentInteractionListener} interface to handle interaction events. Use
 * factory method to create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {


  private Button cancelButton;

  private OnFragmentInteractionListener mListener;

  public DashboardFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment

    View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
    cancelButton = view.findViewById(R.id.cancel_button);
    cancelButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

        Fragment fragment = getFragmentManager().findFragmentByTag("DashboardFragment");
        if (fragment != null) {
          getFragmentManager().beginTransaction().remove(fragment).commit();
        }

      }
    });
    return view;
  }

  // TODO: Rename method, update argument and hook method into UI event
  public void onButtonPressed(Uri uri) {
    if (mListener != null) {
      mListener.onFragmentInteraction(uri);
    }
  }


  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
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
