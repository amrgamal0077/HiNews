package com.example.amrgamal.hi_news.Data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AmrGamal on 25/04/2019.
 */

public class Articles implements Parcelable{
    public String author;
    public String description;
    public String title;
    public String photo_url;


    public Articles() {

    }

    public Articles(String title,String author,String photo_url,String description) {
        this.description=description;
        this.photo_url=photo_url;
        this.author=author;
        this.title=title;
    }
    protected Articles(Parcel in) {
        author = in.readString();
        description = in.readString();
        title = in.readString();
        photo_url = in.readString();
    }

    public static final Creator<Articles> CREATOR = new Creator<Articles>() {
        @Override
        public Articles createFromParcel(Parcel in) {
            return new Articles(in);
        }

        @Override
        public Articles[] newArray(int size) {
            return new Articles[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(description);
        dest.writeString(title);
        dest.writeString(photo_url);
    }
}
