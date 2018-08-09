package ericbraga.bakingapp.environment.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkManager {

    private final Context mContext;

    public NetworkManager(Context context) {
        mContext = context;
    }

    public boolean hasConnection() {
        ConnectivityManager connManager =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connManager == null) {
            return false;
        }

        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


}
