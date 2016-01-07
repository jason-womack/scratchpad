package com.github;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason Ryan Womack on 1/6/16.
 */
public class ErrorHandlerExamples implements ErrorHandlerIF {

    @Override
    public void one() throws APrime, BPrime, CPrime {

    }

    @Override
    public void two() throws APrime, BPrime, CPrime {
        one();
    }

    @Override
    public void three() {
        try {
            one();
        } catch (APrime aPrime) {
            aPrime.printStackTrace();
        } catch (BPrime bPrime) {
            bPrime.printStackTrace();
        } catch (CPrime cPrime) {
            cPrime.printStackTrace();
        }
    }

    @Override
    public void four() throws A, B, C {
        try {
            one();
        } catch (APrime aPrime) {
            throw toA(aPrime);
        } catch (BPrime bPrime) {
            throw toB(bPrime);
        } catch (CPrime cPrime) {
            throw toC(cPrime);
        }
    }


    static public A toA(APrime aPrime) {
        return new A();
    }

    static public B toB(BPrime bPrime) {
        return new B();
    }

    static public C toC(CPrime cPrime) {
        return new C();
    }

    static public D toD(DPrime dPrime) {
        return new D();
    }

    static public E toE(EPrime ePrime) {
        return new E();
    }


    static final Map<Class<? extends Exception>, HandlerIF> DEFAULT_HANDLERS;

    static {
        Map<Class<? extends Exception>, HandlerIF> defaultHandlers = new HashMap<>();
        defaultHandlers.put(APrime.class, aPrime -> toA((APrime) aPrime));
        defaultHandlers.put(BPrime.class, bPrime -> toB((BPrime) bPrime));
        defaultHandlers.put(CPrime.class, cPrime -> toC((CPrime) cPrime));

        DEFAULT_HANDLERS = Collections.unmodifiableMap(defaultHandlers);
    }

    public <T extends Exception, TPrime extends Exception> void toT(TPrime tPrime) throws A, B, C, T {
        //return new T();  Does not compile.  Can't instantiate a Generic Types constructor
        throw (T) DEFAULT_HANDLERS.get(tPrime).handle(tPrime);
    }

    @Override
    public void five() throws A, B, C {
        try {
            one();
        } catch (Exception e) {
            toT(e);
        }
    }

    @Override
    public void six() {
        CheckedFluent chx = new CheckedFluent(DEFAULT_HANDLERS);

        chx.onAction(objects -> {
            one();
            return null;
        })
           .withHandler(DPrime.class, dPrime -> toD((DPrime) dPrime))
           .withHandler(EPrime.class, ePrime -> toE((EPrime) ePrime))
           .executeOn();
    }

    @Override
    public void seven() throws A, B, C, D, E {
        six();
    }
}
