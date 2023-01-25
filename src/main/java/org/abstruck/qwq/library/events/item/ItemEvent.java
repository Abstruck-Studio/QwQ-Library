package org.abstruck.qwq.library.events.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.abstruck.qwq.library.events.IEvent;

import java.util.List;

/**
 * @author Goulixiaoji
 */
public class ItemEvent implements IEvent {
    public ItemEvent(){

    }

    public static class OnUseEvent extends ItemEvent {
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
    public static class FinishUsingEvent extends ItemEvent {
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
    public static class UseOnBlockEvent extends ItemEvent {
        private ItemUsageContext context;
        public UseOnBlockEvent(ItemUsageContext context){
            this.context = context;
        }

        public ItemUsageContext getContext() {
            return context;
        }
    }
    public static class UseOnEntityEvent extends ItemEvent {
        private ItemStack stack;
        private PlayerEntity player;
        private LivingEntity entity;
        private Hand hand;
        public UseOnEntityEvent(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
            this.stack = stack;
            this.player = player;
            this.entity = entity;
            this.hand = hand;
        }

        public ItemStack getStack() {
            return stack;
        }

        public PlayerEntity getPlayer() {
            return player;
        }

        public LivingEntity getEntity() {
            return entity;
        }

        public Hand getHand() {
            return hand;
        }
    }
    public static class AppendTooltipEvent extends ItemEvent {
        private ItemStack stack;
        private World world;
        private List<Text> tooltip;
        private TooltipContext context;
        public AppendTooltipEvent(ItemStack stack, World world, List<Text> tooltip, TooltipContext context){
            this.stack = stack;
            this.world = world;
            this.tooltip = tooltip;
            this.context = context;
        }

        public ItemStack getStack() {
            return stack;
        }

        public World getWorld() {
            return world;
        }

        public List<Text> getTooltip() {
            return tooltip;
        }

        public TooltipContext getContext() {
            return context;
        }
    }
}
