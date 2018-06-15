package ericbraga.bakingapp.model;

import android.support.annotation.NonNull;

public class Step implements Comparable<Step> {
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
}
