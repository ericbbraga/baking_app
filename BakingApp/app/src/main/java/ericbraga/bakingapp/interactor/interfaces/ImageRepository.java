package ericbraga.bakingapp.interactor.interfaces;

public interface ImageRepository<T> {
    interface ImageCallback<T> {
        void onSuccess(T image);
        void onError(String message);
    }

    void loadImageFrom(String url, ImageCallback<T> callback);
}
