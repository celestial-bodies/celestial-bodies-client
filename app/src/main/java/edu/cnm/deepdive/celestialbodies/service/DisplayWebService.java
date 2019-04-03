package edu.cnm.deepdive.celestialbodies.service;

import android.os.AsyncTask;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Declares the {@link #get(String, String)} Retrofit service method () for communicating with the
 * NASA APOD web service, and defines nested classes in support of making these requests and
 * deserializing the JSON data returned.
 */
public interface DisplayWebService {

  /**
   * Note that the implementation of this method is completed by Retrofit.
   * @param ra ra
   * @param dec dec
   * @param angle angle
   * @param max_stars max_stars
   * @param max_vmag max_vmag
   * @return
   */
  @GET("getstars.jsp")
  Call<StarResponse> get(@Query("ra") String ra, @Query("de") String dec, @Query("angle") String angle,
      @Query("max_stars") String max_stars, @Query("max_vmag") String max_vmag);

  //@QueryMap Map<String,String>


  /**
   * Implements the initialization-on-demand holder idiom for a singleton of {@link
   * DisplayWebService}.
   */
  class InstanceHolder {

    private static final DisplayWebService INSTANCE;

    static {
      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl("http://server1.sky-map.org/getstars.jsp")
          .addConverterFactory(SimpleXmlConverterFactory.create())
          .build();
      INSTANCE = retrofit.create(DisplayWebService.class);
    }

  }

  /**
   * Encapsulates the request lifecycle for the NASA APOD web service as a {@link
   * BaseFluentAsyncTask} subclass.
   */
  class GetFromWikiSkyTask extends AsyncTask<String, Void, StarResponse> {

    @Override
    protected StarResponse doInBackground(String... params) {
      StarResponse result = null;
      try {
        Response<StarResponse> response = InstanceHolder.INSTANCE.get(params[0],
            params[1],params[2],params[3],params[4]).execute();
        if (!response.isSuccessful()) {
          throw new RuntimeException("Request Not Successful");
        }
        return response.body();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

  }

  public class Star
  {
    private String catId;

    private String de;

    private String mag;

    private String id;

    private String ra;

    public String getCatId ()
    {
      return catId;
    }

    public void setCatId (String catId)
    {
      this.catId = catId;
    }

    public String getDe ()
    {
      return de;
    }

    public void setDe (String de)
    {
      this.de = de;
    }

    public String getMag ()
    {
      return mag;
    }

    public void setMag (String mag)
    {
      this.mag = mag;
    }

    public String getId ()
    {
      return id;
    }

    public void setId (String id)
    {
      this.id = id;
    }

    public String getRa ()
    {
      return ra;
    }

    public void setRa (String ra)
    {
      this.ra = ra;
    }

    @Override
    public String toString()
    {
      return "ClassPojo [catId = "+catId+", de = "+de+", mag = "+mag+", id = "+id+", ra = "+ra+"]";
    }
  }

  public class InnerResponse
  {
    private String de;

    private String msgs;

    private Star[] star;

    private String angle;

    private String max_stars;

    private String ra;

    public String getDe ()
    {
      return de;
    }

    public void setDe (String de)
    {
      this.de = de;
    }

    public String getMsgs ()
    {
      return msgs;
    }

    public void setMsgs (String msgs)
    {
      this.msgs = msgs;
    }

    public Star[] getStar ()
    {
      return star;
    }

    public void setStar (Star[] star)
    {
      this.star = star;
    }

    public String getAngle ()
    {
      return angle;
    }

    public void setAngle (String angle)
    {
      this.angle = angle;
    }

    public String getMax_stars ()
    {
      return max_stars;
    }

    public void setMax_stars (String max_stars)
    {
      this.max_stars = max_stars;
    }

    public String getRa ()
    {
      return ra;
    }

    public void setRa (String ra)
    {
      this.ra = ra;
    }

    @Override
    public String toString()
    {
      return "ClassPojo [de = "+de+", msgs = "+msgs+", star = "+star+", angle = "+angle+", max_stars = "+max_stars+", ra = "+ra+"]";
    }
  }

  public class StarResponse
  {
    private InnerResponse response;

    public InnerResponse getResponse ()
    {
      return response;
    }

    public void setResponse (InnerResponse response)
    {
      this.response = response;
    }

    @Override
    public String toString()
    {
      return "ClassPojo [response = "+response+"]";
    }
  }


}
