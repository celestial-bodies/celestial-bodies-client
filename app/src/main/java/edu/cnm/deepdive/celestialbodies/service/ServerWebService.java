package edu.cnm.deepdive.celestialbodies.service;

import android.support.v4.app.INotificationSideChannel;
import edu.cnm.deepdive.celestialbodies.model.entity.StarDetail;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


public interface ServerWebService {


  //Add a @Query annotation to pass the star id to the server
  @GET("/rest/celestial_body_server/stars")
  Call<List<StarDetail>> getStars(@Header("Authorization") String formattedIdTokenString);

  @GET("/rest/celestial_body_server/stars")
  Call<StarDetail> getStarByHdid(@Header("Authorization") String formattedIdTokenString,
      @Query("hdid") long hdid);

  //Url for emulator to local server: 10.0.2.2

  public static ServerWebService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  class InstanceHolder {

    private static final ServerWebService INSTANCE;

    static {
      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl("http://10.46.2.144:8080/")
          .addConverterFactory(GsonConverterFactory.create())
          .build();
      INSTANCE = retrofit.create(ServerWebService.class);
    }

  }


}
