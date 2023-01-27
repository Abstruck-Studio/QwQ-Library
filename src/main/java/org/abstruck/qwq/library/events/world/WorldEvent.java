package org.abstruck.qwq.library.events.world;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.abstruck.qwq.library.events.IEvent;

/**
 * @author Goulixiaoji
 */
public class WorldEvent implements IEvent {
    private World world;
    public WorldEvent(World world) {
        this.world = world;
    }

    public static class BreakBlockEvent extends WorldEvent{
        private BlockPos pos;
        private boolean drop;
        private Entity breakingEntity;
        private int maxUpdateDepth;
        public BreakBlockEvent(BlockPos pos, boolean drop, Entity breakingEntity, int maxUpdateDepth, World world) {
            super(world);
            this.pos = pos;
            this.drop = drop;
            this.breakingEntity = breakingEntity;
            this.maxUpdateDepth = maxUpdateDepth;
        }

        public BlockPos getPos() {
            return pos;
        }

        public boolean isDrop() {
            return drop;
        }

        public Entity getBreakingEntity() {
            return breakingEntity;
        }

        public int getMaxUpdateDepth() {
            return maxUpdateDepth;
        }
    }
}
