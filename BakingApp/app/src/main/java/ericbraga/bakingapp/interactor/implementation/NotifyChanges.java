package ericbraga.bakingapp.interactor.implementation;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import ericbraga.bakingapp.environment.widget.RecipeWidgetProvider;
import ericbraga.bakingapp.interactor.interfaces.NotifyChangesInteractor;

public class NotifyChanges implements NotifyChangesInteractor {

    private final Context mContext;

    public NotifyChanges(Context context) {
        mContext = context;
    }

    @Override
    public void execute() {
        Intent it = new Intent(mContext, RecipeWidgetProvider.class);
        it.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        int[] ids = AppWidgetManager.getInstance(mContext).getAppWidgetIds(
                new ComponentName(mContext, RecipeWidgetProvider.class));
        it.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        mContext.sendBroadcast(it);
    }
}
