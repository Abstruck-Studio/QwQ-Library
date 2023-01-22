package org.abstruck.qwq.mixin;

import net.minecraft.entity.player.PlayerEntity;
import org.abstruck.qwq.library.event.reflection.EventManager;
import org.abstruck.qwq.library.events.player.PlayerEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Goulixiaoji
 */
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(method = "wakeUp(ZZ)V", at = @At("RETURN"))
    public void wakeUp(boolean skipSleepTimer, boolean updateSleepingPlayers, CallbackInfo ci){
        EventManager.onEventAction(() -> new PlayerEvent.PlayerWakeUpEvent(skipSleepTimer, updateSleepingPlayers, (PlayerEntity) (Object) this));
    }
}
