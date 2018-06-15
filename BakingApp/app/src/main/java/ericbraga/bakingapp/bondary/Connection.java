package ericbraga.bakingapp.bondary;

public interface Connection {
    void connect(String url, Callback callback);

    interface Callback {
        void onSuccess(String result);
        void onError(String errorMessage);
    }
}
