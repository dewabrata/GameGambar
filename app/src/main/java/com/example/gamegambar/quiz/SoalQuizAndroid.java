
package com.example.gamegambar.quiz;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SoalQuizAndroid implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("pertanyaan")
    @Expose
    private String pertanyaan;
    @SerializedName("a")
    @Expose
    private String a;
    @SerializedName("b")
    @Expose
    private String b;
    @SerializedName("c")
    @Expose
    private String c;
    @SerializedName("d")
    @Expose
    private String d;
    @SerializedName("jawaban")
    @Expose
    private String jawaban;
    @SerializedName("point")
    @Expose
    private String point;
    @SerializedName("gambar")
    @Expose
    private String gambar;
    @SerializedName("jenis_pertanyaan")
    @Expose
    private String jenisPertanyaan;
    public final static Creator<SoalQuizAndroid> CREATOR = new Creator<SoalQuizAndroid>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SoalQuizAndroid createFromParcel(Parcel in) {
            return new SoalQuizAndroid(in);
        }

        public SoalQuizAndroid[] newArray(int size) {
            return (new SoalQuizAndroid[size]);
        }

    }
    ;
    private final static long serialVersionUID = 3086528199799056061L;

    protected SoalQuizAndroid(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.pertanyaan = ((String) in.readValue((String.class.getClassLoader())));
        this.a = ((String) in.readValue((String.class.getClassLoader())));
        this.b = ((String) in.readValue((String.class.getClassLoader())));
        this.c = ((String) in.readValue((String.class.getClassLoader())));
        this.d = ((String) in.readValue((String.class.getClassLoader())));
        this.jawaban = ((String) in.readValue((String.class.getClassLoader())));
        this.point = ((String) in.readValue((String.class.getClassLoader())));
        this.gambar = ((String) in.readValue((String.class.getClassLoader())));
        this.jenisPertanyaan = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public SoalQuizAndroid() {
    }

    /**
     * 
     * @param a
     * @param b
     * @param c
     * @param d
     * @param id
     * @param jawaban
     * @param gambar
     * @param point
     * @param pertanyaan
     * @param jenisPertanyaan
     */
    public SoalQuizAndroid(String id, String pertanyaan, String a, String b, String c, String d, String jawaban, String point, String gambar, String jenisPertanyaan) {
        super();
        this.id = id;
        this.pertanyaan = pertanyaan;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.jawaban = jawaban;
        this.point = point;
        this.gambar = gambar;
        this.jenisPertanyaan = jenisPertanyaan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getJenisPertanyaan() {
        return jenisPertanyaan;
    }

    public void setJenisPertanyaan(String jenisPertanyaan) {
        this.jenisPertanyaan = jenisPertanyaan;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(pertanyaan);
        dest.writeValue(a);
        dest.writeValue(b);
        dest.writeValue(c);
        dest.writeValue(d);
        dest.writeValue(jawaban);
        dest.writeValue(point);
        dest.writeValue(gambar);
        dest.writeValue(jenisPertanyaan);
    }

    public int describeContents() {
        return  0;
    }

}
