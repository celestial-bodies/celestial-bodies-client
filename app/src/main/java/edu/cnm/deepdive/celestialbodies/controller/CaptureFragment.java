package edu.cnm.deepdive.celestialbodies.controller;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import edu.cnm.deepdive.celestialbodies.R;
import edu.cnm.deepdive.celestialbodies.model.CelestialBodiesDB;
import edu.cnm.deepdive.celestialbodies.service.DisplayWebService.GetFromWikiSkyTask;
import edu.cnm.deepdive.celestialbodies.service.DisplayWebService.GetImageFromWikiSkyTask;
import edu.cnm.deepdive.celestialbodies.service.DisplayWebService.StarResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the
 * {@link CaptureFragment} interface to handle interaction events.
 *
 * fragment.
 */
public class CaptureFragment extends Fragment {

  private ImageView wikiImage;
  private Button captureButton;
  private Button infoButton;

  public CaptureFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    //Toast.makeText(getActivity(), "Captured Screen", Toast.LENGTH_SHORT).show();

    // Inflate the layout for this fragment
    View view = inflater
        .inflate(R.layout.fragment_capture, container, false);
    wikiImage = view.findViewById(R.id.wiki_image);

    captureButton = view.findViewById(R.id.bn_capture);
    captureButton.setOnClickListener(v -> {

      Map<String,String> imageMap = new HashMap<>();
      imageMap.put("ra", "30");
      imageMap.put("de", "25");
      imageMap.put("angle","40");
      imageMap.put("max_stars", "10");

      new GetImageTask().execute(imageMap);
      new GetStarInfoTask().execute(imageMap);

    });

    infoButton = view.findViewById(R.id.bn_info);
    infoButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        //Toast.makeText(getActivity(), "Info Clicked", Toast.LENGTH_SHORT).show();
        loadInfoFragment(new InfoFragment());
      }
    });

    return view;
  }

  class GetImageTask extends GetImageFromWikiSkyTask {

    @Override
    protected void onPostExecute(Bitmap bitmap) {
      wikiImage.setImageBitmap(bitmap);

    }
  }

  class GetStarInfoTask extends GetFromWikiSkyTask {

    @Override
    protected StarResponse doInBackground(Map<String, String>... params) {
      StarResponse starResponse = super.doInBackground(params);
      if (starResponse.getStar() != null) {
        CelestialBodiesDB.getInstance().getStarDao().insert(starResponse.getStar());
      }
      return null;
    }
  }

  private void loadInfoFragment(InfoFragment frag) {
    FragmentManager nextFrag;
    nextFrag = getFragmentManager();
    assert nextFrag != null;
    FragmentTransaction transaction = nextFrag.beginTransaction();
    transaction.add(R.id.fragment_container, frag);
    transaction.addToBackStack(null);
    transaction.commit();
  }
}
