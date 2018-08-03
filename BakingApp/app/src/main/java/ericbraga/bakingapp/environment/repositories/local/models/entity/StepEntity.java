package ericbraga.bakingapp.environment.repositories.local.models.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import ericbraga.bakingapp.environment.repositories.local.database.contract.BakingContract;

@Entity(tableName = BakingContract.StepEntry.TABLE_NAME)
public class StepEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int mId;

    @ColumnInfo(name="description")
    private String mDescription;

    @ColumnInfo(name="short_description")
    private String mShortDescription;

    @ColumnInfo(name="order")
    private int mOrder;

    @ColumnInfo(name="video_url")
    private String mVideoUrl;

    @ColumnInfo(name="thumbnail_url")
    private String mThumbnail;

    @ColumnInfo(name="recipe_id")
    private long mRecipeId;

    public StepEntity() {
    }

    @Ignore
    public StepEntity(String description, String shortDescription, int order, String videoUrl,
                      String thumbnail, long recipeId) {
        mDescription = description;
        mShortDescription = shortDescription;
        mOrder = order;
        mVideoUrl = videoUrl;
        mThumbnail = thumbnail;
        mRecipeId = recipeId;
    }

    @Ignore
    public StepEntity(int id, String description, String shortDescription, int order,
                      String videoUrl, String thumbnail, long recipeId) {
        mId = id;
        mDescription = description;
        mShortDescription = shortDescription;
        mOrder = order;
        mVideoUrl = videoUrl;
        mThumbnail = thumbnail;
        mRecipeId = recipeId;
    }

    public int getId() {
        return mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getShortDescription() {
        return mShortDescription;
    }

    public int getOrder() {
        return mOrder;
    }

    public String getVideoUrl() {
        return mVideoUrl;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public long getRecipeId() {
        return mRecipeId;
    }

    public void setRecipeId(long recipeId) {
        mRecipeId = recipeId;
    }

    public void setId(int id) {
        mId = id;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setShortDescription(String shortDescription) {
        mShortDescription = shortDescription;
    }

    public void setOrder(int order) {
        mOrder = order;
    }

    public void setVideoUrl(String videoUrl) {
        mVideoUrl = videoUrl;
    }

    public void setThumbnail(String thumbnail) {
        mThumbnail = thumbnail;
    }
}
