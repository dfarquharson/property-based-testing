package com.dsjf.property;

import static org.assertj.core.api.Assertions.assertThat;

import io.vavr.test.Arbitrary;
import io.vavr.test.Property;
import org.junit.Ignore;
import org.junit.Test;

public class TrickyMathTest {

    private static final TrickyMath tricky = new TrickyMath();

    @Test
    public void cleanDivisorExample() {
        assertThat(tricky.cleanDivisor(1, 2)).isFalse();
        assertThat(tricky.cleanDivisor(2, 1)).isTrue();
        assertThat(tricky.cleanDivisor(-2, -1)).isTrue();
    }

    @Test
    public void cleanDivisorPropertyClean() {
        Property.def("x * 2 / x is clean")
            .forAll(Arbitrary.integer().filter(x -> x != 0))
            .suchThat(x -> tricky.cleanDivisor(x * 2, x).equals(true))
            .check()
            .assertIsSatisfied();
    }

    @Ignore
    @Test
    public void cleanDivisorPropertyUnclean() {
        Property.def("x / x + 1 is unclean")
            .forAll(Arbitrary.integer().filter(x -> x != 0))
            .suchThat(x -> tricky.cleanDivisor(x, x + 1).equals(false))
            .check()
            .assertIsSatisfied();
    }

}
