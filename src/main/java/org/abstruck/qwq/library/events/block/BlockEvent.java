package org.abstruck.qwq.library.events.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.abstruck.qwq.library.event.IEvent;
import org.jetbrains.annotations.Nullable;

/**
 * @author Goulixiaoji
 */
public class BlockEvent implements IEvent {
    private final BlockState state;
    public BlockEvent(BlockState state){
        this.state = state;
    }

    public BlockState getState() {
        return state;
    }

    /**
     * Callback when net.minecraft.block.onBreak is used
     */
    public static class OnBreakBlockEvent extends BlockEvent{
        private final World world;
        private final BlockPos pos;
        private final PlayerEntity player;

        public OnBreakBlockEvent(World world, BlockPos pos, BlockState state, PlayerEntity player){
            super(state);
            this.world = world;
            this.pos = pos;
            this.player = player;
        }

        public World getWorld() {
            return world;
        }

        public BlockPos getPos() {
            return pos;
        }

        public PlayerEntity getPlayer() {
            return player;
        }
    }

    /**
     * Callback when net.minecraft.block.onBroken is used
     */
    public static class OnBrokenBlockEvent extends BlockEvent{
        private final WorldAccess world;
        private final BlockPos pos;
        public OnBrokenBlockEvent(WorldAccess world, BlockPos pos, BlockState state) {
            super(state);
            this.world = world;
            this.pos = pos;
        }

        public WorldAccess getWorld() {
            return world;
        }

        public BlockPos getPos() {
            return pos;
        }
    }

    /**
     * Callback when net.minecraft.block.afterBreak is used
     */
    public static class AfterBreakEvent extends BlockEvent{
        private final World world;
        private final PlayerEntity player;
        private final BlockPos pos;
        private final BlockEntity blockEntity;
        private final ItemStack stack;
        public AfterBreakEvent(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
            super(state);
            this.world = world;
            this.player = player;
            this.pos = pos;
            this.blockEntity = blockEntity;
            this.stack = stack;
        }

        public PlayerEntity getPlayer() {
            return player;
        }

        public BlockPos getPos() {
            return pos;
        }

        public World getWorld() {
            return world;
        }

        public BlockEntity getBlockEntity() {
            return blockEntity;
        }

        public ItemStack getStack() {
            return stack;
        }
    }
}
