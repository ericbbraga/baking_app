package ericbraga.bakingapp.environment.android.repositories;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import ericbraga.bakingapp.interactor.interfaces.ImageRepository;

public class GlideLoader implements ImageRepository<Drawable> {
    private final Context mContext;

    public GlideLoader(Context context) {
        mContext = context;
    }
    @Override
    public void loadImageFrom(String uri, ImageCallback<Drawable> callback) {

        Glide.with(mContext)
                .load(uri)
                .listener(new GlideRepositoryListener(callback))
                .submit();
    }

    private class GlideRepositoryListener implements RequestListener<Drawable> {
        private final ImageCallback<Drawable> mCallback;

        GlideRepositoryListener(ImageCallback<Drawable> callback) {
            mCallback = callback;
        }

        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                    Target<Drawable> target, boolean isFirstResource) {
            mCallback.onError("Could not load image: " + e.getMessage());
            return false;
        }

        @Override
        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                       DataSource dataSource, boolean isFirstResource) {

            mCallback.onSuccess(resource);
            return true;
        }
    }
}
