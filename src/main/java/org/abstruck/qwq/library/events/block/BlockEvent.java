package org.abstruck.qwq.library.events.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.abstruck.qwq.library.events.IEvent;
import org.jetbrains.annotations.Nullable;

/**
 * @author Goulixiaoji
 */
public class BlockEvent implements IEvent {
    private BlockState state;
    public BlockEvent(BlockState state){
        this.state = state;
    }

    public BlockState getState() {
        return state;
    }

    public static class OnBreakEvent extends BlockEvent{
        private World world;
        private BlockPos pos;
        private PlayerEntity player;

        public OnBreakEvent(World world, BlockPos pos, BlockState state, PlayerEntity player){
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

    public static class OnBrokenEvent extends BlockEvent{
        private WorldAccess world;
        private BlockPos pos;
        public OnBrokenEvent(WorldAccess world, BlockPos pos, BlockState state) {
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

    public static class AfterBreakEvent extends BlockEvent{
        private World world;
        private PlayerEntity player;
        private BlockPos pos;
        private BlockEntity blockEntity;
        private ItemStack stack;
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
