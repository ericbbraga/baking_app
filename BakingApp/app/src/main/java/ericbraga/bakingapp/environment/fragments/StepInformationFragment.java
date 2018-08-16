package ericbraga.bakingapp.environment.fragments;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.PlayerView;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.environment.interfaces.PlayerViewContract;
import ericbraga.bakingapp.presenter.interfaces.DisplayStepContract;

public class StepInformationFragment extends Fragment
        implements DisplayStepContract.View<Drawable>, View.OnClickListener {

    private TextView mShortDescription;
    private TextView mDescription;
    private PlayerView mExoPlayerView;
    private ImageView mThumbnailPreview;
    private View mNextWidget;
    private View mPreviousWidget;
    private DisplayStepContract.Presenter<Drawable> mPresenter;
    private PlayerViewContract mPlayerViewContract;
    private int mStepPosition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.step_information_fragment, container, false);
        configureViews(root);
        return root;
    }

    private void configureViews(View root) {
        mShortDescription = root.findViewById(R.id.step_information_short_description);
        mDescription = root.findViewById(R.id.step_information_description);
        mExoPlayerView = root.findViewById(R.id.step_information_video);
        mThumbnailPreview = root.findViewById(R.id.step_information_thumbnail);

        mNextWidget = root.findViewById(R.id.step_information_next_step);
        mPreviousWidget = root.findViewById(R.id.step_information_previous_step);

        configureEvents();
    }

    private void configureEvents() {
        if (mNextWidget != null) {
            mNextWidget.setOnClickListener(this);
        }

        if (mPreviousWidget != null) {
            mPreviousWidget.setOnClickListener(this);
        }
    }

    public void setPlayerViewContract(PlayerViewContract playerViewContract) {
        mPlayerViewContract = playerViewContract;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPlayerViewContract != null) {
            mPlayerViewContract.setPlayerView(mExoPlayerView);
        }

        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            detachPresenter();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            detachPresenter();
        }
    }

    private void detachPresenter() {
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }

    public void setPresenter(DisplayStepContract.Presenter<Drawable> presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
    }

    public int getCurrentIndex() {
        return mPresenter.getCurrentStepIndex();
    }

    public boolean isPlaying() {
        return mPresenter.isPlaying();
    }

    public long getCurrentPosition() {
        return mPresenter.getCurrentPosition();
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
    public void displayShortDescription(String shortDescription) {
        if (mShortDescription != null) {
            mShortDescription.setText(shortDescription);
        }
    }

    @Override
    public void displayDescription(String description) {
        if (mDescription != null) {
            mDescription.setText(description);
        }
    }

    @Override
    public void displayPreview(Drawable image) {
        if (mThumbnailPreview != null) {
            mThumbnailPreview.setVisibility(View.VISIBLE);
            mThumbnailPreview.setImageDrawable(image);
        }
    }

    @Override
    public void displayVideo() {
        mExoPlayerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeAllRecipePreviewElements() {
        mThumbnailPreview.setVisibility(View.GONE);
        mExoPlayerView.setVisibility(View.GONE);
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
        if (mPreviousWidget != null) {
            mPreviousWidget.setEnabled(true);
        }
    }

    @Override
    public void disablePreviousWidget() {
        if (mPreviousWidget != null) {
            mPreviousWidget.setEnabled(false);
        }
    }

    @Override
    public void enableNextWidget() {
        if (mNextWidget != null) {
            mNextWidget.setEnabled(true);
        }
    }

    @Override
    public void disableNextWidget() {
        if (mNextWidget != null) {
            mNextWidget.setEnabled(false);
        }
    }

    public void setStepPosition(int stepPosition) {
        mPresenter.setCurrentStepIndex(stepPosition);
    }
}
