package ericbraga.bakingapp.interactor.implementation;

import ericbraga.bakingapp.interactor.interfaces.ImageRepository;
import ericbraga.bakingapp.interactor.interfaces.StepInteractor;
import ericbraga.bakingapp.model.Step;

public class LoadStepContent<T> implements StepInteractor<T>, ImageRepository.ImageCallback<T> {

    private final ImageRepository<T> mRepository;
    private Callback<T> mOuterCallback;

    public LoadStepContent(ImageRepository<T> repository) {
        mRepository = repository;
    }

    @Override
    public void loadStepInformation(Step step, Callback<T> callback) {
        mOuterCallback = callback;

        String background = getBackground(step);
        if (background != null) {
            mRepository.loadImageFrom(background, this);
        } else {
            mOuterCallback.displayFallbackImage();
        }
    }

    private String getBackground(Step step) {
        String background = null;

        String thumbnail = step.getThumbnailUrl();
        String videoUrl = step.getVideoUrl();

        if (thumbnail != null && !thumbnail.trim().isEmpty()) {
            background = thumbnail;
        } else if (videoUrl != null && !videoUrl.trim().isEmpty()) {
            background = videoUrl;
        }

        return background;
    }

    @Override
    public void onSuccess(T image) {
        mOuterCallback.onSuccess(image);
    }

    @Override
    public void onError(String message) {
        mOuterCallback.onError(message);
    }
}
