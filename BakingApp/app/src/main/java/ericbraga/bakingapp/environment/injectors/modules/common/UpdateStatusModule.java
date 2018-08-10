package ericbraga.bakingapp.environment.injectors.modules.common;

import dagger.Module;
import dagger.Provides;
import ericbraga.bakingapp.environment.interfaces.AsyncWriteRepository;
import ericbraga.bakingapp.interactor.implementation.UpdateStatus;
import ericbraga.bakingapp.interactor.interfaces.NotifyChangesInteractor;
import ericbraga.bakingapp.interactor.interfaces.UpdateStatusInteractor;

@Module
public class UpdateStatusModule {

    @Provides
    public UpdateStatusInteractor provideUpdateInterator(AsyncWriteRepository writeRepository,
                                                         NotifyChangesInteractor notifyChanges) {
        return new UpdateStatus(writeRepository, notifyChanges);
    }

}
