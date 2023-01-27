package org.abstruck.qwq.mixin;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.abstruck.qwq.library.event.reflection.EventManager;
import org.abstruck.qwq.library.events.command.CommandEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Goulixiaoji
 */
@Mixin(CommandManager.class)
public abstract class CommandManagerMixin {
    @Shadow
    @Final
    private CommandDispatcher<ServerCommandSource> dispatcher;

    @Inject(at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/CommandDispatcher;findAmbiguities(Lcom/mojang/brigadier/AmbiguityConsumer;)V"), method = "<init>")
    private void qwqInit(CommandManager.RegistrationEnvironment environment, CallbackInfo ci) {
        EventManager.onEventAction(() -> new CommandEvent.RegisterEvent(this.dispatcher, environment == CommandManager.RegistrationEnvironment.DEDICATED));
    }
}
