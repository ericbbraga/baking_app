package ericbraga.bakingapp.environment.repositories.local.database.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import java.util.List;

import ericbraga.bakingapp.environment.repositories.local.models.entity.IngredientEntity;

@Dao
public interface IngredientDao {
    @Insert
    void insert(IngredientEntity ingredientEntity);

    @Update
    void update(List<IngredientEntity> ingredientsEntity);
}
