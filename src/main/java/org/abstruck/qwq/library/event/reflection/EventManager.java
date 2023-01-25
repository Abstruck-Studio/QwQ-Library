package org.abstruck.qwq.library.event.reflection;

import org.abstruck.qwq.QwQ;
import org.abstruck.qwq.library.events.IEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

/**
 * @author Goulixiaoji
 */
public class EventManager extends EventHandle{

    public static void onEventAction(Supplier<? extends IEvent> onEvent) {
        for (MethodWithClass methodWithClass : EVENT_METHODS){
            Method method = methodWithClass.getMethod();
            Class<?>[] parameter = method.getParameterTypes();

            if (onEvent.get().getClass().equals(parameter[0])){
                method.setAccessible(true);
                try {
                    method.invoke(methodWithClass.getKlass(), onEvent.get());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    QwQ.LOGGER.error(e.getMessage());
                }
            }
        }
    }

}
