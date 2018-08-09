package ericbraga.bakingapp.environment.injectors.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ericbraga.bakingapp.interactor.implementation.NotifyChanges;
import ericbraga.bakingapp.interactor.interfaces.NotifyChangesInteractor;

@Module
public class NotifyModule {

    private Context mContext;

    public NotifyModule(Context context) {
        mContext = context;
    }

    @Provides
    public NotifyChangesInteractor provideNotifyInteractor() {
        return new NotifyChanges(mContext);
    }
}
