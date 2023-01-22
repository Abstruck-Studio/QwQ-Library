package org.abstruck.qwq.library.registries;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Goulixiaoji
 */
public class Registration<T> {
    private final DefaultedRegistry<T> REGISTER;
    private final String MOD_ID;
    private final Map<Identifier, T> OBJECT_MAP = new HashMap<>();

    public Registration(DefaultedRegistry<T> register, String modId){
        this.REGISTER = register;
        this.MOD_ID = modId;
    }

    public T register(String regId, Supplier<T> object){
        OBJECT_MAP.put(new Identifier(MOD_ID, regId), object.get());
        return object.get();
    }

    public void register(){
        OBJECT_MAP.forEach((regId, object) -> Registry.register(REGISTER, regId, object));
    }
}
