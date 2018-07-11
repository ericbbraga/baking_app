package ericbraga.bakingapp.interactor.implementation;

import ericbraga.bakingapp.interactor.interfaces.ExternalMediaController;
import ericbraga.bakingapp.interactor.interfaces.MediaInteractor;

public class MediaPlayer implements MediaInteractor {

    private final ExternalMediaController mMediaController;
    private boolean mPlaying;

    public MediaPlayer(ExternalMediaController mediaController) {
        mMediaController = mediaController;
        mPlaying = false;
    }

    @Override
    public boolean isPlaying() {
        return mPlaying;
    }

    @Override
    public void play() {
        //mMediaController.play();
        mPlaying = true;
    }

    @Override
    public void pause() {
        //mMediaController.pause();
        mPlaying = false;
    }

    @Override
    public void stop() {
        //mMediaController.stop();
        mPlaying = false;
    }
}
