package com.ws.zipcode.range.challenge;

/**
 * This class is a pojo class for ZIP Code Range which will be used for
 * ZipCodeChallenge
 * 
 * @author Jia(Josh) Xu
 *
 */
public class ZipCodeRange implements Comparable<ZipCodeRange> {
	/**
	 * represents lower bound
	 */
	private int lowerBound;
	/**
	 * represents upper bound
	 */
	private int upperBound;

	/**
	 * constuctor for class ZipCodeRange
	 * 
	 * @param lowerBound
	 *            input for lower bound
	 * @param upperBound
	 *            input for upper bound
	 */
	public ZipCodeRange(int lowerBound, int upperBound) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	public int getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}

	public int getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
	}

	/**
	 * create a custom comparison rule for ZipCode Range object, which compares
	 * the lowerbounds
	 * 
	 * @param zipCodeRange
	 *            takes ZipCodeRange instances
	 * @return int
	 */
	@Override
	public int compareTo(ZipCodeRange zipCodeRange) {
		// use lowerBound as the criteria of zipCodeRange comparison
		return Integer.compare(this.lowerBound, zipCodeRange.lowerBound);
	}

}
