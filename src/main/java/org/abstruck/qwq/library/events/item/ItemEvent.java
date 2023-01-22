package org.abstruck.qwq.library.events.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.abstruck.qwq.library.events.IEvent;

/**
 * @author Goulixiaoji
 */
public class ItemEvent implements IEvent {
    public ItemEvent(){

    }

    public static class OnUseEvent extends ItemEvent{
        private World world;
        private PlayerEntity player;
        private Hand hand;
        public OnUseEvent(World world, PlayerEntity user, Hand hand){
            this.world = world;
            this.player = user;
            this.hand = hand;
        }

        public World getWorld() {
            return world;
        }

        public PlayerEntity getPlayer() {
            return player;
        }

        public Hand getHand() {
            return hand;
        }
    }

    public static class FinishUsingEvent extends ItemEvent{
        private ItemStack stack;
        private World world;
        private LivingEntity entity;
        public FinishUsingEvent(ItemStack stack, World world, LivingEntity user){
            this.stack = stack;
            this.world = world;
            this.entity = user;
        }

        public ItemStack getStack() {
            return stack;
        }

        public World getWorld() {
            return world;
        }

        public LivingEntity getEntity() {
            return entity;
        }
    }
}
