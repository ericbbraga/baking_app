package ericbraga.bakingapp.environment.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.environment.repositories.local.database.contract.BakingContract;
import ericbraga.bakingapp.environment.widget.model.RecipeWidget;

public class ServiceRecipeWidget extends IntentService {

    public static final String WIDGET_ID = "widget_id";

    public ServiceRecipeWidget() {
        super("ServiceRecipeWidget");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            Bundle extras =  intent.getExtras();

            if (extras != null) {
                int appWidgetId = extras.getInt(WIDGET_ID, 0);
                RecipeWidget recipe = updateRecipe(this);

                String title = recipe == null ?
                    getString(R.string.no_favorite_recipe) : recipe.getName();
                RemoteViews views = new RemoteViews(getPackageName(), R.layout.recipe_widget);
                views.setTextViewText(R.id.title_recipe_widget, title);

                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
                appWidgetManager.updateAppWidget(appWidgetId, views);
            }


        }
    }

    private RecipeWidget updateRecipe(Context context) {
        RecipeWidget recipe = null;

        Cursor cursor = context.getContentResolver().query(
                BakingContract.RecipeEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            long id = cursor.getLong(cursor.getColumnIndex(BakingContract.RecipeEntry._ID));
            String name = cursor.getString(
                    cursor.getColumnIndex(BakingContract.RecipeEntry.NAME_COLUMN)
            );

            recipe = new RecipeWidget(id, name);
            cursor.close();
        }

        return recipe;
    }
}
