package ericbraga.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class Step implements Comparable<Step>, Parcelable {
    private final int mOrder;
    private final String mShortDescription;
    private final String mDescription;

    private final String mVideoUrl;
    private final String mThumbnailUrl;

    public Step(int order, String shortDescription, String description, String video,
                String thumbnail) {
        mOrder = order;
        mShortDescription = shortDescription;
        mDescription = description;
        mVideoUrl = video;
        mThumbnailUrl = thumbnail;
    }

    protected Step(Parcel in) {
        mOrder = in.readInt();
        mShortDescription = in.readString();
        mDescription = in.readString();
        mVideoUrl = in.readString();
        mThumbnailUrl = in.readString();
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    public int getOrder() {
        return mOrder;
    }

    public String getShortDescription() {
        return mShortDescription;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getVideoUrl() {
        return mVideoUrl;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    @Override
    public int compareTo(@NonNull Step step) {
        return getOrder() - step.getOrder();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mOrder);
        parcel.writeString(mShortDescription);
        parcel.writeString(mDescription);
        parcel.writeString(mVideoUrl);
        parcel.writeString(mThumbnailUrl);
    }
}
