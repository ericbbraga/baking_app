package ericbraga.bakingapp.environment.widget;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViewsService;

public class RecipeWidgetViewService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.i("RecipeWidgetService", "onGetViewFactory");
        Context context = getApplicationContext();
        return new RecipeRemoteViewFactory(context);
    }
}
