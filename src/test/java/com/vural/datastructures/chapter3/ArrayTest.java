package com.vural.datastructures.chapter3;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class ArrayTest {

    static int[] array1;
    static int[] array2;
    static int[][] array3;
    static int[][] array4;

    @Before
    public void setup(){
        array1 = new int[]{1, 2, 3};
        array2 = new int[]{1, 2, 3};
        array3 = new int[][]{{1}, {2}, {3}};
        array4 = new int[][]{{1}, {2}, {3}};
    }

    @Test
    public void should_not_equal_by_equal_to_operator_for_one_dimensional_array(){
        assert array1 != array2;
    }

    @Test
    public void should_not_equal_by_equals_method_for_one_dimensional_array(){
        //behind the scene, equals method uses '==' operator
        assert !array1.equals(array2);
    }

    @Test
    public void should_be_equal_by_arrays_equals_method_for_one_dimensional_array(){
        //behind the scene, 'Arrays.equals' compares one by one every elements from two arrays.
        //It uses '==' operator for primitives, 'equals()' for non-primitives
        assert Arrays.equals(array1, array2);
    }

    @Test
    public void should_not_be_equal_by_equal_to_operator_for_multi_dimensional_array(){
        assert array3 != array4;
    }

    @Test
    public void should_not_be_equal_by_equals_method_for_multi_dimensional_array(){
        //behind the scene, equals method uses '==' operator
        assert !array3.equals(array4);
    }

    @Test
    public void should_not_be_equal_by_arrays_equals_method_for_multi_dimensional_array(){
        //behind the scene, 'Arrays.equals' compares one by one every elements from two arrays.
        //It uses '==' operator for primitives, 'equals()' for non-primitives
        assert !Arrays.equals(array3, array4);
    }

    @Test
    public void should_be_equal_by_arrays_deep_equals_method_for_multi_dimensional_array(){
        /*Identical to Arrays.equals(a,b) except when the elements
        of a and b are themselves arrays, in which case it calls
        Arrays.deepEquals(a[k],b[k]) for corresponding entries,
        rather than a[k].equals(b[k]).*/
        assert Arrays.deepEquals(array3, array4);
    }
}
