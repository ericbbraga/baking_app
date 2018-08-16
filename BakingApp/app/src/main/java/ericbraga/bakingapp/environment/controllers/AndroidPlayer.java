package ericbraga.bakingapp.environment.controllers;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import ericbraga.bakingapp.environment.interfaces.PlayerViewContract;
import ericbraga.bakingapp.presenter.interfaces.ExternalMediaContract;

public class AndroidPlayer implements ExternalMediaContract, PlayerViewContract {

    private PlayerView mPlayerView;
    private ExternalMediaContract.Callback mCallback;
    private ExoPlayer mExoPlayer;
    private boolean mShouldStartPlaying;
    private long mStartAt;
    private final ExtractorMediaSource.Factory mFactory;
    private boolean mPlaying;

    public AndroidPlayer(Context context, String applicationName,
                         boolean shouldStartPlaying, long startAt) {
        mShouldStartPlaying = shouldStartPlaying;
        mStartAt = startAt;

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelector trackSelector = new DefaultTrackSelector(bandwidthMeter);
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector);

        String userAgent = Util.getUserAgent(context, applicationName);
        DefaultHttpDataSourceFactory httpDataSourceFactory =
            new DefaultHttpDataSourceFactory(userAgent);

        mFactory = new ExtractorMediaSource.Factory(httpDataSourceFactory);
        mPlaying = false;
    }

    @Override
    public void setCallback(ExternalMediaContract.Callback callback) {
        mCallback = callback;
    }

    @Override
    public boolean isPlaying() {
        return mPlaying;
    }

    @Override
    public void setPlayerView(PlayerView playerView) {
        mPlayerView = playerView;
    }

    @Override
    public void prepare(String url) {
        MediaSource mediaSource = mFactory.createMediaSource(Uri.parse(url));

        mExoPlayer.prepare(mediaSource);
        if (mShouldStartPlaying) {
            mExoPlayer.seekTo(mStartAt);
        }

        mExoPlayer.setPlayWhenReady(mShouldStartPlaying);
        mExoPlayer.addListener(this);


        if (mPlayerView != null) {
            mPlayerView.setPlayer(mExoPlayer);
        }
    }

    @Override
    public void stop() {
        mExoPlayer.stop();
    }

    @Override
    public void release() {
        if (mPlayerView != null) {
            mPlayerView.setPlayer(null);
            mPlayerView = null;
        }

        if (mExoPlayer != null) {
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @Override
    public long getCurrentPosition() {
        return mExoPlayer.getCurrentPosition();
    }

    @Override
    public void reset() {
        mShouldStartPlaying = false;
        mStartAt = 0;
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        mPlaying = (Player.STATE_READY == playbackState) && playWhenReady;
        if (mCallback != null) {
            mCallback.onMediaPlayerChangedState();
        }
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {}

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {}

    @Override
    public void onLoadingChanged(boolean isLoading) {}

    @Override
    public void onRepeatModeChanged(int repeatMode) {}

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {}

    @Override
    public void onPlayerError(ExoPlaybackException error) {}

    @Override
    public void onPositionDiscontinuity(int reason) {}

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {}

    @Override
    public void onSeekProcessed() {}
}
