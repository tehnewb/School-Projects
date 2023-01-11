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

	/**
	 * Flips the bits of the given {@code binary} string.
	 * 
	 * @param binary the string of binary to flip
	 * @return the flipped binary string
	 */
	public static String flipBits(String binary) {
		char[] sequence = new char[binary.length()];
		for (int i = 0; i < binary.length(); i++) {
			if (binary.charAt(i) == '0') {
				sequence[i] = '1';
			} else {
				sequence[i] = '0';
			}
		}
		return new String(sequence);
	}

	/**
	 * Converts the given {@code binary} string to the 1's complement form.
	 * 
	 * @param binary the string to convert
	 * @return the 1's complement form of the given string
	 */
	public static String onesComplement(String binary) {
		return flipBits(binary);
	}

	/**
	 * Converts the given {@code binary} string to the 2's complement form.
	 * 
	 * @param binary the string to convert
	 * @return the 2's complement form of the given string
	 */
	public static String twosComplement(String binary) {
		return addBinary(flipBits(binary), "1");
	}

	/**
	 * Adds both binary strings together and returns the added binary string.
	 * 
	 * @param first  the first binary string to add
	 * @param second the second binary string to add
	 * @return the added binary string
	 */
	public static String addBinary(String first, String second) {
		boolean carried = false;
		int length = Math.max(first.length(), second.length());
		first = first.replace(" ", "");
		second = second.replace(" ", "");

		first = String.format("%1$" + length + "s", first).replace(" ", "0");
		second = String.format("%1$" + length + "s", second).replace(" ", "0");
		char[] sequence = new char[length];
		for (int i = length - 1; i >= 0; i--) {
			if (first.charAt(i) == '1' && second.charAt(i) == '1') {
				if (carried) {
					sequence[i] = '1';
					carried = false;
				} else {
					sequence[i] = '0';
				}
				carried = true;
			} else if ((first.charAt(i) == '0' && second.charAt(i) == '1') || (first.charAt(i) == '1' && second.charAt(i) == '0')) {
				if (carried) {
					sequence[i] = '0';
				} else {
					sequence[i] = '1';
				}
			} else if (carried) {
				carried = false;
				sequence[i] = '1';
			} else {
				sequence[i] = '0';
			}
		}
		return new String(sequence);
	}

}
