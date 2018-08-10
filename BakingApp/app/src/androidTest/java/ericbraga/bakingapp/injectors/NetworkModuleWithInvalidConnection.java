package ericbraga.bakingapp.injectors;

import android.content.Context;

import ericbraga.bakingapp.environment.injectors.modules.common.NetworkModule;
import ericbraga.bakingapp.environment.util.NetworkManager;

public class NetworkModuleWithInvalidConnection extends NetworkModule {
    private Context mContext;

    public NetworkModuleWithInvalidConnection(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public NetworkManager provideNetworkManager() {
        return new NetworkManagerWithoutConnection(mContext);
    }
}

class NetworkManagerWithoutConnection extends NetworkManager {
    NetworkManagerWithoutConnection(Context context) {
        super(context);
    }

    @Override
    public boolean hasConnection() {
        return false;
    }
}