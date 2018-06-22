package ericbraga.bakingapp.environment.boundary.common;

import java.io.IOException;

import ericbraga.bakingapp.environment.boundary.common.interfaces.Connection;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebConnection implements Connection {

    private final String mUrl;

    public WebConnection(String url) {
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
                    callback.onSuccess(response.body().toString());
                }
            });

        } catch(IllegalArgumentException e) {
            callback.onError(e.getMessage());
        }
    }
}
