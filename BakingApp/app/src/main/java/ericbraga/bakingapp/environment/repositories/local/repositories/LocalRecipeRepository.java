package ericbraga.bakingapp.environment.repositories.local.repositories;

import android.content.Context;

import java.util.Iterator;
import java.util.List;

import ericbraga.bakingapp.environment.interfaces.AsyncReadWriteRepository;
import ericbraga.bakingapp.environment.interfaces.ReadWriteRepository;
import ericbraga.bakingapp.environment.repositories.local.boundary.RecipeCollectionLocalMapper;
import ericbraga.bakingapp.environment.repositories.local.boundary.RecipeLocalMapper;
import ericbraga.bakingapp.environment.repositories.local.database.LocalDatabase;
import ericbraga.bakingapp.environment.repositories.local.models.RecipeLocal;
import ericbraga.bakingapp.environment.repositories.local.models.RecipeLocalCollection;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;

public class LocalRecipeRepository implements AsyncReadWriteRepository, ReadWriteRepository {
    private final LocalDatabase mRepository;

    public LocalRecipeRepository(Context context) {
        mRepository = LocalDatabase.getInstance(context);
    }

    @Override
    public void listRecipes(final RepositoryCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    RecipeCollection collection = listRecipes();
                    callback.listRecipesContent(collection);
                }
            }
        };

        new Thread(runnable).start();
    }

    @Override
    public void getStarredRecipe(final RepositoryCallbackStarredRecipe callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                RecipeLocal recipeLocal = mRepository.loadRecipeStarred();
                if (recipeLocal != null) {
                    RecipeLocalMapper mapper = new RecipeLocalMapper();
                    callback.starredRecipe(mapper.fromLocalRecipe(recipeLocal));

                } else {
                    callback.starredRecipe(null);
                }
            }
        };

        new Thread(runnable).start();
    }

    @Override
    public RecipeCollection listRecipes() {
        List<RecipeLocal> recipes = mRepository.recipes();
        RecipeLocalCollection localCollection = new RecipeLocalCollection(recipes);

        RecipeCollectionLocalMapper collectionMapper = new RecipeCollectionLocalMapper();
        return collectionMapper.fromLocalRecipe(localCollection);
    }

    @Override
    public Recipe getStarredRecipe() {
        RecipeLocal recipeLocal = mRepository.loadRecipeStarred();
        if (recipeLocal != null) {
            RecipeLocalMapper mapper = new RecipeLocalMapper();
            return mapper.fromLocalRecipe(recipeLocal);
        }

        return null;

    }

    @Override
    public void saveCollection(RecipeCollection collection, WriteRepositoryCallback callback) {
        if (callback != null) {
            Iterator<Recipe> iterator = collection.iterator();
            while (iterator.hasNext()) {
                Recipe recipe = iterator.next();
                save(recipe);
            }

            callback.onSave();
        }
    }

    @Override
    public void update(final Recipe recipe, final WriteRepositoryCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(callback != null){
                    update(recipe);
                    callback.onSave();
                }
            }
        };

        new Thread(runnable).start();
    }

    @Override
    public long save(Recipe recipe) {
        RecipeLocalMapper mRecipeLocalMapper = new RecipeLocalMapper();
        RecipeLocal recipeLocal = mRecipeLocalMapper.toLocalRecipe(recipe);

        return mRepository.save(recipeLocal);
    }

    @Override
    public void update(Recipe recipe) {
        RecipeLocalMapper mRecipeLocalMapper = new RecipeLocalMapper();
        RecipeLocal recipeLocal = mRecipeLocalMapper.toLocalRecipe(recipe);
        mRepository.update(recipeLocal);
    }

}
