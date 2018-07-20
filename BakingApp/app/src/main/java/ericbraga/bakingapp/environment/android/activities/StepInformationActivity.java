package ericbraga.bakingapp.environment.android.activities;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.environment.android.repositories.GlideLoader;
import ericbraga.bakingapp.interactor.implementation.LoadStepContent;
import ericbraga.bakingapp.interactor.implementation.LoadStepsFurtherInfo;
import ericbraga.bakingapp.interactor.implementation.MediaPlayer;
import ericbraga.bakingapp.interactor.interfaces.ExternalMediaController;
import ericbraga.bakingapp.interactor.interfaces.ImageRepository;
import ericbraga.bakingapp.interactor.interfaces.StepContentInteractor;
import ericbraga.bakingapp.interactor.interfaces.StepInteractor;
import ericbraga.bakingapp.model.Step;
import ericbraga.bakingapp.presenter.StepPresenter;
import ericbraga.bakingapp.presenter.interfaces.DisplayStepContract;

public class StepInformationActivity extends AppCompatActivity
        implements DisplayStepContract.View<Drawable>, View.OnClickListener, Player.EventListener {
    private TextView mShortDescription;
    private TextView mDescription;
    private PlayerView mExoPlayerView;
    private ExoPlayer mExoPlayer;
    private ImageView mThumbnailPreview;
    private View mNextWidget;
    private View mPreviousWidget;
    private boolean mPlayingVideo;

    private DisplayStepContract.Presenter<Drawable> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_information);
        List<Step> steps = getIntent().getParcelableArrayListExtra("steps");

        configureViews();

        mPlayingVideo = false;

        ImageRepository<Drawable> repository = new GlideLoader(this);
        StepInteractor<Drawable> stepInteractor = new LoadStepContent<>(repository);
        StepContentInteractor<Drawable> stepContentInteractor =
                new LoadStepsFurtherInfo<>(stepInteractor);

        MediaPlayer mediaPlayer = new MediaPlayer(new ExternalMediaController() {
        });

        mPresenter = new StepPresenter<>(stepContentInteractor, mediaPlayer, steps);
        createExoPlayer();
    }

    private void configureViews() {
        mShortDescription = findViewById(R.id.step_information_short_description);
        mDescription = findViewById(R.id.step_information_description);
        mExoPlayerView = findViewById(R.id.step_information_video);
        mThumbnailPreview = findViewById(R.id.step_information_thumbnail);

        mNextWidget = findViewById(R.id.step_information_next_step);
        mNextWidget.setOnClickListener(this);

        mPreviousWidget = findViewById(R.id.step_information_previous_step);
        mPreviousWidget.setOnClickListener(this);
    }

    private void createExoPlayer() {
        TrackSelector trackSelector = new DefaultTrackSelector();
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.attachView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void displayShortDescription(String shortDescription) {
        mShortDescription.setText(shortDescription);
    }

    @Override
    public void displayDescription(String description) {
        mDescription.setText(description);
    }

    @Override
    public void displayPreview(Drawable image) {
        mThumbnailPreview.setVisibility(View.VISIBLE);
        mThumbnailPreview.setImageDrawable(image);
    }

    @Override
    public void displayVideo(String url) {
        mExoPlayerView.setVisibility(View.VISIBLE);
        mExoPlayerView.setPlayer(mExoPlayer);

        String userAgent = Util.getUserAgent(this,
                getResources().getString(R.string.app_name));
        DefaultHttpDataSourceFactory httpDataSourceFactory =
                new DefaultHttpDataSourceFactory(userAgent);

        MediaSource mediaSource = new ExtractorMediaSource.Factory(httpDataSourceFactory)
                .createMediaSource(Uri.parse(url));

        mExoPlayer.prepare(mediaSource);
        mExoPlayer.setPlayWhenReady(false);
        mExoPlayer.addListener(this);
    }

    @Override
    public void displayDefaultImage() {
        mThumbnailPreview.setVisibility(View.VISIBLE);
        mThumbnailPreview.setImageResource(R.drawable.ic_launcher_foreground);
    }

    @Override
    public void hideVideo() {
        mExoPlayerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hidePreview() {
        mThumbnailPreview.setVisibility(View.INVISIBLE);
    }

    @Override
    public void enablePreviousWidget() {
        mPreviousWidget.setEnabled(true);
    }

    @Override
    public void disablePreviousWidget() {
        mPreviousWidget.setEnabled(false);
    }

    @Override
    public void enableNextWidget() {
        mNextWidget.setEnabled(true);
    }

    @Override
    public void disableNextWidget() {
        mNextWidget.setEnabled(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.step_information_previous_step:
                mPresenter.previousStepWidgetClicked();
                break;
            case R.id.step_information_next_step:
                mPresenter.nextStepWidgetClicked();
                break;

            default:
                throw new IllegalArgumentException("Invalid Handle Click");
        }
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {}

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {}

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        mPlayingVideo = (Player.STATE_READY == playbackState) && playWhenReady;
    }

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
