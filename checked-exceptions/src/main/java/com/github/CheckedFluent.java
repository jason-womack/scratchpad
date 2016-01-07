package com.github;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason Ryan Womack on 1/7/16.
 */
public class CheckedFluent {
    private final Map<Class<? extends Exception>, HandlerIF> defaultHandlers;

    private ActionIF<?> action = null;

    public CheckedFluent(Map<Class<? extends Exception>, HandlerIF> defaultHandlers) {
        this.defaultHandlers = Collections.unmodifiableMap(defaultHandlers);
    }

    public <T> CheckedFluent onAction(ActionIF<T> action) {
        this.action = action;
        return this;
    }

    public <T extends Exception, TPrime extends Exception> CheckedFluent withHandler(Class<TPrime> clazz, HandlerIF<T, TPrime> handler) {
        Map<Class<? extends Exception>, HandlerIF> defaultHandlers = new HashMap<>(this.defaultHandlers);
        defaultHandlers.put(clazz, handler);

        CheckedFluent checker = new CheckedFluent(defaultHandlers);
        checker.onAction(this.action);

        return checker;
    }

    public <T extends Exception> void executeOn(Object... objects) throws T {
        try {
            this.action.on(objects);
        } catch (final Exception e) {
            handle(e);
        }
    }

    private <T extends Exception, TPrime extends Exception> void handle(TPrime tPrime) throws  T {
        //return new T();  Does not compile.  Can't instantiate a Generic Types constructor
        final HandlerIF handlerIF = this.defaultHandlers.get(tPrime.getClass());
        throw (T)  handlerIF.handle(tPrime);
    }
}
