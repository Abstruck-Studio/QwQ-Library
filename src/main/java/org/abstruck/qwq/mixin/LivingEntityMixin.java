package org.abstruck.qwq.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.abstruck.qwq.library.event.reflection.EventManager;
import org.abstruck.qwq.library.events.entity.LivingEntityEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Goulixiaoji
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "onDeath", at = @At("RETURN"))
    public void onDeath(DamageSource source, CallbackInfo ci){
        LivingEntity self = (LivingEntity) (Object) this;
        EventManager.onEventAction(() -> new LivingEntityEvent.OnDeathEvent(source, self));
    }

    @Inject(method = "onKilledBy", at = @At("RETURN"))
    public void onKilledBy(LivingEntity adversary, CallbackInfo ci){
        LivingEntity self = (LivingEntity) (Object) this;
        EventManager.onEventAction(() -> new LivingEntityEvent.OnKilledByEvent(adversary, self));
    }
}
