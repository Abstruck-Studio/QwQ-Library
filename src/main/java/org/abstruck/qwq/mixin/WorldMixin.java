package org.abstruck.qwq.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.abstruck.qwq.library.event.reflection.EventManager;
import org.abstruck.qwq.library.events.world.WorldEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Goulixiaoji
 */
@Mixin(World.class)
public abstract class WorldMixin {
    @Inject(method = "breakBlock", at = @At("RETURN"))
    public void breakBlock(BlockPos pos, boolean drop, Entity breakingEntity, int maxUpdateDepth, CallbackInfoReturnable<Boolean> cir) {
        World self = (World) (Object) this;
        EventManager.onEventAction(() -> new WorldEvent.BreakBlockEvent(pos, drop, breakingEntity, maxUpdateDepth, self));
    }
}
