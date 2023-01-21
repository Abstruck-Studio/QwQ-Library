package org.abstruck.qwq.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.abstruck.qwq.library.event.reflection.EventManager;
import org.abstruck.qwq.library.events.block.BlockEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Goulixiaoji
 */
@Mixin(Block.class)
public abstract class BlockMixin {
    @Inject(method = "onBreak", at = @At("RETURN"))
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo ci){
        EventManager.onEventAction(() -> new BlockEvent.OnBreakEvent(world, pos, state, player));
    }

    @Inject(method = "onBroken", at = @At("RETURN"))
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state, CallbackInfo ci){
        EventManager.onEventAction(() -> new BlockEvent.OnBrokenEvent(world, pos, state));
    }

    @Inject(method = "afterBreak", at = @At("RETURN"))
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack, CallbackInfo ci){
        EventManager.onEventAction(() -> new BlockEvent.AfterBreakEvent(world, player, pos, state, blockEntity, stack));
    }
}
