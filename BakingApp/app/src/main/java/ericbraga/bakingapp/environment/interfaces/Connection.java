package ericbraga.bakingapp.environment.interfaces;

public interface Connection {
    void connect(Callback callback);

    interface Callback {
        void onSuccess(String result);
        void onError(String errorMessage);
    }
}
