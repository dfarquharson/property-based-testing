package com.dsjf.property;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitQuickcheck.class)
public class HelloTest {

    @Property
    public void concatenationLength(String s1, String s2) {
        assertThat(s1.length() + s2.length())
                .isEqualTo((s1 + s2).length());
    }

    @Property
    public void associativeAddition(Integer i, Integer j) {
        System.out.println(i + " + " + j);
        assertThat(i + j).isEqualTo(j + i);
    }

//    @Property
//    public void positiveAddition(Integer i, Integer j) {
//        assertThat(i + j).isGreaterThan(0);
//    }

    // TODO: 100% branch and line coverage, but still find a bug with property-based testing
}
