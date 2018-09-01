package ericbraga.bakingapp.environment.injectors.modules.common;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ericbraga.bakingapp.interactor.implementation.NotifyChanges;
import ericbraga.bakingapp.interactor.interfaces.NotifyChangesInteractor;

@Module
public class NotifyModule {

    @Provides
    public NotifyChangesInteractor provideNotifyInteractor(Context context) {
        return new NotifyChanges(context);
    }
}
