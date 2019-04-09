package edu.cnm.deepdive.celestialbodies.controller;

import static android.content.Context.SENSOR_SERVICE;

import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import edu.cnm.deepdive.celestialbodies.R;
import edu.cnm.deepdive.celestialbodies.model.CelestialBodiesDB;
import edu.cnm.deepdive.celestialbodies.model.entity.Star;
import edu.cnm.deepdive.celestialbodies.service.DisplayWebService.GetFromWikiSkyTask;
import edu.cnm.deepdive.celestialbodies.service.DisplayWebService.GetImageFromWikiSkyTask;
import edu.cnm.deepdive.celestialbodies.service.DisplayWebService.StarResponse;
import edu.cnm.deepdive.celestialbodies.units.GeocentricCoordinates;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the
 * {@link CaptureFragment} interface to handle interaction events. Populates a {@link
 * android.webkit.WebView} with the image of the {@link Star} instance for the for the selected
 * portion of the sky retrieved from the WikiSky web service.
 */
public class CaptureFragment extends Fragment implements SensorEventListener {


  private SensorManager mSensorManager = null;

  // angular speeds from gyro
  private float[] gyro = new float[3];

  // rotation matrix from gyro data
  private float[] gyroMatrix = new float[9];

  // orientation angles from gyro matrix
  private float[] gyroOrientation = new float[3];

  // magnetic field vector
  private float[] magnet = new float[3];

  // accelerometer vector
  private float[] accel = new float[3];

  // orientation angles from accel and magnet
  private float[] accMagOrientation = new float[3];

  // final orientation angles from sensor fusion
  private float[] fusedOrientation = new float[3];
  private double x;
  private double y;
  private double z;

  // accelerometer and magnetometer based rotation matrix
  private float[] rotationMatrix = new float[9];

  private static final float EPSILON = 0.000000001f;
  private static final float NS2S = 1.0f / 1000000000.0f;
  private float timestamp;
  private boolean initState = true;

  private static final int TIME_CONSTANT = 30;
  private static final float FILTER_COEFFICIENT = 0.98f;
  private Timer fuseTimer = new Timer();

  private ImageView wikiImage;
  private Button captureButton;
  private Button infoButton;

  private float dec;
  private float ra;

  DecimalFormat d = new DecimalFormat("#.##");
  private Handler mHandler;
  private RadioGroup mRadioGroup;
  private TextView mAzimuthView;
  private TextView mPitchView;
  private TextView mRollView;
  private int radioSelection = 2;
  private Runnable updateOrientationDisplayTask = new Runnable() {
    public void run() {
      updateOrientationDisplay();
    }
  };

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    //Toast.makeText(getActivity(), "Captured Screen", Toast.LENGTH_SHORT).show();

    // Inflate the layout for this fragment
    View view = inflater
        .inflate(R.layout.fragment_capture, container, false);
    wikiImage = view.findViewById(R.id.wiki_image);

    gyroOrientation[0] = 0.0f;
    gyroOrientation[1] = 0.0f;
    gyroOrientation[2] = 0.0f;

    // initialise gyroMatrix with identity matrix
    gyroMatrix[0] = 1.0f;
    gyroMatrix[1] = 0.0f;
    gyroMatrix[2] = 0.0f;
    gyroMatrix[3] = 0.0f;
    gyroMatrix[4] = 1.0f;
    gyroMatrix[5] = 0.0f;
    gyroMatrix[6] = 0.0f;
    gyroMatrix[7] = 0.0f;
    gyroMatrix[8] = 1.0f;

    // get sensorManager and initialise sensor listeners
    mSensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
    initListeners();

    // wait for one second until gyroscope and magnetometer/accelerometer
    // data is initialised then scedule the complementary filter task
    fuseTimer.scheduleAtFixedRate(new calculateFusedOrientationTask(),
        1000, TIME_CONSTANT);

    Button captureButton = view.findViewById(R.id.bn_capture);
    captureButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

        new ClearStarTableTask().execute();

      //      AstronomerModelImpl getDir = new AstronomerModelImpl(magneticDeclinationCalculator);

      //      GeocentricCoordinates geocenCoord = new GeocentricCoordinates(
      //          (float) (getX()), (float) getY(),
      //          (float) getZ());

      GeocentricCoordinates geocenCoord = new GeocentricCoordinates(-rotationMatrix[2],
          -rotationMatrix[5], -rotationMatrix[8]);

      dec = geocenCoord.getDec();
      ra = geocenCoord.getRa();
      String decstring = "";
      decstring += dec;
      String raString = "";
      raString += ra;

      Map<String, String> imageMap = new HashMap<>();
      imageMap.put("ra", raString);
      imageMap.put("dec", decstring);
      imageMap.put("angle", "45");
      imageMap.put("max_stars", "25");

      new GetImageTask().execute(imageMap);
      new GetStarInfoTask().execute(imageMap);

      }
    });

    infoButton = view.findViewById(R.id.bn_info);
    infoButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        //Toast.makeText(getActivity(), "Info Clicked", Toast.LENGTH_SHORT).show();
        loadInfoFragment(new InfoFragment());
      }
    });
    mAzimuthView = view.findViewById(R.id.textView4);
    mPitchView = view.findViewById(R.id.textView5);
    mRollView = view.findViewById(R.id.textView6);


    return view;
  }

  @Override
  public void onStop() {
    super.onStop();
    // unregister sensor listeners to prevent the activity from draining the device's battery.
    mSensorManager.unregisterListener(this);
  }

  @Override
  public void onPause() {
    super.onPause();
    // unregister sensor listeners to prevent the activity from draining the device's battery.
    mSensorManager.unregisterListener(this);
  }

  @Override
  public void onResume() {
    super.onResume();
    // restore the sensor listeners when user resumes the application.
    initListeners();
  }

  // This function registers sensor listeners for the accelerometer, magnetometer and gyroscope.
  private void initListeners() {
    mSensorManager.registerListener(this,
        mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
        SensorManager.SENSOR_DELAY_FASTEST);

    mSensorManager.registerListener(this,
        mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
        SensorManager.SENSOR_DELAY_FASTEST);

    mSensorManager.registerListener(this,
        mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
        SensorManager.SENSOR_DELAY_FASTEST);
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) {
  }

  // calculates orientation angles from accelerometer and magnetometer output
  private void calculateAccMagOrientation() {
    if (SensorManager.getRotationMatrix(rotationMatrix, null, accel, magnet)) {
      SensorManager.getOrientation(rotationMatrix, accMagOrientation);
      x = (fusedOrientation[0] * 180 / Math.PI);
      y = fusedOrientation[1] * 180 / Math.PI;
      z = fusedOrientation[2] * 180 / Math.PI;
    }
  }

  // This function is borrowed from the Android reference
  // at http://developer.android.com/reference/android/hardware/SensorEvent.html#values
  // It calculates a rotation vector from the gyroscope angular speed values.
  private void getRotationVectorFromGyro(float[] gyroValues,
      float[] deltaRotationVector,
      float timeFactor) {
    float[] normValues = new float[3];

    // Calculate the angular speed of the sample
    float omegaMagnitude =
        (float) Math.sqrt(gyroValues[0] * gyroValues[0] +
            gyroValues[1] * gyroValues[1] +
            gyroValues[2] * gyroValues[2]);

    // Normalize the rotation vector if it's big enough to get the axis
    if (omegaMagnitude > EPSILON) {
      normValues[0] = gyroValues[0] / omegaMagnitude;
      normValues[1] = gyroValues[1] / omegaMagnitude;
      normValues[2] = gyroValues[2] / omegaMagnitude;
    }

    // Integrate around this axis with the angular speed by the timestep
    // in order to get a delta rotation from this sample over the timestep
    // We will convert this axis-angle representation of the delta rotation
    // into a quaternion before turning it into the rotation matrix.
    float thetaOverTwo = omegaMagnitude * timeFactor;
    float sinThetaOverTwo = (float) Math.sin(thetaOverTwo);
    float cosThetaOverTwo = (float) Math.cos(thetaOverTwo);
    deltaRotationVector[0] = sinThetaOverTwo * normValues[0];
    deltaRotationVector[1] = sinThetaOverTwo * normValues[1];
    deltaRotationVector[2] = sinThetaOverTwo * normValues[2];
    deltaRotationVector[3] = cosThetaOverTwo;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public double getZ() {
    return z;
  }

  // This function performs the integration of the gyroscope data.
  // It writes the gyroscope based orientation into gyroOrientation.
  public void gyroFunction(SensorEvent event) {
    // don't start until first accelerometer/magnetometer orientation has been acquired
    if (accMagOrientation == null) {
      return;
    }

    // initialisation of the gyroscope based rotation matrix
    if (initState) {
      float[] initMatrix = new float[9];
      initMatrix = getRotationMatrixFromOrientation(accMagOrientation);
      float[] test = new float[3];
      SensorManager.getOrientation(initMatrix, test);
      gyroMatrix = matrixMultiplication(gyroMatrix, initMatrix);
      initState = false;
    }

    // copy the new gyro values into the gyro array
    // convert the raw gyro data into a rotation vector
    float[] deltaVector = new float[4];
    if (timestamp != 0) {
      final float dT = (event.timestamp - timestamp) * NS2S;
      System.arraycopy(event.values, 0, gyro, 0, 3);
      getRotationVectorFromGyro(gyro, deltaVector, dT / 2.0f);
    }

    // measurement done, save current time for next interval
    timestamp = event.timestamp;

    // convert rotation vector into rotation matrix
    float[] deltaMatrix = new float[9];
    SensorManager.getRotationMatrixFromVector(deltaMatrix, deltaVector);

    // apply the new rotation interval on the gyroscope based rotation matrix
    gyroMatrix = matrixMultiplication(gyroMatrix, deltaMatrix);

    // get the gyroscope based orientation from the rotation matrix
    SensorManager.getOrientation(gyroMatrix, gyroOrientation);
  }

  private float[] getRotationMatrixFromOrientation(float[] o) {
    float[] xM = new float[9];
    float[] yM = new float[9];
    float[] zM = new float[9];

    float sinX = (float) Math.sin(o[1]);
    float cosX = (float) Math.cos(o[1]);
    float sinY = (float) Math.sin(o[2]);
    float cosY = (float) Math.cos(o[2]);
    float sinZ = (float) Math.sin(o[0]);
    float cosZ = (float) Math.cos(o[0]);

    // rotation about x-axis (pitch)
    xM[0] = 1.0f;
    xM[1] = 0.0f;
    xM[2] = 0.0f;
    xM[3] = 0.0f;
    xM[4] = cosX;
    xM[5] = sinX;
    xM[6] = 0.0f;
    xM[7] = -sinX;
    xM[8] = cosX;

    // rotation about y-axis (roll)
    yM[0] = cosY;
    yM[1] = 0.0f;
    yM[2] = sinY;
    yM[3] = 0.0f;
    yM[4] = 1.0f;
    yM[5] = 0.0f;
    yM[6] = -sinY;
    yM[7] = 0.0f;
    yM[8] = cosY;

    // rotation about z-axis (azimuth)
    zM[0] = cosZ;
    zM[1] = sinZ;
    zM[2] = 0.0f;
    zM[3] = -sinZ;
    zM[4] = cosZ;
    zM[5] = 0.0f;
    zM[6] = 0.0f;
    zM[7] = 0.0f;
    zM[8] = 1.0f;

    // rotation order is y, x, z (roll, pitch, azimuth)
    float[] resultMatrix = matrixMultiplication(xM, yM);
    resultMatrix = matrixMultiplication(zM, resultMatrix);
    return resultMatrix;
  }

  private float[] matrixMultiplication(float[] A, float[] B) {
    float[] result = new float[9];

    result[0] = A[0] * B[0] + A[1] * B[3] + A[2] * B[6];
    result[1] = A[0] * B[1] + A[1] * B[4] + A[2] * B[7];
    result[2] = A[0] * B[2] + A[1] * B[5] + A[2] * B[8];

    result[3] = A[3] * B[0] + A[4] * B[3] + A[5] * B[6];
    result[4] = A[3] * B[1] + A[4] * B[4] + A[5] * B[7];
    result[5] = A[3] * B[2] + A[4] * B[5] + A[5] * B[8];

    result[6] = A[6] * B[0] + A[7] * B[3] + A[8] * B[6];
    result[7] = A[6] * B[1] + A[7] * B[4] + A[8] * B[7];
    result[8] = A[6] * B[2] + A[7] * B[5] + A[8] * B[8];

    return result;
  }

  class calculateFusedOrientationTask extends TimerTask {

    public void run() {
      float oneMinusCoeff = 1.0f - FILTER_COEFFICIENT;

      /*
       * Fix for 179� <--> -179� transition problem:
       * Check whether one of the two orientation angles (gyro or accMag) is negative while the other one is positive.
       * If so, add 360� (2 * math.PI) to the negative value, perform the sensor fusion, and remove the 360� from the result
       * if it is greater than 180�. This stabilizes the output in positive-to-negative-transition cases.
       */

      // azimuth
      if (gyroOrientation[0] < -0.5 * Math.PI && accMagOrientation[0] > 0.0) {
        fusedOrientation[0] = (float) (FILTER_COEFFICIENT * (gyroOrientation[0] + 2.0 * Math.PI)
            + oneMinusCoeff * accMagOrientation[0]);
        fusedOrientation[0] -= (fusedOrientation[0] > Math.PI) ? 2.0 * Math.PI : 0;
      } else if (accMagOrientation[0] < -0.5 * Math.PI && gyroOrientation[0] > 0.0) {
        fusedOrientation[0] = (float) (FILTER_COEFFICIENT * gyroOrientation[0] + oneMinusCoeff * (
            accMagOrientation[0] + 2.0 * Math.PI));
        fusedOrientation[0] -= (fusedOrientation[0] > Math.PI) ? 2.0 * Math.PI : 0;
      } else {
        fusedOrientation[0] =
            FILTER_COEFFICIENT * gyroOrientation[0] + oneMinusCoeff * accMagOrientation[0];
      }

      // pitch
      if (gyroOrientation[1] < -0.5 * Math.PI && accMagOrientation[1] > 0.0) {
        fusedOrientation[1] = (float) (FILTER_COEFFICIENT * (gyroOrientation[1] + 2.0 * Math.PI)
            + oneMinusCoeff * accMagOrientation[1]);
        fusedOrientation[1] -= (fusedOrientation[1] > Math.PI) ? 2.0 * Math.PI : 0;
      } else if (accMagOrientation[1] < -0.5 * Math.PI && gyroOrientation[1] > 0.0) {
        fusedOrientation[1] = (float) (FILTER_COEFFICIENT * gyroOrientation[1] + oneMinusCoeff * (
            accMagOrientation[1] + 2.0 * Math.PI));
        fusedOrientation[1] -= (fusedOrientation[1] > Math.PI) ? 2.0 * Math.PI : 0;
      } else {
        fusedOrientation[1] =
            FILTER_COEFFICIENT * gyroOrientation[1] + oneMinusCoeff * accMagOrientation[1];
      }

      // roll
      if (gyroOrientation[2] < -0.5 * Math.PI && accMagOrientation[2] > 0.0) {
        fusedOrientation[2] = (float) (FILTER_COEFFICIENT * (gyroOrientation[2] + 2.0 * Math.PI)
            + oneMinusCoeff * accMagOrientation[2]);
        fusedOrientation[2] -= (fusedOrientation[2] > Math.PI) ? 2.0 * Math.PI : 0;
      } else if (accMagOrientation[2] < -0.5 * Math.PI && gyroOrientation[2] > 0.0) {
        fusedOrientation[2] = (float) (FILTER_COEFFICIENT * gyroOrientation[2] + oneMinusCoeff * (
            accMagOrientation[2] + 2.0 * Math.PI));
        fusedOrientation[2] -= (fusedOrientation[2] > Math.PI) ? 2.0 * Math.PI : 0;
      } else {
        fusedOrientation[2] =
            FILTER_COEFFICIENT * gyroOrientation[2] + oneMinusCoeff * accMagOrientation[2];
      }

      // overwrite gyro matrix and orientation with fused orientation
      // to comensate gyro drift
      gyroMatrix = getRotationMatrixFromOrientation(fusedOrientation);
      System.arraycopy(fusedOrientation, 0, gyroOrientation, 0, 3);
    }
  }


  class GetImageTask extends GetImageFromWikiSkyTask {

    @Override
    protected void onPostExecute(Bitmap bitmap) {
      wikiImage.setImageBitmap(bitmap);
    }
  }

  class GetStarInfoTask extends GetFromWikiSkyTask {

    @Override
    @SafeVarargs
    protected final StarResponse doInBackground(Map<String, String>... params) {
      StarResponse starResponse = super.doInBackground(params);
      if (starResponse.getStar() != null) {
        CelestialBodiesDB.getInstance().getStarDao().insert(starResponse.getStar());
      }
      return null;
    }
  }

  static class ClearStarTableTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
      CelestialBodiesDB.getInstance().getStarDao().deleteAll();
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

  @Override
  public void onSensorChanged(SensorEvent event) {
    switch (event.sensor.getType()) {
      case Sensor.TYPE_ACCELEROMETER:
        // copy new accelerometer data into accel array and calculate orientation
        System.arraycopy(event.values, 0, accel, 0, 3);
        calculateAccMagOrientation();
        break;

      case Sensor.TYPE_GYROSCOPE:
        // process gyro data
        gyroFunction(event);
        break;

      case Sensor.TYPE_MAGNETIC_FIELD:
        // copy new magnetometer data into magnet array
        System.arraycopy(event.values, 0, magnet, 0, 3);
        break;
    }
    updateOrientationDisplay();
  }

  public void onCheckedChanged(RadioGroup group, int checkedId) {
    switch (checkedId) {
      case R.id.radio0:
        radioSelection = 0;
        break;
      case R.id.radio1:
        radioSelection = 1;
        break;
      case R.id.radio2:
        radioSelection = 2;
        break;
    }
  }

  private void updateOrientationDisplay() {

    switch (radioSelection) {
//      case 0:
//        mAzimuthView.setText(d.format(accMagOrientation[0] * 180 / Math.PI) + '�');
//        mPitchView.setText(d.format(accMagOrientation[1] * 180 / Math.PI) + '�');
//        mRollView.setText(d.format(accMagOrientation[2] * 180 / Math.PI) + '�');
//        break;
//      case 1:
//        mAzimuthView.setText(d.format(gyroOrientation[0] * 180 / Math.PI) + '�');
//        mPitchView.setText(d.format(gyroOrientation[1] * 180 / Math.PI) + '�');
//        mRollView.setText(d.format(gyroOrientation[2] * 180 / Math.PI) + '�');
//        break;
      case 2:
        mAzimuthView.setText(d.format(fusedOrientation[0] * 180 / Math.PI) + '�');
        mPitchView.setText(d.format(fusedOrientation[1] * 180 / Math.PI) + '�');
        mRollView.setText(d.format(fusedOrientation[2] * 180 / Math.PI) + '�');
        break;
    }
  }
}
