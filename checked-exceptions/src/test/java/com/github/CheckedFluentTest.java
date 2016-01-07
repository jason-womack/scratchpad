package com.github;

import org.junit.Test;

import static com.github.ErrorHandlerExamples.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Jason Ryan Womack on 1/7/16.
 */
public class CheckedFluentTest {

    @Test
    public void shouldThrowCheckedException() {
        CheckedFluent check = new CheckedFluent(DEFAULT_HANDLERS);

        check.onAction(objects -> {
            throw new APrime();
        })
                .withHandler(DPrime.class, dPrime -> toD((DPrime) dPrime))
                .withHandler(EPrime.class, ePrime -> toE((EPrime) ePrime));
        try {
            check.executeOn();
        } catch (Exception e) {
            assertThat(e, is(instanceOf(A.class)));
        }
    }

    @Test
    public void shouldThrowAdditionalCheckedException() {
        CheckedFluent check = new CheckedFluent(DEFAULT_HANDLERS);

        try {

            check.onAction(objects -> {
                throw new DPrime();
            })
                .withHandler(DPrime.class, dPrime -> toD((DPrime) dPrime))
                .withHandler(EPrime.class, ePrime -> toE((EPrime) ePrime))
                .executeOn();
        } catch (Exception e) {
            assertThat(e, is(instanceOf(D.class)));
        }
    }

}