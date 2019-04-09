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
import org.simpleframework.xml.ElementList;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Declares the  get(String, String) Retrofit service method () for communicating with the
 * WikiSky web service, and defines nested classes in support of making these requests and
 * deserializing the JSON data returned.
 */
public interface DisplayWebService {

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
   * Encapsulates the request lifecycle for the WikiSky web service as a {@link AsyncTask}
   * subclass.
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

    @ElementList(inline = true)
    private List<Star> star;

    @Element
    private String angle;

    @Element
    private String max_stars;

    @Element
    private String ra;

    /**
     * Gets a String representation of the declination of a Star entity.
     *
     * @return de a star declination.
     */
    public String getDe() {
      return de;
    }

    /**
     * Sets a String representation of the declination of a Star entity.
     *
     * @param de a star declination.
     */
    public void setDe(String de) {
      this.de = de;
    }


    public String getMsgs() {
      return msgs;
    }

    public void setMsgs(String msgs) {
      this.msgs = msgs;
    }

    /**
     * Returns a list of stars
     * @return star from a list of stars
     */
    public List<Star> getStar() {
      return star;
    }

    /**
     * Sets a list of stars
     * @param star a list of stars queried from WikiSky
     */
    public void setStar(List<Star> star) {
      this.star = star;
    }

    /**
     * Returns angle which sets scope of sky to be viewed
     * @return angle angle determines amount of sky examined
     */
    public String getAngle() {
      return angle;
    }

    /**
     * Sets angle for determining scope of sky to be viewed.
     * @param angle viewing angle of sky.
     */
    public void setAngle(String angle) {
      this.angle = angle;
    }

    /**
     * Returns maximum number of stars to be returned by query.
     * @return max_stars
     */
    public String getMax_stars() {
      return max_stars;
    }

    /**
     * Sets the maximum number of stars to be returned for display to user.
     * @param max_stars maximum number of stars to be returned by query.
     */
    public void setMax_stars(String max_stars) {
      this.max_stars = max_stars;
    }

    /**
     * Returns the right ascension of star viewing angle.
     * @return ra the right ascension.
     */
    public String getRa() {
      return ra;
    }

    /**
     * Sets the right ascension of star area to be view
     * @param ra the right ascension.
     */
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
