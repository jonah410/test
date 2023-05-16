import java.util.Scanner;

public class A1 {
    private double glycerin;
    private double dehydratorTime;
    private double starch;
    private double guarGum;
    private double carrageenan;

    public static final double glycerinFP = 4.5;
    public static final double dehydratorFP = 2.8;
    public static final double starchFP = 2;
    public static final double guarGumFP = 3.1;
    public static final double carrageenanFP = 2.1;
    public static final double deliveryFP = 2.7;

    public A1(double gly, double dT, double star, double guar, double carr) {
        glycerin = gly;
        dehydratorTime = dT;
        starch = star;
        guarGum = guar;
        carrageenan = carr;
    }

    public double getScaledGlycerinFP() {
        return glycerinFP * (glycerin / 1000);
    }

    public double getScaledDehydratorFP() {
        return dehydratorFP * (dehydratorTime / 24);
    }

    public double getScaledStarchFP() {
        return starchFP * (starch / 1000);
    }

    public double getScaledGuarGumFP() {
        return guarGumFP * (guarGum / 1000);
    }

    public double getScaledCarrageenanFP() {
        return carrageenanFP * (carrageenan / 1000);
    }

    public double getScaledDeliveryFP() {
        return deliveryFP * ((starch + glycerin + guarGum + carrageenan) / 1000);
    }

    public double getTotalFP() {
        return getScaledGlycerinFP() + getScaledDehydratorFP() + getScaledStarchFP() + getScaledGuarGumFP() + getScaledCarrageenanFP() + getScaledDeliveryFP();
    }

    public double getTotalFP(double algaeAmount) {
        return getScaledGlycerinFP() + getScaledDehydratorFP() + getScaledStarchFP() + getScaledGuarGumFP() + getScaledCarrageenanFP() + (getScaledDeliveryFP() + deliveryFP * (algaeAmount / 1000));
    }

    public static void main(String[] args) {
        boolean running = true;
        int flag = 1;
        while (running) {
            try {
                Scanner reader = new Scanner(System.in);
                double[] values = new double[5];
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
                }
                if (flag == 3) {
                    System.out.println("Enter starch amount, in grams: ");
                    values[2] = reader.nextDouble();    
                    reader.nextLine();
                    flag++;
                }
                if (flag == 4) {
                    System.out.println("Enter guar gum amount, in grams: ");
                    values[3] = reader.nextDouble();
                    reader.nextLine();
                    flag++;
                }
                if (flag == 5) {
                    System.out.println("Enter carrageenan amount, in grams: ");
                    values[4] = reader.nextDouble();
                    reader.nextLine();
                    flag++;
                }
                if (flag == 6) {
                    System.out.println("Did you use algae from Amazon? Enter Y or N: ");
                    String input = reader.nextLine();
                    flag++;
                    if (flag == 7) {
                        if (input.equalsIgnoreCase("Y")) {
                            System.out.println("Oh well. That may increase our carbon footprint a bit. Enter algae amount, in grams: ");
                            double algaeAmount = reader.nextDouble();
                            reader.nextLine();
                            A1 obj = new A1(values[0], values[1], values[2], values[3], values[4]);
                            System.out.println("Estimated carbon footprint: " + obj.getTotalFP(algaeAmount) + " CO2-eq");
                        } else if (input.equalsIgnoreCase("N")) {
                            A1 obj = new A1(values[0], values[1], values[2], values[3], values[4]);
                            System.out.println("Estimated carbon footprint: " + obj.getTotalFP() + " CO2-eq");
                        } else {
                            System.out.println("Please ensure you entered the correct value");
                            flag = 6;
                            continue;
                        }
                    }
                }
                reader.close();
                running = false;
            } catch (Exception e) {
                System.out.println("Please ensure that you have entered the correct value");
                continue;
            }
        }
    }
}

