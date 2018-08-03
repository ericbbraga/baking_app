package ericbraga.bakingapp.environment.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViewsService;

public class RecipeWidgetViewService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Context context = getApplicationContext();
        return new RecipeRemoteViewFactory(context);
    }
}
