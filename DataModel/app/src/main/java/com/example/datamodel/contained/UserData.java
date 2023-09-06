package com.example.datamodel.contained;

import android.os.Parcel;
import android.os.Parcelable;

public class UserData implements Parcelable {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    String name,
            email,
            num,
            gender,
            date,
            time,
            country;

    public UserData(){

    }

    protected UserData(Parcel in) {
        name = in.readString();
        email = in.readString();
        num = in.readString();
        gender = in.readString();
        date = in.readString();
        time = in.readString();
        country = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(num);
        dest.writeString(gender);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(country);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

 /*   public void setter(String n,
                       String nm,
                       String em,
                       String gn,
                       String dt,
                       String tm,
                       String coun){
        this.name=n;
        this.num=nm;
        this.email=em;
        this.gender=gn;
        this.date=dt;
        this.time=tm;
        this.country=coun;
    }
    public String getter(String x){
        switch (x){
            case "name":
                return name;
            case "number":
                return num;
            case "email":
                return email;
            case "gender":
                return gender;
            case "date":
                return date;
            case "time":
                return time;
            case "country":
                return country;
            default: return "0";
        }
    }*/

}
