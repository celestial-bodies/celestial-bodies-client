//package edu.cnm.deepdive.celestialbodies.service;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.http.GET;
//import retrofit2.http.Url;
//
//public interface UrlImageGetter {
//
//  // don't need add 'Content-Type' header, it's useless
//  // @Headers({"Content-Type: image/png"})
//  @GET
//  Call<ResponseBody> fetchCaptcha(@Url String url);
//
//
//  Call<ResponseBody> call = api.fetchCaptcha(url);
//call.enqueue(new Callback<ResponseBody>() {
//
//
//@Override
//public void onResponse(Call<UrlImageGetter> call, Response<ResponseBody> response) {
//    if (response.isSuccessful()) {
//    if (response.body() != null) {
//    // display the image data in a ImageView or save it
//    Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
//    imageView.setImageBitmap(bmp);
//    } else {
//    // TODO
//    }
//    } else {
//    // TODO
//    }
//    }
//
//@Override
//public void onFailure(Call<ResponseBody> call, Throwable t) {
//    // TODO
//    }
//    });
