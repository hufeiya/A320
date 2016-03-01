package com.hufeiya.a320.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hufeiya on 16-2-21.
 */
public class Info implements Parcelable{
    private int id;
    private String type;
    private String system;
    private String date;
    private String site;
    private String ATA;
    private String jobType;
    private String discryption;
    private String measure;
    private String unitNo;
    private String sequenceNo;

    public Info(int id, String type, String system, String date, String site, String ATA,
                String jobType, String discryption, String measure, String unitNo,String sequenceNo) {
        this.id = id;
        this.type = type;
        this.system = system;
        this.date = date;
        this.site = site;
        this.ATA = ATA;
        this.jobType = jobType;
        this.discryption = discryption;
        this.measure = measure;
        this.unitNo = unitNo;
        this.sequenceNo = sequenceNo;
    }

    public Info(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getATA() {
        return ATA;
    }

    public void setATA(String ATA) {
        this.ATA = ATA;
    }

    public String getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(String sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getDiscryption() {
        return discryption;
    }

    public void setDiscryption(String discryption) {
        this.discryption = discryption;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public Info(Parcel in){
        this.id = in.readInt();
        this.type = in.readString();
        this.system = in.readString();
        this.date = in.readString();
        this.site = in.readString();
        this.ATA = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Info> CREATOR
            = new Parcelable.Creator<Info>(){

        @Override
        public Info createFromParcel(Parcel source) {
            return new Info(source);
        }

        @Override
        public Info[] newArray(int size) {
            return new Info[size];
        }
    };
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.type);
        dest.writeString(this.system);
        dest.writeString(this.date);
        dest.writeString(this.site);
        dest.writeString(this.ATA);
    }
}
