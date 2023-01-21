package org.abstruck.qwq.library.events.minecraft;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.abstruck.qwq.library.events.IEvent;

/**
 * @author Goulixiaoji
 */
public class MinecraftEvent implements IEvent {
    private MinecraftClient minecraft;

    MinecraftEvent (MinecraftClient minecraft){
        this.minecraft = minecraft;
    }

    public MinecraftClient getMinecraft() {
        return minecraft;
    }

    public static class JoinWorldEvent extends MinecraftEvent {
        private ClientWorld world;

        public JoinWorldEvent(ClientWorld world, MinecraftClient minecraft) {
            super(minecraft);
            this.world = world;
        }

        public ClientWorld getWorld() {
            return world;
        }
    }
}
