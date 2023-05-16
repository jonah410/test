import java.util.Scanner;

/**
 * @author jonahblack
 * @description: a tool to compute the approximate carbon footprint of A.3 CreAlgae prototype
 * Final values are predetermined, and are measured in CO2-eq/kg (CO2-eq = Carbon Dioxide Equivalent, which denotes carbon dioxide or equivalent greenhouse gases)
 * 
 */
public class A3 {
private double glycerin; // grams
private double dehydratorTime; // hours
private double resin; // grams
// private double delivery; // maybe create separate variables for each individual delivery method, average it afterwards?
public static final double glycerinFP = 4.5; //from palm oil
public static final double dehydratorFP = 2.8; //per 24 hrs
public static final double resinFP = 4.5; // liquid epoxy resin
public static final double deliveryFP =  2.7; // 2.7 per kg of product. Highly variable per individual delivery, but should approach this value (or close to it) over time

	public A3(double gly, double dT, double res) { // create A.3 object, in grams
		glycerin = gly;
		dehydratorTime = dT;
		resin = res;
	}
	public double getGlycerin() {
		return glycerin;
	}
	public double getdehydratorTime() {
		return dehydratorTime;
	}
	public double getResin() {
		return resin;
	}
		public double getScaledGlycerinFP() throws IllegalArgumentException { // take user input, if seeking to modify initial values
			return glycerinFP * (glycerin / 1000);
		}
		public double getScaledDehydratorFP() throws IllegalArgumentException { // take user input, if seeking to modify initial values
			return dehydratorFP * (dehydratorTime / 24);
		}
		public double getScaledResinFP() throws IllegalArgumentException { // take user input, if seeking to modify initial value
			return resinFP * (resin / 1000);
		}
		public double getScaledDeliveryFP() throws IllegalArgumentException { // take user input, if seeking to modify initial values
			return deliveryFP * ((resin + glycerin) / 1000);
		}
		public double getTotalFP() {
			return getScaledGlycerinFP() + getScaledDehydratorFP() + getScaledResinFP()+ getScaledDeliveryFP();
		}
		public double getTotalFP(double algaeAmount) { // for algae ordered from amazon
			return getScaledGlycerinFP() + getScaledDehydratorFP() + getScaledResinFP()+ (getScaledDeliveryFP() + deliveryFP * (algaeAmount / 1000));
		}
public static void main(String[] args) throws IllegalArgumentException {
	boolean running = true;
	int flag = 1;
	while (running) {
		try {
			Scanner reader = new Scanner(System.in);
			double[] values = new double[4];
			if (flag == 1) {
				System.out.println("Enter glycerin amount, in grams: ");
				values[0] = reader.nextDouble();
				reader.nextLine();
				flag++;
			}
			if (flag == 2) {
				System.out.println("Enter time spent in dehydrator, in hours: ");
				values[1] = reader.nextDouble();	
				reader.nextLine();
				flag++;
			} // test
			if (flag == 3) {
				System.out.println("Enter resin amount, in grams: ");
				values[2] = reader.nextDouble();	
				reader.nextLine();
				flag++;
			}
			if (flag == 4) {
				System.out.println("Did you use algae from Amazon? Enter Y or N: ");
				String input = reader.nextLine();
				flag++;
				if (flag == 5) {
					if (input.equals("Y")) {
						System.out.println("Oh well. That may increase our carbon footprint a bit. Enter algae amount, in grams: ");
						values[3] = reader.nextDouble();
						reader.nextLine();
						A3 obj = new A3(values[0], values[1], values[2]);
						System.out.println("Estimated carbon footprint: " + obj.getTotalFP(values[3]) + " CO2-eq");
					} else if (input.equals("N")) {
						A3 obj = new A3(values[0], values[1], values[2]);
						System.out.println("Estimated carbon footprint: " + obj.getTotalFP() + " CO2-eq");
					} else {
						System.out.println("Please ensure you entered the correct value");
						flag = 4;
						continue;
					}
				}
			}
			reader.close();
			running = false;
			} catch (Exception e) {
				System.out.println("Please ensure you entered the correct value");
				continue;
			}
		}
	}
}