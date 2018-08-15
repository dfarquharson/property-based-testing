package com.dsjf.property;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.vavr.CheckedFunction1;
import io.vavr.test.Arbitrary;
import io.vavr.test.Gen;
import io.vavr.test.Property;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;

public class TrickyMathExperimentalTest {

    private static final TrickyMath tricky = new TrickyMath();

    @Test
    public void additionIsAssociative() {
        Arbitrary<Integer> ints = Arbitrary.integer();

        CheckedFunction1<Integer, Boolean> associative = i ->
            tricky.add(i, i * i).equals(tricky.add(i * i, i));

        Property.def("addition is associative")
            .forAll(ints)
            .suchThat(associative)
            .check(10_000, 1_000)
            .assertIsSatisfied();
    }

    @Test
    public void divisionIsAThingThatWorksExampleBased() {
        assertThat(tricky.divide(2d, 4d))
            .isGreaterThan(0)
            .isLessThanOrEqualTo(1)
            .isEqualTo(0.5d);
        assertThat(tricky.divide(4d, 2d))
            .isGreaterThan(1)
            .isEqualTo(2d);
    }

    @Test
    public void divisionIsAThingThatWorksPropertyBased() {
        CheckedFunction1<Double, Boolean> works = i -> {
            System.out.println(i);
            return tricky.divide(i, i + 1) < 1 &&
                tricky.divide(i + 1, i) > 1;
        };

        Property.def("division works")
            .forAll(n -> Gen.choose(0d, (double) n))
            .suchThat(works)
            .check(100, 1_000)
            .assertIsSatisfied();
    }

    @Ignore
    @Test
    public void cleanDivisor() {
        CheckedFunction1<Integer, Boolean> clean = i ->
            tricky.cleanDivisor(i, i).equals(true);

        Property.def("x / x is always cleanly divisible")
            .forAll(Arbitrary.integer())
            .suchThat(clean)
            .check(100, 1_000)
            .assertIsSatisfied();
    }

    @Ignore
    @Test
    public void uncleanDivisorExample() {
        assertThat(tricky.cleanDivisor(1, 2)).isFalse();
        assertThat(tricky.cleanDivisor(4, 5)).isFalse();
//        assertThat(tricky.cleanDivisor(2, 1)).isTrue();
//        assertThatThrownBy(() -> tricky.cleanDivisor(1, 0))
//            .isInstanceOf(ArithmeticException.class);
//        assertThat(tricky.cleanDivisor(-2, -1)).isTrue();
//        assertThat(tricky.cleanDivisor(0, 1)).isTrue();
    }

    @Ignore
    @Test
    public void uncleanDivisor() {
        CheckedFunction1<Integer, Boolean> unclean = i ->
            tricky.cleanDivisor(i, i + 1).equals(false);

        Property.def("x / x + 1 is unclean")
            .forAll(Arbitrary.integer())
            .suchThat(unclean)
            .check(10, 1_000)
            .assertIsSatisfied();
    }

    @Test
    public void timeIsAFlatCircle() {
        Arbitrary<LocalDateTime> dates = Arbitrary.localDateTime();

        CheckedFunction1<LocalDateTime, Boolean> isAfter = d -> {
            System.out.println(d.toString());
            return d.plusHours(1L).isAfter(d) &&
                d.plusHours(1L).minusHours(1L).equals(d);
        };

        Property.def("an hour after something is always larger")
            .forAll(dates)
            .suchThat(isAfter)
            .check()
            .assertIsSatisfied();
    }

    @Ignore
    @Test
    public void cleanDivisorExample() {
        assertThat(tricky.cleanDivisor(1, 2)).isFalse();
        assertThat(tricky.cleanDivisor(2, 1)).isTrue();
    }

    @Ignore
    @Test
    public void cleanDivisorPropertyClean() {
        Property.def("x * 2/ x is clean")
            .forAll(Arbitrary.integer())
            .suchThat(x -> tricky.cleanDivisor(x * 2, x).equals(true))
            .check()
            .assertIsSatisfied();
    }

    @Ignore
    @Test
    public void cleanDivisorPropertyUnclean() {
        Property.def("x / x + 1 is unclean")
            .forAll(Arbitrary.integer())
            .suchThat(x -> tricky.cleanDivisor(x, x + 1).equals(false))
            .check()
            .assertIsSatisfied();
    }

}