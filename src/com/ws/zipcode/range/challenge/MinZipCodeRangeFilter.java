package com.ws.zipcode.range.challenge;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

import com.ws.zipcode.range.exception.InvalidUserInputsException;

/**
 * This class is created for filtering out minimum required zip code ranges
 * 
 * @author Jia(Josh) Xu
 *
 */
public class MinZipCodeRangeFilter {

	// setup logger for this Class
	final static Logger logger = Logger
			.getLogger(com.ws.zipcode.range.challenge.MinZipCodeRangeFilter.class);
	// pattern for checking valid range structure with 5 digits for both lower
	// and upper bounds
	private static final Pattern ZIP_CODE_RANGE_PATTERN = Pattern
			.compile("\\[(?<lowerBound>\\d{5}),(?<upperBound>\\d{5})\\]");

	// define vaild user input format
	private static final String VALID_INPUT_FORMAT = "[<5_digits_numbers>,<5_digits_numbers>]";

	/**
	 * This method is created for merging zipcode ranges
	 * 
	 * @param zipCodeRanges
	 *            takes ZipCodeRange array input
	 * @return String output which is the minimum required zipcode ranges
	 */
	public static String mergeZipCodeRanges(ZipCodeRange[] zipCodeRanges) {
		logger.info("start merging zip code ranges process...");
		// create a commparator to all sorting by increasing orders
		Comparator<ZipCodeRange> zipCodeRangeComparator = Comparator
				.<ZipCodeRange> naturalOrder();
		Arrays.sort(zipCodeRanges, zipCodeRangeComparator);

		// create the rangesStack for store expected zip code ranges
		Stack<ZipCodeRange> rangeStack = new Stack<>();
		rangeStack.push(zipCodeRanges[0]);
		for (ZipCodeRange currentRange : zipCodeRanges) {
			ZipCodeRange top = rangeStack.peek();

			// check if the current range overlaps with top, if yes, push,
			// otherwise merge
			if (top.getUpperBound() < currentRange.getLowerBound()) {
				rangeStack.push(currentRange);
			} else if (top.getUpperBound() < currentRange.getUpperBound()) {
				top.setUpperBound(currentRange.getUpperBound());
				rangeStack.pop();
				rangeStack.push(top);
			}

		}

		// output String for storing the final expected ranges
		String mergedRanges = "";
		while (!rangeStack.isEmpty()) {

			ZipCodeRange zipCodeRange = rangeStack.pop();
			if (mergedRanges.length() < 1) {
				mergedRanges = String.format("[%d,%d]",
						zipCodeRange.getLowerBound(),
						zipCodeRange.getUpperBound());
			} else {
				mergedRanges = mergedRanges
						+ " "
						+ String.format("[%d,%d]",
								zipCodeRange.getLowerBound(),
								zipCodeRange.getUpperBound());
			}
		}
		logger.info("Zip code ranges merging completed.");
		return mergedRanges;

	}

	/**
	 * This method is created for converting user args into ZipCodeRange Array,
	 * which will be used an input for mergeZipCodeRanges method
	 * 
	 * @param zipCodeRanges
	 * @param userInputs
	 * @throws InvalidUserInputsException
	 */
	private static void convertUserArgsInputIntoZipCodeRanges(
			ZipCodeRange[] zipCodeRanges, String[] userInputs)
			throws InvalidUserInputsException {
		int arraySize = userInputs.length;
		int index = 0;
		logger.info("start users' inputs conversion process...");
		for (String userInput : userInputs) {
			Matcher matcher = ZIP_CODE_RANGE_PATTERN.matcher(userInput);
			if (matcher.find()) {
				zipCodeRanges[index] = new ZipCodeRange(
						Integer.parseInt(matcher.group("lowerBound")),
						Integer.parseInt(matcher.group("upperBound")));
				index++;
			} else {
				throw new InvalidUserInputsException("Invalid input: "
						+ userInput
						+ " ,please enter valid user input with format: "
						+ VALID_INPUT_FORMAT);

			}
		}
		logger.info("users' inputs conversion completed.");
	}

	public static void main(String[] args) {
		// build an ZipCodeRange array which shares the same size as user
		// inputs'
		ZipCodeRange[] zipCodeRanges = new ZipCodeRange[args.length];
		try {
			convertUserArgsInputIntoZipCodeRanges(zipCodeRanges, args);
		} catch (InvalidUserInputsException e) {
			logger.error(e.getMessage());
			System.exit(-1);
		}
		System.out.println(mergeZipCodeRanges(zipCodeRanges));

	}

}
