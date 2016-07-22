package com.srmarlins.vertex.home.instagram.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by JaredFowler on 7/22/2016.
 */
public class InstagramMedia implements Parcelable {

    public enum Type {
        VIDEO,
        PHOTO
    }

    private Type type;
    private String url;
    private String description;

    public InstagramMedia(){

    }

    public InstagramMedia(String url, String description, Type type){
        this.url = url;
        this.type = type;
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Type stringToType(String type){
        return TextUtils.isEmpty(type) ? Type.PHOTO : Type.VIDEO;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type == null ? -1 : this.type.ordinal());
        dest.writeString(this.url);
        dest.writeString(this.description);
    }

    protected InstagramMedia(Parcel in) {
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : Type.values()[tmpType];
        this.url = in.readString();
        this.description = in.readString();
    }

    public static final Creator<InstagramMedia> CREATOR = new Creator<InstagramMedia>() {
        @Override
        public InstagramMedia createFromParcel(Parcel source) {
            return new InstagramMedia(source);
        }

        @Override
        public InstagramMedia[] newArray(int size) {
            return new InstagramMedia[size];
        }
    };
}
