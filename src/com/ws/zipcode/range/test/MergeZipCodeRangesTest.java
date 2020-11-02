package com.ws.zipcode.range.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ws.zipcode.range.challenge.MinZipCodeRangeFilter;
import com.ws.zipcode.range.challenge.ZipCodeRange;
/**
 * This class is created to test mergeZipCodeRanges method
 * @author Jia(Josh) Xu
 *
 */
public class MergeZipCodeRangesTest {

	@Test
	public void test() {
		// [94133,94133] [94200,94299] [94600,94699]
		ZipCodeRange[] firstTestSet = { new ZipCodeRange(94133, 94133),
				new ZipCodeRange(94200, 94299), new ZipCodeRange(94600,94699) };
		// [94133,94133] [94200,94299] [94226,94399]
		ZipCodeRange[] secondTestSet = { new ZipCodeRange(94133, 94133),
				new ZipCodeRange(94200, 94299), new ZipCodeRange(94226, 94399) };
		
		// for first test set
		assertEquals(
				"The expected ranges should be [94600,94699] [94200,94299] [94133,94133]",
				"[94600,94699] [94200,94299] [94133,94133]",
				MinZipCodeRangeFilter.mergeZipCodeRanges(firstTestSet));
		
		// for second test set
		assertEquals(
				"The expected ranges should be [94200,94399] [94133,94133]",
				"[94200,94399] [94133,94133]",
				MinZipCodeRangeFilter.mergeZipCodeRanges(secondTestSet));
		
	}

}
