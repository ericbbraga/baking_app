package ericbraga.bakingapp.environment.repositories.local.models;


public class StepLocal {
    private String mDescription;

    private int mOrder;
    private String mShortDescription;
    private String mVideoUrl;
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

    public void setDescription(String description) {
        mDescription = description;
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
