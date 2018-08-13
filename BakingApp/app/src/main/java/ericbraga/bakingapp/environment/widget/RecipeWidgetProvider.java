package ericbraga.bakingapp.environment.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import ericbraga.bakingapp.R;

public class RecipeWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private void updateAppWidget(Context context,
                                 AppWidgetManager appWidgetManager, int appWidgetId) {
        refreshTitle(context, appWidgetId);
        refreshIngredientsList(context, appWidgetManager, appWidgetId);
    }

    private void refreshTitle(Context context, int appWidgetId) {
        Intent it = new Intent(context, ServiceRecipeWidget.class);
        it.putExtra(ServiceRecipeWidget.WIDGET_ID, appWidgetId);
        context.startService(it);
    }

    private void refreshIngredientsList(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);

        Intent it = new Intent(context, RecipeWidgetViewService.class);
        views.setRemoteAdapter(R.id.recipe_ingredients_list, it);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}

