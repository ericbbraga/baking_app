package ericbraga.bakingapp.environment.interfaces;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerView;

public interface PlayerViewContract extends Player.EventListener{
    void setPlayerView(PlayerView playerView);
}
