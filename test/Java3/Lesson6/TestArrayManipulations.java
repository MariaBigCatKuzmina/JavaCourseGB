package Java3.Lesson6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestArrayManipulations {

    ArrayManipulations arrayManipulations;

    @BeforeEach
    public void init() {
         arrayManipulations = new ArrayManipulations();
    }

    @ParameterizedTest
    @MethodSource("dataFor_getElementsAfterLastFour")
    public void parametrizedTest_getElementsAfterLastFour(int[] inArray, int[] outArray) {
        Assertions.assertArrayEquals(outArray, arrayManipulations.getElementsAfterLastFour(inArray));
    }

    public static Stream<Arguments> dataFor_getElementsAfterLastFour() {
        List<Arguments> out = new ArrayList<>();

        out.add(Arguments.arguments(new int[] {3, 5, 4, 1, 1}, new int[] {1, 1}));
        out.add(Arguments.arguments(new int[] {2, 1, 3, 10, 4}, new int[] {}));
        out.add(Arguments.arguments(new int[] {4, 1, 2, 3, 5}, new int[] {1, 2, 3, 5}));

        return out.stream();
    }

    @Test
    public void test_rightResult_getElementsAfterLastFour() {
        Assertions.assertArrayEquals(new int[]{1, 7},
                arrayManipulations.getElementsAfterLastFour(new int[]{1, 2 , 4, 4, 2, 3, 4, 1, 7}));
    }

    @Test
    public void test_rightResult2_getElementsAfterLastFour() {
        Assertions.assertArrayEquals(new int[]{5, 2, 3, 0, 1, 7},
                arrayManipulations.getElementsAfterLastFour(new int[]{1, 2 , 4, 5, 2, 3, 0, 1, 7}));
    }
    @Test
    public void test_noFourFound_getElementsAfterLastFour() {
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () ->
                arrayManipulations.getElementsAfterLastFour(new int[]{1, 2 , 7, 1, 2, 3, 11, 1, 7}));
        System.out.println(thrown.getMessage());
    }

    @Test
    public void test_empty_getElementsAfterLastFour() {
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () ->
                arrayManipulations.getElementsAfterLastFour(new int[]{}));
        System.out.println(thrown.getMessage());
    }

    @Test
    public void test_true_hasOneAndFour() {
        Assertions.assertTrue(arrayManipulations.hasOneAndFour(new int[]{1,4,4,4,1,1}));
    }

    @Test
    public void test_false_hasOneAndFour() {
        Assertions.assertFalse(arrayManipulations.hasOneAndFour(new int[]{3,4,4,4,2}));
    }

    @Test
    public void test_empty_hasOneAndFour() {
        Assertions.assertFalse(arrayManipulations.hasOneAndFour(new int[]{}));
    }


}