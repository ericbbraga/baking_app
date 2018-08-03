package ericbraga.bakingapp.environment.repositories.local.database.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import java.util.List;

import ericbraga.bakingapp.environment.repositories.local.database.contract.BakingContract;
import ericbraga.bakingapp.environment.repositories.local.models.entity.RecipeEntity;
import ericbraga.bakingapp.environment.repositories.local.models.entity.RecipeIngredientsSteps;

@Dao
public interface RecipeDao {
    @Query("Select * From " + BakingContract.RecipeEntry.TABLE_NAME)
    @Transaction
    List<RecipeIngredientsSteps> loadRecipes();

    @Query("Select * From " + BakingContract.RecipeEntry.TABLE_NAME +
            " Where " + BakingContract.RecipeEntry.STARRED_COLUMN + "=1 LIMIT 1")
    @Transaction
    RecipeIngredientsSteps loadRecipeStarred();

    @Query("Select * From " + BakingContract.RecipeEntry.TABLE_NAME +
            " Where " + BakingContract.RecipeEntry._ID + "=:id")
    @Transaction
    RecipeIngredientsSteps findRecipeById(long id);

    @Insert
    long insert(RecipeEntity recipe);

    @Update
    void update(RecipeEntity recipe);
}
