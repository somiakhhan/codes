public class NumericMax {
	public static void main(String[] args) {
    double max = Double.NEGATIVE_INFINITY;
    boolean foundNumeric = false;
	for (String arg : args) {
         try {
    		double value = Double.parseDouble(arg);
                if (value > max) {
                    max = value;
                }
                foundNumeric = true;
            } catch (NumberFormatException e) {
                System.out.println("skipping " + arg);
            }
        }

        if (foundNumeric) {
            System.out.println("max = " + max);
        } else {
            System.out.println("no numeric arguments");
        }
    }
}
