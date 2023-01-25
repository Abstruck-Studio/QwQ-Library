package org.abstruck.qwq.library.events.player;

import net.minecraft.entity.player.PlayerEntity;
import org.abstruck.qwq.library.events.IEvent;

/**
 * @author Goulixiaoji
 */
public class PlayerEvent implements IEvent {
    private PlayerEntity player;

    public PlayerEvent(PlayerEntity player){
        this.player = player;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public static class WakeUpEvent extends PlayerEvent{
        private boolean skipSleepTimer;
        private boolean updateSleepingPlayers;

        public WakeUpEvent(boolean skipSleepTimer, boolean updateSleepingPlayers, PlayerEntity player) {
            super(player);
            this.skipSleepTimer = skipSleepTimer;
            this.updateSleepingPlayers = updateSleepingPlayers;
        }

        public boolean isUpdateSleepingPlayers() {
            return updateSleepingPlayers;
        }

        public boolean isSkipSleepTimer() {
            return skipSleepTimer;
        }
    }
}
