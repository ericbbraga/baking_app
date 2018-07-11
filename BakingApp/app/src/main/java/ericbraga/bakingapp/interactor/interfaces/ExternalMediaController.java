package ericbraga.bakingapp.interactor.interfaces;

public interface ExternalMediaController {

    int STOP = 0;
    int PAUSE = 1;
    int PLAYING = 2;

    interface Callback {
        void onMediaPlayerChangedState(int state);
    }

}
