package ericbraga.bakingapp.environment.repositories.local.models.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import ericbraga.bakingapp.environment.repositories.local.database.contract.BakingContract;

@Entity(tableName = BakingContract.RecipeEntry.TABLE_NAME)
public class RecipeEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = BakingContract.RecipeEntry._ID)
    private long mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "starred")
    private boolean mStarred;

    public RecipeEntity(long id, String name, boolean starred) {
        mId = id;
        mName = name;
        mStarred = starred;
    }

    public long getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public boolean isStarred() {
        return mStarred;
    }

    public void setStarred(boolean starred) {
        mStarred = starred;
    }
}
