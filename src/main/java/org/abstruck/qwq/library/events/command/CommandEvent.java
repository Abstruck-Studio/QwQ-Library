package org.abstruck.qwq.library.events.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;
import org.abstruck.qwq.library.events.IEvent;

/**
 * @author Goulixiaoji
 */
public class CommandEvent implements IEvent {
    public static class RegisterEvent extends CommandEvent{
        private CommandDispatcher<ServerCommandSource> dispatcher;
        private boolean dedicated;

        public RegisterEvent(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
            this.dispatcher = dispatcher;
            this.dedicated = dedicated;
        }

        public CommandDispatcher<ServerCommandSource> getDispatcher() {
            return dispatcher;
        }

        public boolean isDedicated() {
            return dedicated;
        }
    }
}
