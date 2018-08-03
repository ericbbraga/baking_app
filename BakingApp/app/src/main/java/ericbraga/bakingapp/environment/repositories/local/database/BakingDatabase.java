package ericbraga.bakingapp.environment.repositories.local.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ericbraga.bakingapp.environment.repositories.local.database.interfaces.IngredientDao;
import ericbraga.bakingapp.environment.repositories.local.database.interfaces.RecipeDao;
import ericbraga.bakingapp.environment.repositories.local.database.interfaces.StepDao;
import ericbraga.bakingapp.environment.repositories.local.models.entity.IngredientEntity;
import ericbraga.bakingapp.environment.repositories.local.models.entity.RecipeEntity;
import ericbraga.bakingapp.environment.repositories.local.models.entity.StepEntity;

@Database(entities = {RecipeEntity.class, IngredientEntity.class, StepEntity.class},
        version = 1,
        exportSchema = false)
public abstract class BakingDatabase extends RoomDatabase {
    public abstract RecipeDao recipeDao();

    public abstract IngredientDao ingredientDao();

    public abstract StepDao stepDao();
}
