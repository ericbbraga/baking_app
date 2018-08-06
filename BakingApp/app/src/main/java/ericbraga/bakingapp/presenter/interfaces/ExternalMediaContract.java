package ericbraga.bakingapp.presenter.interfaces;

public interface ExternalMediaContract {

    interface Callback {
        void onMediaPlayerChangedState(boolean playing);
    }

    void setCallback(ExternalMediaContract.Callback callback);
    void prepare(String url);
    void stop();
    void release();

}
