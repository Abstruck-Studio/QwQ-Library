package org.abstruck.qwq.library.event.asm;

import org.abstruck.qwq.QwQ;
import org.abstruck.qwq.library.event.IEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Goulixiaoji
 */
public class EventManager {
    public static final List<ClassWithMethod> TASK_METHODS = new ArrayList<>();

    public static void onEventAction(Supplier<? extends IEvent> onEvent){
        for (ClassWithMethod classWithMethod : TASK_METHODS){
            Method method = classWithMethod.getMethod();
            Class<?>[] parameter = method.getParameterTypes();
            if (parameter.length > 1){
                QwQ.LOGGER.error(method.getName() + " can only have one parameter type.");
                return;
            }
            if (onEvent.get().getClass().equals(parameter[0])){
                method.setAccessible(true);
                try {
                    method.invoke(classWithMethod.getClazz(), onEvent.get());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    QwQ.LOGGER.error(e.getMessage());
                }
            }
        }
    }

    public static class ClassWithMethod{
        private final Class<?> clazz;
        private final Method method;
        public ClassWithMethod(Class<?> clazz, Method method){
            this.clazz = clazz;
            this.method = method;
        }

        public Class<?> getClazz() {
            return clazz;
        }

        public Method getMethod() {
            return method;
        }
    }
}
