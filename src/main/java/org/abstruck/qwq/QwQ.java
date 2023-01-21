package org.abstruck.qwq;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import org.abstruck.qwq.library.event.reflection.EventLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Goulixiaoji
 */
public class QwQ implements ModInitializer {
    public static final String MOD_ID = "qwq";
    public static final Logger LOGGER = LoggerFactory.getLogger(QwQ.class);

    @Override
    public void onInitialize() {
        LOGGER.info("Who stole my strawberry cake!");
    }
}

