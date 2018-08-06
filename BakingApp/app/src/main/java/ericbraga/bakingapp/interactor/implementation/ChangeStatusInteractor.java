package ericbraga.bakingapp.interactor.implementation;

import android.content.Context;
import android.util.Log;

import ericbraga.bakingapp.environment.interfaces.AsyncWriteRepository;
import ericbraga.bakingapp.interactor.interfaces.NotifyChangesInteractor;
import ericbraga.bakingapp.interactor.interfaces.UpdateStatusInteractor;
import ericbraga.bakingapp.model.Recipe;

public class ChangeStatusInteractor implements UpdateStatusInteractor {
    private final NotifyChangesInteractor mNotifyInteractor;
    private AsyncWriteRepository mWriteRepository;

    public ChangeStatusInteractor(AsyncWriteRepository writeRepository,
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
                Log.i("ChangeStatusInteractor", "error " + message);
            }
        });

        return true;
    }

    @Override
    public void execute(Recipe recipe, final ChangeStatusInteractor.Callback callback) {
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
