package ericbraga.bakingapp.android.bondary;

import java.io.IOException;

import ericbraga.bakingapp.bondary.Connection;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebConnection implements Connection {

    private final OkHttpClient mClient;

    public WebConnection() {
        mClient = new OkHttpClient();
    }

    @Override
    public void connect(String url, final Callback callback) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("Url is null");
        }

        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

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
