package com.ws.zipcode.range.test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import com.ws.zipcode.range.challenge.MinZipCodeRangeFilter;
import com.ws.zipcode.range.challenge.ZipCodeRange;
import com.ws.zipcode.range.exception.InvalidUserInputsException;
/**
 * This a Test class created for testing convertUserArgsInputIntoZipCodeRanges
 * @author Jia(Josh) Xu
 *
 */
public class UserInputConversionTest {

	@Test
	public void test() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		ZipCodeRange[] testSet = new ZipCodeRange[3];
		String [] userInputs = {"[94133,94133]","[94200,94299]","[94600,94699]"};
		Method method = MinZipCodeRangeFilter.class.getDeclaredMethod("convertUserArgsInputIntoZipCodeRanges", ZipCodeRange[].class, String[].class);
		method.setAccessible(true);
		method.invoke(MinZipCodeRangeFilter.class, testSet, userInputs);
		
		// test the first range
		assertEquals("The expected the lower bound should be 94133", 94133, testSet[0].getLowerBound());
		assertEquals("The expected the upper bound should be 94133", 94133, testSet[0].getUpperBound());
		
		// test the second range
		assertEquals("The expected the lower bound should be 94200", 94200, testSet[1].getLowerBound());
		assertEquals("The expected the upper bound should be 94299", 94299, testSet[1].getUpperBound());
		
		// test the third range
		assertEquals("The expected the lower bound should be 94600", 94600, testSet[2].getLowerBound());
		assertEquals("The expected the upper bound should be 94699", 94699, testSet[2].getUpperBound());
	}
	
	@Test
	public void testExceptionWithInvaildRangeDigits()
			throws InvalidUserInputsException, NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		ZipCodeRange[] testSet = new ZipCodeRange[3];
		String[] userInputs = { "[941330,941330]", "[942001,942991]",
				"[946002,946992]" };
		Method method = MinZipCodeRangeFilter.class.getDeclaredMethod(
				"convertUserArgsInputIntoZipCodeRanges", ZipCodeRange[].class,
				String[].class);
		method.setAccessible(true);
		try {
			method.invoke(MinZipCodeRangeFilter.class, testSet, userInputs);
		} catch (InvocationTargetException ie) {
			assertEquals(InvalidUserInputsException.class, ie.getCause()
					.getClass());
		}

	}

	@Test
	public void testExceptionWithInvaildRangeLetters()
			throws InvalidUserInputsException, NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		ZipCodeRange[] testSet = new ZipCodeRange[3];
		String[] userInputs = { "[abcde,94133]", "[9aa00,94299]",
				"[94600,94699]" };
		Method method = MinZipCodeRangeFilter.class.getDeclaredMethod(
				"convertUserArgsInputIntoZipCodeRanges", ZipCodeRange[].class,
				String[].class);
		method.setAccessible(true);
		try {
			method.invoke(MinZipCodeRangeFilter.class, testSet, userInputs);
		} catch (InvocationTargetException ie) {
			assertEquals(InvalidUserInputsException.class, ie.getCause()
					.getClass());
		}

	}

	@Test
	public void testExceptionWithInvaildRangeExtraSpace()
			throws InvalidUserInputsException, NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		ZipCodeRange[] testSet = new ZipCodeRange[3];
		String[] userInputs = { "[94133, 94133]", "[94200 ,94299]",
				"[94600,94699]" };
		Method method = MinZipCodeRangeFilter.class.getDeclaredMethod(
				"convertUserArgsInputIntoZipCodeRanges", ZipCodeRange[].class,
				String[].class);
		method.setAccessible(true);
		try {
			method.invoke(MinZipCodeRangeFilter.class, testSet, userInputs);
		} catch (InvocationTargetException ie) {
			assertEquals(InvalidUserInputsException.class, ie.getCause()
					.getClass());
		}

	}
	
	@Test
	public void testExceptionWithInvaildRangeEmptyInput()
			throws InvalidUserInputsException, NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		ZipCodeRange[] testSet = new ZipCodeRange[1];
		String[] userInputs = { "" };
		Method method = MinZipCodeRangeFilter.class.getDeclaredMethod(
				"convertUserArgsInputIntoZipCodeRanges", ZipCodeRange[].class,
				String[].class);
		method.setAccessible(true);
		try {
			method.invoke(MinZipCodeRangeFilter.class, testSet, userInputs);
		} catch (InvocationTargetException ie) {
			assertEquals(InvalidUserInputsException.class, ie.getCause()
					.getClass());
		}

	}

}
