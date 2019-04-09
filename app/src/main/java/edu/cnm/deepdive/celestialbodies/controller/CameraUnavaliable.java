package edu.cnm.deepdive.celestialbodies.controller;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.cnm.deepdive.celestialbodies.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CameraUnavaliable extends Fragment {


  public CameraUnavaliable() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_camera_unavaliable, container, false);
  }

}
