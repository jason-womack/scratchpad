package com.github;

/**
 * Created by Jason Ryan Womack on 1/6/16.
 */
public interface ErrorHandlerIF {

    void one() throws APrime, BPrime, CPrime;
    void two() throws APrime, BPrime, CPrime;
    void three() throws APrime, BPrime, CPrime;
    void four()  throws A, B, C;
    void five()  throws A, B, C;
    void six();
    void seven() throws  A, B, C, D, E;
}
