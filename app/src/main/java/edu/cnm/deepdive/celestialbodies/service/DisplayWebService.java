package edu.cnm.deepdive.celestialbodies.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import edu.cnm.deepdive.celestialbodies.model.entity.Star;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import okhttp3.ResponseBody;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Declares the {@link #get(String, String)} Retrofit service method () for communicating with the
 * NASA APOD web service, and defines nested classes in support of making these requests and
 * deserializing the JSON data returned.
 */
public interface DisplayWebService {

  /**
   * Note that the implementation of this method is completed by Retrofit.
   *
   * @param ra ra
   * @param dec dec
   * @param angle angle
   * @param max_stars max_stars
   * @param max_vmag max_vmag
   */
  @GET("getstars.jsp")
  Call<StarResponse> get(@QueryMap Map<String, String> queryMap);

  @GET("map")
  Call<ResponseBody> fetchImageMap(@QueryMap Map<String, String> queryMap);

  /**
   * Implements the initialization-on-demand holder idiom for a singleton of {@link
   * DisplayWebService}.
   */
  class InstanceHolder {

    private static final DisplayWebService INSTANCE;

    static {
      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl("http://server1.sky-map.org")
          .addConverterFactory(SimpleXmlConverterFactory.create())
          .build();
      INSTANCE = retrofit.create(DisplayWebService.class);
    }

  }

  /**
   * Encapsulates the request lifecycle for the NASA APOD web service as a {@link
   * BaseFluentAsyncTask} subclass.
   */
  class GetFromWikiSkyTask extends AsyncTask<Map<String, String>, Void, StarResponse> {

    @Override
    protected StarResponse doInBackground(Map<String, String>... params) {
      StarResponse result = null;
      try {
        Response<StarResponse> response = InstanceHolder.INSTANCE.get(params[0]).execute();
        if (!response.isSuccessful()) {
          throw new RuntimeException("Request Not Successful");
        }
        return response.body();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

  }

  /**
   *
   */
  class GetImageFromWikiSkyTask extends AsyncTask<Map<String, String>, Void, Bitmap> {

    @Override
    protected Bitmap doInBackground(Map<String, String>... queryMap) {
      StarResponse result = null;
      try {
        Response<ResponseBody> response = InstanceHolder.INSTANCE.fetchImageMap(queryMap[0])
            .execute();
        if (!response.isSuccessful()) {
          throw new RuntimeException("Request Not Successful");
        }
        return BitmapFactory.decodeStream(response.body().byteStream());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

  }



  class StarResponse {

    @Element
    private String de;

    @Element(required = false)
    private String msgs;

    @ElementList(inline=true)
    private List<Star> star;

    @Element
    private String angle;

    @Element
    private String max_stars;

    @Element
    private String ra;

    public String getDe() {
      return de;
    }

    public void setDe(String de) {
      this.de = de;
    }

    public String getMsgs() {
      return msgs;
    }

    public void setMsgs(String msgs) {
      this.msgs = msgs;
    }

    public List<Star> getStar() {
      return star;
    }

    public void setStar(List<Star> star) {
      this.star = star;
    }

    public String getAngle() {
      return angle;
    }

    public void setAngle(String angle) {
      this.angle = angle;
    }

    public String getMax_stars() {
      return max_stars;
    }

    public void setMax_stars(String max_stars) {
      this.max_stars = max_stars;
    }

    public String getRa() {
      return ra;
    }

    public void setRa(String ra) {
      this.ra = ra;
    }

    @Override
    public String toString() {
      return "ClassPojo [de = " + de + ", msgs = " + msgs + ", star = " + star + ", angle = "
          + angle + ", max_stars = " + max_stars + ", ra = " + ra + "]";
    }
  }


}
