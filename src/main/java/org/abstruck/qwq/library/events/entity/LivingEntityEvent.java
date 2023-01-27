package org.abstruck.qwq.library.events.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.abstruck.qwq.library.events.IEvent;

/**
 * @author Goulixiaoji
 */
public class LivingEntityEvent implements IEvent {
    private LivingEntity entity;
    public LivingEntityEvent(LivingEntity entity){
        this.entity = entity;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public static class OnDeathEvent extends LivingEntityEvent {
        private DamageSource source;
        public OnDeathEvent(DamageSource source, LivingEntity entity) {
            super(entity);
            this.source = source;
        }

        public DamageSource getSource() {
            return source;
        }
    }

    public static class OnKilledByEvent extends LivingEntityEvent {
        private LivingEntity adversary;
        public OnKilledByEvent(LivingEntity adversary, LivingEntity entity) {
            super(entity);
            this.adversary = adversary;
        }

        public LivingEntity getAdversary() {
            return adversary;
        }
    }

    public static class TickEvent extends LivingEntityEvent {

        public TickEvent(LivingEntity entity) {
            super(entity);
        }
    }
}
