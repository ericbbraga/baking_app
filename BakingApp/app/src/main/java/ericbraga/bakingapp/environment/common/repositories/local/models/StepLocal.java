package ericbraga.bakingapp.environment.common.repositories.local.models;

import com.google.gson.annotations.SerializedName;

public class StepLocal {
    @SerializedName("order")
    private int mOrder;

    @SerializedName("shortDescription")
    private String mShortDescription;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("videoURL")
    private String mVideoUrl;

    @SerializedName("thumbnailURL")
    private String mThumbnailUrl;

    public StepLocal() {
    }

    public StepLocal(int order, String shortDescription, String description, String video,
                     String thumbnail) {
        mOrder = order;
        mShortDescription = shortDescription;
        mDescription = description;
        mVideoUrl = video;
        mThumbnailUrl = thumbnail;
    }

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

}
