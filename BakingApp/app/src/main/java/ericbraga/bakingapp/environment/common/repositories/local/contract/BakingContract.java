package ericbraga.bakingapp.environment.common.repositories.local.contract;

import android.net.Uri;
import android.provider.BaseColumns;

public final class BakingContract {
    private BakingContract() {}

    private static final String AUTHORITY = "ericbraga.bakingapp";

    public static final Uri BASE_CONTENT_URI = Uri.parse(String.format("content://%s",AUTHORITY));

    public static final String PATH_RECIPE = "recipes";

    public static class RecipeEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_RECIPE)
                .build();

        public static final String TABLE_NAME = "RecipeLocal";
        public static final String NAME_COLUMN = "name";

        public static final String[] COLUMNS = new String[] {
          _ID,
          NAME_COLUMN
        };
    }

    public static class IngredientEntry implements BaseColumns {
        public static final String TABLE_NAME = "IngredientLocal";
        public static final String NAME_COLUMN = "name";
        public static final String QUANTITY_COLUMN = "quantity";
        public static final String MEASURE_COLUMN = "measure";

        public static final String[] COLUMNS = new String[] {
                _ID,
                NAME_COLUMN,
                QUANTITY_COLUMN,
                MEASURE_COLUMN
        };
    }

    public static class StepEntry implements BaseColumns {
        public static final String TABLE_NAME = "StepLocal";
        public static final String ORDER_SEQUENCY_COLUMN = "order_sequency";
        public static final String SHORT_DESCRIPTION_COLUMN = "short_description";
        public static final String DESCRIPTION_COLUMN = "description";
        public static final String VIDEO_COLUMN = "video_url";
        public static final String THUMBNAIL_COLUMN = "thumbnail_url";

        public static final String[] COLUMNS = new String[] {
                _ID,
                ORDER_SEQUENCY_COLUMN,
                SHORT_DESCRIPTION_COLUMN,
                DESCRIPTION_COLUMN,
                VIDEO_COLUMN,
                THUMBNAIL_COLUMN
        };
    }

    public static class RecipeIngredientEntry implements BaseColumns {
        public static final String TABLE_NAME = "Recipe_Ingredient";
        public static final String RECIPE_ID = "recipe_id";
        public static final String INGREDIENT_ID = "ingredient_id";
        public static final String RECIPE_REFERENCE = RecipeEntry.TABLE_NAME;
        public static final String INGREDIENT_REFERENCE = IngredientEntry.TABLE_NAME;
        public static final String RECIPE_REFERENCE_ID = RecipeEntry._ID;
        public static final String INGREDIENT_REFERENCE_ID = IngredientEntry._ID;

        public static final String[] COLUMNS = new String[] {
                _ID,
                RECIPE_ID,
                INGREDIENT_ID
        };
    }

    public static class RecipeStepEntry implements BaseColumns {
        public static final String TABLE_NAME = "Recipe_Step";
        public static final String RECIPE_ID = "recipe_id";
        public static final String STEP_ID = "step_id";
        public static final String RECIPE_REFERENCE = RecipeEntry.TABLE_NAME;
        public static final String STEP_REFERENCE = StepEntry.TABLE_NAME;
        public static final String RECIPE_REFERENCE_ID = RecipeEntry._ID;
        public static final String STEP_REFERENCE_ID = StepEntry._ID;

        public static final String[] COLUMNS = new String[] {
                _ID,
                RECIPE_ID,
                STEP_ID
        };
    }
}
