package com.gvm.todolist.model;


import android.os.Parcel;
import android.os.Parcelable;

public class CustomModel implements Parcelable {

    private long id;
    private String nama;
    private String address;

    protected CustomModel(Parcel in) {
        id = in.readLong();
        nama = in.readString();
        address = in.readString();
    }

    public static final Creator<CustomModel> CREATOR = new Creator<CustomModel>() {
        @Override
        public CustomModel createFromParcel(Parcel in) {
            return new CustomModel(in);
        }

        @Override
        public CustomModel[] newArray(int size) {
            return new CustomModel[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(nama);
        dest.writeString(address);
    }
}
