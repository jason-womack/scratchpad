package com.github;

/**
 * Created by Jason Ryan Womack on 1/6/16.
 */
public interface HandlerIF<T extends Exception, TPrime extends Exception> {
    T handle(TPrime tPrime);
}
