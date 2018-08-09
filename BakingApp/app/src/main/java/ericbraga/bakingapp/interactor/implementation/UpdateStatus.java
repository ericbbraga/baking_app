package ericbraga.bakingapp.interactor.implementation;

import android.util.Log;

import ericbraga.bakingapp.environment.interfaces.AsyncWriteRepository;
import ericbraga.bakingapp.interactor.interfaces.NotifyChangesInteractor;
import ericbraga.bakingapp.interactor.interfaces.UpdateStatusInteractor;
import ericbraga.bakingapp.model.Recipe;

public class UpdateStatus implements UpdateStatusInteractor {
    private final NotifyChangesInteractor mNotifyInteractor;
    private AsyncWriteRepository mWriteRepository;

    public UpdateStatus(AsyncWriteRepository writeRepository,
                        NotifyChangesInteractor notifyInteractor) {
        mWriteRepository = writeRepository;
        mNotifyInteractor = notifyInteractor;
    }

    @Override
    public boolean execute(final Recipe recipe) {
        mWriteRepository.update(recipe,
                new AsyncWriteRepository.WriteRepositoryCallback() {
            @Override
            public void onSave() {
                mNotifyInteractor.execute();
            }

            @Override
            public void onError(String message) {
                Log.i("UpdateStatus", "error " + message);
            }
        });

        return true;
    }

    @Override
    public void execute(Recipe recipe, final UpdateStatus.Callback callback) {
        if (callback != null) {
            mWriteRepository.update(recipe, new AsyncWriteRepository.WriteRepositoryCallback() {
                @Override
                public void onSave() {
                    callback.onUpdate();
                }

                @Override
                public void onError(String message) {
                    callback.onError(message);
                }
            });
        }
    }
}
