package org.abstruck.qwq.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.abstruck.qwq.library.event.reflection.EventManager;
import org.abstruck.qwq.library.events.item.ItemEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Goulixiaoji
 */
@Mixin(Item.class)
public abstract class ItemMixin {
    @Inject(method = "use", at = @At("RETURN"))
    public void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir){
        EventManager.onEventAction(() -> new ItemEvent.OnUseEvent(world, user, hand));
    }

    @Inject(method = "finishUsing", at = @At("RETURN"))
    public void finishUsing(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir){
        EventManager.onEventAction(() -> new ItemEvent.FinishUsingEvent(stack, world, user));
    }
}
