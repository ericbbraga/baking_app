package ericbraga.bakingapp.environment.common.repositories.web;

import java.io.IOException;

import ericbraga.bakingapp.environment.common.interfaces.Connection;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class WebConnection implements Connection {

    private final String mUrl;

    WebConnection(String url) {
        mUrl = url;
    }

    @Override
    public void connect(final Callback callback) {
        if (mUrl == null || mUrl.isEmpty()) {
            throw new IllegalArgumentException("Url is null");
        }

        try {
            Request request = new Request.Builder()
                    .url(mUrl)
                    .build();

            OkHttpClient mClient = new OkHttpClient();
            mClient.newCall(request).enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    callback.onError(e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    callback.onSuccess(response.body().string());
                }
            });

        } catch(IllegalArgumentException e) {
            callback.onError(e.getMessage());
        }
    }
}
