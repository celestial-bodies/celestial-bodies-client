package edu.cnm.deepdive.celestialbodies.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
  private Double dist;
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
   *
   * @return
   */
  public Integer getId() {
    return id;
  }

  /**
   *
   * @param id
   */
  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getHip() {
    return hip;
  }

  public void setHip(Integer hip) {
    this.hip = hip;
  }

  public Integer getHd() {
    return hd;
  }

  public void setHd(Integer hd) {
    this.hd = hd;
  }

  public String getHr() {
    return hr;
  }

  public void setHr(String hr) {
    this.hr = hr;
  }

  public String getGl() {
    return gl;
  }

  public void setGl(String gl) {
    this.gl = gl;
  }

  public String getBf() {
    return bf;
  }

  public void setBf(String bf) {
    this.bf = bf;
  }

  public String getProper() {
    return proper;
  }

  public void setProper(String proper) {
    this.proper = proper;
  }

  public Double getRa() {
    return ra;
  }

  public void setRa(Double ra) {
    this.ra = ra;
  }

  public Double getDec() {
    return dec;
  }

  public void setDec(Double dec) {
    this.dec = dec;
  }

  public Double getDist() {
    return dist;
  }

  public void setDist(Double dist) {
    this.dist = dist;
  }

  public Double getPmra() {
    return pmra;
  }

  public void setPmra(Double pmra) {
    this.pmra = pmra;
  }

  public Double getPmdec() {
    return pmdec;
  }

  public void setPmdec(Double pmdec) {
    this.pmdec = pmdec;
  }

  public Integer getRv() {
    return rv;
  }

  public void setRv(Integer rv) {
    this.rv = rv;
  }

  public Double getMag() {
    return mag;
  }

  public void setMag(Double mag) {
    this.mag = mag;
  }

  public Double getAbsmag() {
    return absmag;
  }

  public void setAbsmag(Double absmag) {
    this.absmag = absmag;
  }

  public String getSpect() {
    return spect;
  }

  public void setSpect(String spect) {
    this.spect = spect;
  }

  public Double getCi() {
    return ci;
  }

  public void setCi(Double ci) {
    this.ci = ci;
  }

  public Double getX() {
    return x;
  }

  public void setX(Double x) {
    this.x = x;
  }

  public Double getY() {
    return y;
  }

  public void setY(Double y) {
    this.y = y;
  }

  public Double getZ() {
    return z;
  }

  public void setZ(Double z) {
    this.z = z;
  }

  public Double getVx() {
    return vx;
  }

  public void setVx(Double vx) {
    this.vx = vx;
  }

  public Double getVy() {
    return vy;
  }

  public void setVy(Double vy) {
    this.vy = vy;
  }

  public Double getVz() {
    return vz;
  }

  public void setVz(Double vz) {
    this.vz = vz;
  }

  public Double getRarad() {
    return rarad;
  }

  public void setRarad(Double rarad) {
    this.rarad = rarad;
  }

  public Double getDecrad() {
    return decrad;
  }

  public void setDecrad(Double decrad) {
    this.decrad = decrad;
  }

  public Double getPmrarad() {
    return pmrarad;
  }

  public void setPmrarad(Double pmrarad) {
    this.pmrarad = pmrarad;
  }

  public Double getPmdecrad() {
    return pmdecrad;
  }

  public void setPmdecrad(Double pmdecrad) {
    this.pmdecrad = pmdecrad;
  }

  public String getBayer() {
    return bayer;
  }

  public void setBayer(String bayer) {
    this.bayer = bayer;
  }

  public String getFlam() {
    return flam;
  }

  public void setFlam(String flam) {
    this.flam = flam;
  }

  public String getCon() {
    return con;
  }

  public void setCon(String con) {
    this.con = con;
  }

  public Integer getComp() {
    return comp;
  }

  public void setComp(Integer comp) {
    this.comp = comp;
  }

  public Integer getCompPrimary() {
    return compPrimary;
  }

  public void setCompPrimary(Integer compPrimary) {
    this.compPrimary = compPrimary;
  }

  public String getBase() {
    return base;
  }

  public void setBase(String base) {
    this.base = base;
  }

  public Double getLum() {
    return lum;
  }

  public void setLum(Double lum) {
    this.lum = lum;
  }

  public String getVar() {
    return var;
  }

  public void setVar(String var) {
    this.var = var;
  }

  public Double getVarMin() {
    return varMin;
  }

  public void setVarMin(Double varMin) {
    this.varMin = varMin;
  }

  public Double getVarMax() {
    return varMax;
  }

  public void setVarMax(Double varMax) {
    this.varMax = varMax;
  }

}
