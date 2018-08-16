package ericbraga.bakingapp.presenter.interfaces;

public interface ExternalMediaContract {

    interface Callback {
        void onMediaPlayerChangedState();
    }

    void setCallback(ExternalMediaContract.Callback callback);
    boolean isPlaying();
    void prepare(String url);
    void stop();
    void release();
    long getCurrentPosition();
    void reset();

}
