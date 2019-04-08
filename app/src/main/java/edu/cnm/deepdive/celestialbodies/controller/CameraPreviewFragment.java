
package edu.cnm.deepdive.celestialbodies.controller;


import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.facebook.stetho.inspector.network.ResponseHandlingInputStream.TAG;

import android.Manifest;
import android.Manifest.permission;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import edu.cnm.deepdive.celestialbodies.R;
import edu.cnm.deepdive.celestialbodies.view.CameraView;
import java.io.IOException;


public class CameraPreviewFragment extends Fragment {

  private CameraView cameraView;
  private CameraDevice cameraDevice;
  private  boolean cameraPermission;
  @SuppressLint("MissingPermission")
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    if (!checkCameraHardware(getContext())) {
      return null;
    }
    View view = inflater.inflate(R.layout.fragment_camera_preview, container, false);
    cameraView = view.findViewById(R.id.camera_view);

    CameraManager manager = (CameraManager) getContext().getSystemService(Context.CAMERA_SERVICE);

    try {
      for (String cameraId : manager.getCameraIdList()) {
        CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
        Integer facing = characteristics.get(CameraCharacteristics.LENS_FACING);
        if (facing != null && facing.equals(CameraCharacteristics.LENS_FACING_FRONT)) {
          manager.openCamera(cameraId, new StateCallback(), null);
          break;
        }
      }
    } catch (CameraAccessException e) {
      e.printStackTrace();
    }

    return view;
  }


/**
   * Check if this device has a camera
   */

  private boolean checkCameraHardware(Context context) {
    if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
      // this device has a camera
      return true;
    } else {
      // no camera on this device
      return false;
    }
  }



  private class StateCallback extends CameraDevice.StateCallback {

    @Override
    public void onOpened(@NonNull CameraDevice cameraDevice) {

      CameraPreviewFragment.this.cameraDevice = cameraDevice;
      //TODO Connect Camera View to camera view
    }

    @Override
    public void onDisconnected(@NonNull CameraDevice cameraDevice) {

      CameraPreviewFragment.this.cameraDevice = null;
      //TODO Update UI to indicate no camera
    }

    @Override
    public void onError(@NonNull CameraDevice cameraDevice, int i) {

    }
  }

  /** A basic Camera preview class */
  public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Camera mCamera;

    public CameraPreview(Context context, Camera camera) {
      super(context);
      mCamera = camera;

      // Install a SurfaceHolder.Callback so we get notified when the
      // underlying surface is created and destroyed.
      mHolder = getHolder();
      mHolder.addCallback(this);
      // deprecated setting, but required on Android versions prior to 3.0
      mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
      // The Surface has been created, now tell the camera where to draw the preview.
      try {
        mCamera.setPreviewDisplay(holder);
        mCamera.startPreview();
      } catch (IOException e) {

      }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
      // empty. Take care of releasing the Camera preview in your activity.
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
      // If your preview can change or rotate, take care of those events here.
      // Make sure to stop the preview before resizing or reformatting it.

      if (mHolder.getSurface() == null){
        // preview surface does not exist
        return;
      }

      // stop preview before making changes
      try {
        mCamera.stopPreview();
      } catch (Exception e){
        // ignore: tried to stop a non-existent preview
      }

      // set preview size and make any resize, rotate or
      // reformatting changes here

      // start preview with new settings
      try {
        mCamera.setPreviewDisplay(mHolder);
        mCamera.startPreview();

      } catch (Exception e){

      }
    }
  }

  public static CameraPreviewFragment newInstance() {

    Bundle args = new Bundle();

    CameraPreviewFragment fragment = new CameraPreviewFragment();
    fragment.setArguments(args);
    return fragment;
  }
}

//
//
///*
// * Copyright 2015 The Android Open Source Project
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package edu.cnm.deepdive.celestialbodies.controller;
//
//import android.hardware.Camera;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//import android.widget.Toast;
//import edu.cnm.deepdive.celestialbodies.R;
//import edu.cnm.deepdive.celestialbodies.view.CameraView;
//
//
///**
// * Displays a {@link CameraPreview} of the first {@link Camera}.
// * An error message is displayed if the Camera is not available.
// * <p>
// * This Fragment is only used to illustrate that access to the Camera API has been granted (or
// * denied) as part of the runtime permissions model. It is not relevant for the use of the
// * permissions API.
// * <p>
// * Implementation is based directly on the documentation at
// * http://developer.android.com/guide/topics/media/camera.html
// */
//public class CameraPreviewFragment extends Fragment {
//
//  private static final String TAG = "CameraPreview";
//
//  /**
//   * Id of the camera to access. 0 is the first camera.
//   */
//  private static final int CAMERA_ID = 0;
//
//  private CameraView mPreview;
//  private Camera mCamera;
//
//  public static CameraPreviewFragment newInstance() {
//    return new CameraPreviewFragment();
//  }
//
//  /**
//   * A safe way to get an instance of the Camera object.
//   */
//  public static Camera getCameraInstance(int cameraId) {
//    Camera c = null;
//    try {
//      c = Camera.open(cameraId); // attempt to get a Camera instance
//    } catch (Exception e) {
//      // Camera is not available (in use or does not exist)
//      Log.d(TAG, "Camera " + cameraId + " is not available: " + e.getMessage());
//    }
//    return c; // returns null if camera is unavailable
//  }
//
//  @Override
//  public View onCreateView(LayoutInflater inflater, ViewGroup container,
//      Bundle savedInstanceState) {
//
//    // Open an instance of the first camera and retrieve its info.
//    mCamera = getCameraInstance(CAMERA_ID);
//    Camera.CameraInfo cameraInfo = null;
//
//    if (mCamera != null) {
//      // Get camera info only if the camera is available
//      cameraInfo = new Camera.CameraInfo();
//      Camera.getCameraInfo(CAMERA_ID, cameraInfo);
//    }
//
//    if (mCamera == null || cameraInfo == null) {
//      // Camera is not available, display error message
//      Toast.makeText(getActivity(), "Camera is not available.", Toast.LENGTH_SHORT).show();
//      return inflater.inflate(R.layout.fragment_camera_unavaliable, null);
//    }
//
//    View root = inflater.inflate(R.layout.fragment_camera_preview, null);
//
//    // Get the rotation of the screen to adjust the preview image accordingly.
//    final int displayRotation = getActivity().getWindowManager().getDefaultDisplay()
//        .getRotation();
//
//    // Create the Preview view and set it as the content of this Activity.
//    mPreview = new CameraView(getActivity(), mCamera);
//    FrameLayout preview = (FrameLayout) root.findViewById(R.id.camera_view);
//    preview.addView(mPreview);
//
//    return root;
//  }
//
//  @Override
//  public void onPause() {
//    super.onPause();
//    // Stop camera access
//    releaseCamera();
//  }
//
//  private void releaseCamera() {
//    if (mCamera != null) {
//      mCamera.release();        // release the camera for other applications
//      mCamera = null;
//    }
//  }
//}
