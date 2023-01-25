package org.abstruck.qwq.library.event.reflection;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Goulixiaoji
 */
public class EventHandle {
    protected static final List<MethodWithClass> EVENT_METHODS = new ArrayList<>();
    protected static final Map<String, Boolean> EVENT_PATHS = new HashMap<>();

    protected static class MethodWithClass{
        private Class<?> klass;
        private Method method;
        public MethodWithClass(Class<?> klass, Method method){
            this.klass = klass;
            this.method = method;
        }

        public Class<?> getKlass() {
            return klass;
        }

        public Method getMethod() {
            return method;
        }
    }
}
