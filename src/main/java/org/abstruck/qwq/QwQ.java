package org.abstruck.qwq;

import net.fabricmc.api.ModInitializer;
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
        LOGGER.info("[QwQ] Who stole my strawberry cake!");
        EventLoader.initEvent("std.init.event");
    }
}

