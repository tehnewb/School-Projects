package conversions;

/**
 * This class holds various methods for binary conversions.
 * 
 * @author Albert Beaupre
 * @date January 8th, 2023
 */
public class Binary {

	/**
	 * Converts the given {@code binary} string into a 32-Bit IEEE 754
	 * Single-Precision Floating Point binary value.
	 * 
	 * @param binary the string to convert
	 * @return the converted string
	 */
	public static String convertToIEEE754(String binary) {
		String[] split = binary.split("\\."); // split the binary string by the decimal location
		String leftDecimal = split[0]; // retrieve the left side of the decimal
		String rightDecimal = split[1]; // retrieve the right side of the decimal
		boolean negative = leftDecimal.charAt(0) == '-'; // flag true if the binary value is negative

		int shifts = binary.indexOf('.') - 1; // the amount of shifts from the right
		int bias = 127; // the constant bias value for 32-bit
		int exponent = bias + shifts;

		StringBuilder result = new StringBuilder();
		result.append(negative ? '1' : '0'); // append 1 if negative value, otherwise append 0
		result.append(String.format("%1$8s", Integer.toBinaryString(exponent))); // append exponent as binary
		result.append(String.format("%1$-23s", leftDecimal.substring(leftDecimal.indexOf('1') + 1) + rightDecimal).replace(" ", "0")); // significand
		return result.toString();
	}

}
