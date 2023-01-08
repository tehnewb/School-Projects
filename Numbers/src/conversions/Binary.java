package conversions;

public class Binary {

	public static String convertToIEEE754(String binary) {
		String[] split = binary.split("\\.");
		String leftDecimal = split[0];
		String rightDecimal = split[1];
		boolean negative = leftDecimal.charAt(0) == '-';

		int shifts = binary.indexOf('.') - 1;
		int bias = 127;
		int exponent = bias + shifts;

		String sign = negative ? "1" : "0";
		String exp = String.format("%1$8s", Integer.toBinaryString(exponent));
		String fraction = String.format("%1$-23s", leftDecimal.substring(leftDecimal.indexOf('1') + 1) + rightDecimal).replace(" ", "0");

		System.out.println("32-Bit IEEE 754 Single-Precision Floating Point Binary");
		System.out.println("--\t--------\t-----------------------");
		System.out.println("" + sign + ".\t" + exp + "\t" + fraction + "");
		System.out.println("--\t--------\t-----------------------");

		StringBuilder result = new StringBuilder();
		return result.toString();
	}

}
