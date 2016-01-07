package com.github;

import com.github.exceptions.*;
import org.junit.Test;

import static com.github.ErrorHandlerExamples.*;

/**
 * Created by Jason Ryan Womack on 1/7/16.
 */
public class CheckedFluentTest {

    @Test(expected = A.class)
    public void shouldHandleDefaultCheckedException() {
        CheckedFluent chx = new CheckedFluent(DEFAULT_HANDLERS);

        chx.onAction(objects -> {
            throw new APrime();
        }).executeOn();
    }

    @Test(expected = D.class)
    public void shouldHandleAdditionalCheckedException() {
        CheckedFluent chx = new CheckedFluent(DEFAULT_HANDLERS);

        chx.onAction(objects -> {
            throw new DPrime();
        })
           .withHandler(DPrime.class, dPrime -> toD((DPrime) dPrime))
           .withHandler(EPrime.class, ePrime -> toE((EPrime) ePrime))
           .executeOn();
    }
}