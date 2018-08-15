package com.dsjf.property;

import static org.assertj.core.api.Assertions.assertThat;

import io.vavr.CheckedFunction1;
import io.vavr.collection.Stream;
import io.vavr.test.Arbitrary;
import io.vavr.test.Property;
import org.junit.Test;

import java.util.Random;

public class VavrPropertyTests {

    @Test
    public void positiveSquares() {
        Arbitrary<Integer> ints = Arbitrary.integer();

        Property.def("square(int) >= 0")
            .forAll(ints)
            .suchThat(i -> i * i >= 0)
            .check()
            .assertIsSatisfied();
    }

    @Test
    public void somethingSomethingEvenInts() {
        System.out.println(Arbitrary.integer().filter(i -> i % 2 == 0).apply(10).peek(System.out::println));
        System.out.println(Arbitrary.integer().filter(i -> i % 2 == 0).apply(10).apply(new Random()));
        assertThat(Arbitrary.integer()
            .filter(i -> i % 2 == 0)
            .apply(10)
            .apply(new Random())
        ).isNotNull();
    }

    public Stream<String> fizzBuzz() {
        return Stream.from(1)
            .map(i -> {
                boolean divBy3 = i % 3 == 0;
                boolean divBy5 = i % 5 == 0;

                return divBy3 && divBy5 ? "FizzBuzz" :
                    divBy3 ? "Fizz" :
                        divBy5 ? "Buzz" : i.toString();
            });
    }

    @Test
    public void everyThirdElementStartsWithFizz() {
        Arbitrary<Integer> multiplesOf3 = Arbitrary.integer()
            .filter(i -> i > 0)
            .filter(i -> i % 3 == 0);

        CheckedFunction1<Integer, Boolean> mustStartWithFizz = i ->
            fizzBuzz().get(i - 1).startsWith("Fizz");

        Property.def("Every third element must start with Fizz")
            .forAll(multiplesOf3)
            .suchThat(mustStartWithFizz)
            .check(10_000, 1_000)
            .assertIsSatisfied();
    }

    @Test
    public void everyFifthElementEndsWithBuzz() {
        Arbitrary<Integer> multiplesOf5 = Arbitrary.integer()
            .filter(i -> i > 0)
            .filter(i -> i % 5 == 0);

        CheckedFunction1<Integer, Boolean> mustEndWithBuzz = i ->
            fizzBuzz().get(i - 1).endsWith("Buzz");

        Property.def("Every fifth element must end with Buzz")
            .forAll(multiplesOf5)
            .suchThat(mustEndWithBuzz)
            .check(10_000, 1_000)
            .assertIsSatisfied();
    }

    @Test
    public void everyNonFifthAndNonThirdElementIsANumber() {
        Arbitrary<Integer> nonFifthNonThird = Arbitrary.integer()
            .filter(i -> i > 0)
            .filter(i -> i % 5 != 0)
            .filter(i -> i % 3 != 0);

        CheckedFunction1<Integer, Boolean> mustBeANumber = i ->
            fizzBuzz().get(i - 1).equals(i.toString());

        Property.def("Non-fifth and non-third element must be a number")
            .forAll(nonFifthNonThird)
            .suchThat(mustBeANumber)
            .check(10_000, 1_000)
            .assertIsSatisfied();
    }

}
