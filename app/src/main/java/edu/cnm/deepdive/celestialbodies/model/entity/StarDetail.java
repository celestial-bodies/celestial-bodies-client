package edu.cnm.deepdive.celestialbodies.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This class provides the fields needed for the user to access the server side database.
 */
public class StarDetail {

  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("hip")
  @Expose
  private Integer hip;
  @SerializedName("hd")
  @Expose
  private Integer hd;
  @SerializedName("hr")
  @Expose
  private String hr;
  @SerializedName("gl")
  @Expose
  private String gl;
  @SerializedName("bf")
  @Expose
  private String bf;
  @SerializedName("proper")
  @Expose
  private String proper;
  @SerializedName("ra")
  @Expose
  private Double ra;
  @SerializedName("dec")
  @Expose
  private Double dec;
  @SerializedName("dist")
  @Expose
  private Integer dist;
  @SerializedName("pmra")
  @Expose
  private Double pmra;
  @SerializedName("pmdec")
  @Expose
  private Double pmdec;
  @SerializedName("rv")
  @Expose
  private Integer rv;
  @SerializedName("mag")
  @Expose
  private Double mag;
  @SerializedName("absmag")
  @Expose
  private Double absmag;
  @SerializedName("spect")
  @Expose
  private String spect;
  @SerializedName("ci")
  @Expose
  private Double ci;
  @SerializedName("x")
  @Expose
  private Double x;
  @SerializedName("y")
  @Expose
  private Double y;
  @SerializedName("z")
  @Expose
  private Double z;
  @SerializedName("vx")
  @Expose
  private Double vx;
  @SerializedName("vy")
  @Expose
  private Double vy;
  @SerializedName("vz")
  @Expose
  private Double vz;
  @SerializedName("rarad")
  @Expose
  private Double rarad;
  @SerializedName("decrad")
  @Expose
  private Double decrad;
  @SerializedName("pmrarad")
  @Expose
  private Double pmrarad;
  @SerializedName("pmdecrad")
  @Expose
  private Double pmdecrad;
  @SerializedName("bayer")
  @Expose
  private String bayer;
  @SerializedName("flam")
  @Expose
  private String flam;
  @SerializedName("con")
  @Expose
  private String con;
  @SerializedName("comp")
  @Expose
  private Integer comp;
  @SerializedName("comp_primary")
  @Expose
  private Integer compPrimary;
  @SerializedName("base")
  @Expose
  private String base;
  @SerializedName("lum")
  @Expose
  private Double lum;
  @SerializedName("var")
  @Expose
  private String var;
  @SerializedName("var_min")
  @Expose
  private Double varMin;
  @SerializedName("var_max")
  @Expose
  private Double varMax;

  /**
   * Returns the database primary key
   *
   * @return id database primary key
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the database primary key
   *
   * @param id database primary key
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Returns The star's ID in the Hipparcos catalog, if known.
   *
   * @return hip the star's ID in the Hipparcos catalog, if known.
   */
  public long getHip() {
    return hip;
  }

  /**
   * Sets the star's ID in the Hipparcos catalog, if known.
   *
   * @param hip the star's ID in the Hipparcos catalog, if known.
   */
  public void setHip(Integer hip) {
    this.hip = hip;
  }

  /**
   * Returns the star's ID in the Henry Draper catalog, if known.
   *
   * @return hd the star's ID in the Henry Draper catalog, if known.
   */
  public long getHd() {
    return hd;
  }

  /**
   * Sets the star's ID in the Henry Draper catalog, if known.
   *
   * @param hd the star's ID in the Henry Draper catalog, if known.
   */
  public void setHd(Integer hd) {
    this.hd = hd;
  }

  /**
   * Returns the star's ID in the Harvard Revised catalog, which is the same as its number in the
   * Yale Bright Star Catalog.
   *
   * @return hr the star's ID in the Harvard Revised catalog, which is the same as its number in the
   * Yale Bright Star Catalog.
   */
  public String getHr() {
    return hr;
  }

  /**
   * Sets the star's ID in the Harvard Revised catalog, which is the same as its number in the Yale
   * Bright Star Catalog.
   *
   * @param hr the star's ID in the Harvard Revised catalog, which is the same as its number in the
   * Yale Bright Star Catalog.
   */
  public void setHr(String hr) {
    this.hr = hr;
  }

  /**
   * Returns the star's ID in the third edition of the Gliese Catalog of Nearby Stars.
   *
   * @return gl the star's ID in the third edition of the Gliese Catalog of Nearby Stars.
   */
  public String getGl() {
    return gl;
  }

  /**
   * Sets the star's ID in the third edition of the Gliese Catalog of Nearby Stars.
   *
   * @param gl the star's ID in the third edition of the Gliese Catalog of Nearby Stars.
   */
  public void setGl(String gl) {
    this.gl = gl;
  }

  /**
   * Returns The Bayer / Flamsteed designation, primarily from the Fifth Edition of the Yale Bright
   * Star Catalog. This is a combination of the two designations. The Flamsteed number, if present,
   * is given first; then a three-letter abbreviation for the Bayer Greek letter; the Bayer
   * superscript number, if present; and finally, the three-letter constellation abbreviation.
   *
   * @return bf the Bayer / Flamsteed designation.
   */
  public String getBf() {
    return bf;
  }

  /**
   * Sets the Bayer / Flamsteed designation.
   *
   * @param bf the Bayer / Flamsteed designation.
   */
  public void setBf(String bf) {
    this.bf = bf;
  }

  /**
   * Returns a common name for the star.
   *
   * @return proper a common name for the star.
   */
  public String getProper() {
    return proper;
  }

  /**
   * Sets the common name for the star.
   *
   * @param proper the common name for the star
   */
  public void setProper(String proper) {
    this.proper = proper;
  }

  /**
   * Returns the star's right ascension for epoch and equinox 2000.0.
   *
   * @return ra
   */
  public double getRa() {
    return ra;
  }

  /**
   * Sets the star's right ascension, for epoch and equinox 2000.0.
   *
   * @param ra the star's right ascension, for epoch and equinox 2000.0.
   */
  public void setRa(double ra) {
    this.ra = ra;
  }

  /**
   * Returns the star's declination, for epoch and equinox 2000.0.
   *
   * @return dec
   */
  public double getDec() {
    return dec;
  }

  /**
   * Sets the star's declination, for epoch and equinox 2000.0.
   *
   * @param dec the star's declination, for epoch and equinox 2000.0.
   */
  public void setDec(double dec) {
    this.dec = dec;
  }

  /**
   * Returns the star's distance in parsecs
   */
  public Integer getDist() {
    return dist;
  }

  /**
   * Sets the star's distance in parsecs
   */
  public void setDist(Integer dist) {
    this.dist = dist;
  }

  /**
   * Returns the star's proper motion in right ascension
   */
  public double getPmra() {
    return pmra;
  }

  /**
   * Sets he star's proper motion in right ascension.
   */
  public void setPmra(double pmra) {
    this.pmra = pmra;
  }

  /**
   * Returns the star's proper motion in declination.
   */
  public double getPmdec() {
    return pmdec;
  }

  /**
   * Sets the star's proper motion in declination.
   */
  public void setPmdec(double pmdec) {
    this.pmdec = pmdec;
  }

  /**
   * Returns the star's radial velocity in km/sec, where known.
   */
  public int getRv() {
    return rv;
  }

  /**
   * Sets the star's radial velocity in km/sec, where known.
   */
  public void setRv(int rv) {
    this.rv = rv;
  }

  /**
   * Returns the star's apparent visual magnitude.
   */
  public double getMag() {
    return mag;
  }

  /**
   * Sets the star's apparent visual magnitude.
   */
  public void setMag(double mag) {
    this.mag = mag;
  }

  /**
   * Returns the star's absolute visual magnitude (its apparent magnitude from a distance of 10
   * parsecs).
   */
  public double getAbsmag() {
    return absmag;
  }

  /**
   * Sets the star's absolute visual magnitude (its apparent magnitude from a distance of 10
   * parsecs).
   */
  public void setAbsmag(double absmag) {
    this.absmag = absmag;
  }

  /**
   * Returns the star's spectral type, if known.
   */
  public String getSpect() {
    return spect;
  }

  /**
   * Sets the star's spectral type, if known.
   */
  public void setSpect(String spect) {
    this.spect = spect;
  }

  /**
   * Returns the star's color index (blue magnitude - visual magnitude), where known.
   */
  public Double getCi() {
    return ci;
  }

  /**
   * Sets the star's color index (blue magnitude - visual magnitude), where known.
   */
  public void setCi(Double ci) {
    this.ci = ci;
  }

  /**
   * Returns x local coordinate
   * @return
   */
  public Double getX() {
    return x;
  }

  /**
   * Sets x local coordinate
   * @param x
   */
  public void setX(Double x) {
    this.x = x;
  }

  /**
   * Returns y local coordinate
   * @return
   */
  public Double getY() {
    return y;
  }

  /**
   * Sets y local coordinate
   * @param y
   */
  public void setY(Double y) {
    this.y = y;
  }

  /**
   * Returns z local coordinate
   * @return
   */
  public Double getZ() {
    return z;
  }

  /**
   * Sets z local coordinate
   * @param z
   */
  public void setZ(Double z) {
    this.z = z;
  }


  /**
   * Returns the Cartesian velocity components of the star, in the same coordinate system described
   * immediately above.
   */
  public Double getVx() {
    return vx;
  }

  /**
   * Sets the Cartesian velocity components of the star, in the same coordinate system described
   * immediately above.
   */
  public void setVx(Double vx) {
    this.vx = vx;
  }

  /**
   * Returns the Cartesian velocity components of the star, in the same coordinate system described
   * immediately above.
   */
  public Double getVy() {
    return vy;
  }

  /**
   * Sets the Cartesian velocity components of the star, in the same coordinate system described
   * immediately above.
   */
  public void setVy(Double vy) {
    this.vy = vy;
  }

  /**
   * Returns the Cartesian velocity components of the star, in the same coordinate system described
   * immediately above.
   */
  public Double getVz() {
    return vz;
  }

  /**
   * Sets the Cartesian velocity components of the star, in the same coordinate system described
   * immediately above.
   */
  public void setVz(Double vz) {
    this.vz = vz;
  }

  /**
   * Returns the position in radians
   */
  public double getRarad() {
    return rarad;
  }

  /**
   * Sets the position in radians
   */
  public void setRarad(double rarad) {
    this.rarad = rarad;
  }

  /**
   * Returns the position in radians.
   */
  public double getDecrad() {
    return decrad;
  }

  /**
   * Sets the position in radians.
   */
  public void setDecrad(double decrad) {
    this.decrad = decrad;
  }

  /**
   * Returns the position in proper radians.
   */
  public double getPmrarad() {
    return pmrarad;
  }

  /**
   * Sets the position in proper radians.
   */
  public void setPmrarad(double pmrarad) {
    this.pmrarad = pmrarad;
  }

  /**
   * Returns the position in proper radians.
   */
  public double getPmdecrad() {
    return pmdecrad;
  }

  /**
   * Sets the position in proper radians.
   */
  public void setPmdecrad(double pmdecrad) {
    this.pmdecrad = pmdecrad;
  }

  /**
   * Returns the Bayer designation as a distinct value.
   */
  public String getBayer() {
    return bayer;
  }

  /**
   * Sets the Bayer designation as a distinct value
   */
  public void setBayer(String bayer) {
    this.bayer = bayer;
  }

  /**
   * Returns the Flamsteed number as a distinct value.
   */
  public String getFlam() {
    return flam;
  }

  /**
   * Sets the Flamsteed number as a distinct value.
   */
  public void setFlam(String flam) {
    this.flam = flam;
  }

  /**
   * Returns the standard constellation abbreviation
   */
  public String getCon() {
    return con;
  }

  /**
   * Sets the standard constellation abbreviation.
   */
  public void setCon(String con) {
    this.con = con;
  }

  /**
   * Returns a star in a multiple star system. comp = ID of companion star.
   */
  public double getComp() {
    return comp;
  }

  /**
   * Sets a star in a multiple star system, comp = ID of companion star.
   */
  public void setComp(Integer comp) {
    this.comp = comp;
  }

  /**
   * Returns a star in a multiple star system. Base = catalog ID or name for this multi-star
   * system.
   */
  public String getBase() {
    return base;
  }

  /**
   * Sets a star in a multiple star system. Base = catalog ID or name for this multi-star
   * system.
   * @param base
   */
  public void setBase(String base) {
    this.base = base;
  }

  /**
   * Returns a star's luminosity as a multiple of Solar luminosity.
   * @return
   */
  public double getLum() {
    return lum;
  }

  /**
   * Sets a star's luminosity as a multiple of Solar luminosity.
   * @param lum
   */
  public void setLum(double lum) {
    this.lum = lum;
  }

  /**
   * Returns a star's standard variable star designation, when known.
   * @return
   */
  public String getVar() {
    return var;
  }

  /**
   * Sets a star's standard variable star designation, when known.
   * @param var
   */
  public void setVar(String var) {
    this.var = var;
  }
}
