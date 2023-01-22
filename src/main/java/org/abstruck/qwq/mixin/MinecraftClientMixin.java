package org.abstruck.qwq.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.abstruck.qwq.library.event.reflection.EventManager;
import org.abstruck.qwq.library.events.minecraft.MinecraftEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Goulixiaoji
 */
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Inject(method = "joinWorld", at = @At("RETURN"))
    public void joinWorld(ClientWorld world, CallbackInfo ci){
        EventManager.onEventAction(() -> new MinecraftEvent.JoinWorldEvent(world, (MinecraftClient) (Object) this));
    }
}
