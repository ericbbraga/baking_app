package ericbraga.bakingapp.environment.repositories.local.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import ericbraga.bakingapp.environment.repositories.local.database.contract.BakingContract;
import ericbraga.bakingapp.environment.repositories.local.repositories.LocalRecipeRepository;
import ericbraga.bakingapp.model.Ingredient;
import ericbraga.bakingapp.model.Recipe;

public class BakingProvider extends ContentProvider {
    private static final int RECIPES_CODE = 100;
    private static final int INGREDIENTS_CODE = 101;
    private LocalRecipeRepository mRecipeRepository;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(BakingContract.AUTHORITY,
                BakingContract.PATH_RECIPE, RECIPES_CODE);

        matcher.addURI(BakingContract.AUTHORITY,
                BakingContract.PATH_RECIPE + "/"+ BakingContract.PATH_FAVORITE_RECIPES + "/"
                        + BakingContract.INGREDIENTS_RECIPE,
                INGREDIENTS_CODE);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        mRecipeRepository = new LocalRecipeRepository(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor = null;
        switch (sUriMatcher.match(uri)) {

            case RECIPES_CODE:
                cursor = handleRecipes();
                break;

            case INGREDIENTS_CODE:
                cursor = handleIngredients();
                break;
        }

        return cursor;
    }

    private Cursor handleRecipes() {
        MatrixCursor cursor = new MatrixCursor(BakingContract.RecipeEntry.COLUMNS);
        Recipe recipe = getFavoriteRecipe();

        if (recipe != null) {
            cursor.addRow(new Object[]{
                    recipe.getId(),
                    recipe.getName(),
                    recipe.isStarred() ? 1 : 0
            });
        }
        return cursor;
    }

    private Cursor handleIngredients() {
        MatrixCursor cursor = new MatrixCursor(BakingContract.IngredientEntry.COLUMNS);
        Recipe recipe = getFavoriteRecipe();
        if (recipe == null) {
            return cursor;
        }

        List<Ingredient> ingredients = recipe.getIngredients();

        for (Ingredient ingredient : ingredients) {
            cursor.addRow(new Object[] {
                    ingredient.getName(),
                    ingredient.getQuantity(),
                    ingredient.getMeasure()
            });
        }

        return cursor;
    }

    private Recipe getFavoriteRecipe() {
        return mRecipeRepository.getStarredRecipe();
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        return 0;
    }
}
