package com.github;

/**
 * Created by Jason Ryan Womack on 1/7/16.
 */
public interface ActionIF<T> {
    T on(Object... objects) throws Exception;
}
