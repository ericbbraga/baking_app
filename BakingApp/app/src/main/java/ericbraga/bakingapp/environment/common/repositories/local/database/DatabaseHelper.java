package ericbraga.bakingapp.environment.common.repositories.local.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ericbraga.bakingapp.environment.common.repositories.local.contract.BakingContract;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, String database, int version) {
        super(context, database, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createRecipeTable(sqLiteDatabase);
        createIngredientTable(sqLiteDatabase);
        createStepTable(sqLiteDatabase);

        createReferenceTables(sqLiteDatabase);
    }

    private void createRecipeTable(SQLiteDatabase sqLiteDatabase) {
        String recipeSqlCreate = String.format(
                    "CREATE TABLE %s (" +
                            "%s INTEGER PRIMARY KEY, " +
                            "%s VARCHAR(100)" +
                            ")",
                    BakingContract.RecipeEntry.TABLE_NAME,
                    BakingContract.RecipeEntry._ID,
                    BakingContract.RecipeEntry.NAME_COLUMN
            );

        sqLiteDatabase.execSQL(recipeSqlCreate);
    }

    private void createIngredientTable(SQLiteDatabase sqLiteDatabase) {
        String ingredientSqlCreate = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY, " +
                        "%s VARCHAR(100), " +
                        "%s REAL, " +
                        "%s VARCHAR(1000)" +
                        ")",
                BakingContract.IngredientEntry.TABLE_NAME,
                BakingContract.IngredientEntry._ID,
                BakingContract.IngredientEntry.NAME_COLUMN,
                BakingContract.IngredientEntry.QUANTITY_COLUMN,
                BakingContract.IngredientEntry.MEASURE_COLUMN
        );

        sqLiteDatabase.execSQL(ingredientSqlCreate);
    }

    private void createStepTable(SQLiteDatabase sqLiteDatabase) {
        String stepsSqlCreate = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY, " +
                        "%s INTEGER, " +
                        "%s VARCHAR(200), " +
                        "%s VARCHAR(250), " +
                        "%s VARCHAR(300), " +
                        "%s VARCHAR(300)" +
                        ")",
                BakingContract.StepEntry.TABLE_NAME,
                BakingContract.StepEntry._ID,
                BakingContract.StepEntry.ORDER_SEQUENCY_COLUMN,
                BakingContract.StepEntry.SHORT_DESCRIPTION_COLUMN,
                BakingContract.StepEntry.DESCRIPTION_COLUMN,
                BakingContract.StepEntry.VIDEO_COLUMN,
                BakingContract.StepEntry.THUMBNAIL_COLUMN
        );

        sqLiteDatabase.execSQL(stepsSqlCreate);
    }

    private void createReferenceTables(SQLiteDatabase sqLiteDatabase) {
        String recipeIngredientSqlCreate = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY, " +
                        "%s INTEGER REFERENCES %s (%s), " +
                        "%s INTEGER REFERENCES %s (%s)" +
                        ")",
                BakingContract.RecipeIngredientEntry.TABLE_NAME,
                BakingContract.RecipeIngredientEntry._ID,
                BakingContract.RecipeIngredientEntry.RECIPE_ID,
                BakingContract.RecipeIngredientEntry.RECIPE_REFERENCE,
                BakingContract.RecipeIngredientEntry.RECIPE_REFERENCE_ID,
                BakingContract.RecipeIngredientEntry.INGREDIENT_ID,
                BakingContract.RecipeIngredientEntry.INGREDIENT_REFERENCE,
                BakingContract.RecipeIngredientEntry.INGREDIENT_REFERENCE_ID
        );


        sqLiteDatabase.execSQL(recipeIngredientSqlCreate);

        String recipeStepSqlCreate = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY, " +
                        "%s INTEGER REFERENCES %s (%s), " +
                        "%s INTEGER REFERENCES %s (%s)" +
                        ")",
                BakingContract.RecipeStepEntry.TABLE_NAME,
                BakingContract.RecipeStepEntry._ID,
                BakingContract.RecipeStepEntry.RECIPE_ID,
                BakingContract.RecipeStepEntry.RECIPE_REFERENCE,
                BakingContract.RecipeStepEntry.RECIPE_REFERENCE_ID,
                BakingContract.RecipeStepEntry.STEP_ID,
                BakingContract.RecipeStepEntry.STEP_REFERENCE,
                BakingContract.RecipeStepEntry.STEP_REFERENCE_ID
        );

        sqLiteDatabase.execSQL(recipeStepSqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        onCreate(sqLiteDatabase);
    }
}
