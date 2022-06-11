package edu.temple.cis.paystation;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Map;

/**
     * A main() program should be developed to simulate the PayStation operation. 
     * It displays a menu to allow a customer to select a choice:
     *  Deposit Coins
     *  Display
     *  Buy Ticket
     *  Cancel
     *  Empty (Admin)
     *  Change Rate Strategy (Admin)
     */
public class Main {
    public static Calendar calendar;
    public static void main(String[] args) throws IllegalCoinException{
        Scanner scnr = new Scanner(System.in);
        calendar = new GregorianCalendar();
        int choice, adminChoice, rateChoice;

        PayStationImpl ps = new PayStationImpl(new LinearRateStrategy1());

        while(true){
            System.out.println("\n\tPayStation Main Menu");
            System.out.println("\tCoin in the PayStation: " + ps.coinInPayStation());

            // TESTING PURPOSE ONLY
            System.out.println("\tCurrent Rate: " + ps.currentRate() + "\n");

            System.out.print("\t1.) Deposit Coins \n");
            System.out.print("\t2.) Display\n");
            System.out.print("\t3.) Buy Ticket\n");
            System.out.print("\t4.) Cancel\n");
            System.out.print("\t5.) Admin Mode\n");
            System.out.print("\t6.) Exit\n");
            System.out.print("\nEnter Your Menu Choice: ");
    
            choice = scnr.nextInt();

            switch(choice){
                case 1: //only allows for 1 valid coin a time
                {
                    System.out.print("\nPlease Deposit a Coin: ");
                    int deposit = scnr.nextInt();
                    try {
                        ps.addPayment(deposit);
                        System.out.print("\nThank you, your " + deposit + "cent coin was deposited\n");
                    } catch (IllegalCoinException e) {
                        System.out.println("Invalid coin type\n" + "What would you like to do next?\n");
                    }
                    break;
                }
                case 2:
                    System.out.print("\nScreen Display: ");
                    System.out.println("Time bought: " + ps.readDisplay());
                    break;

                case 3:
                    System.out.print("\nTicket Info: ");
                    Receipt receipt = ps.buy();
                    System.out.println("Thanks for your purchase. You have bought time for " + receipt.value() + " minutes");
                    break;

                case 4:
                    System.out.print("\nCanceling your order: ");
                    Map<Integer, Integer> returnCoins = ps.cancel();
                    if (returnCoins.isEmpty()) {
                        System.out.println("No coins to return.");
                    } else {
                        System.out.println("The returned coins are as follows...");
                        for (Integer coin : returnCoins.keySet()){
                            System.out.println(coin + " cents (" + returnCoins.get(coin) + "x)");
                        }
                    }
                    break;

                case 5:
                    System.out.print("\nYou are now in admin mode\n");
                    System.out.print("1.) Empty all coins\n");
                    System.out.print("2.) Change Rate Strategy\n");
                    System.out.print("Please select an admin option:");
                    adminChoice = scnr.nextInt();

                    switch(adminChoice){
                        case 1:
                        System.out.print("\nYou have emptied all the coins\n");
                            ps.empty();
                            ps.reset();
                            break;

                        case 2:
                        System.out.print("Which rate strategy would you like?:\n ");
                        System.out.print("1.) Alphatown (Linear1)\n ");
                        System.out.print("2.) Betatown (Progressive)\n ");
                        System.out.print("3.) Gammatown (Progressive (DEFAULT) - Linear1)\n ");
                        System.out.print("4.) Deltatown (Linear2)\n ");
                        System.out.print("5.) Omegatown (Linear1 (DEFAULT) - Free)\n ");
                        
                        rateChoice = scnr.nextInt();

                        switch(rateChoice){
                            case 1: 
                                ps.changeStrategy(new LinearRateStrategy1());
                                System.out.print("\nRate Strategy has been changed to Alphatown\n ");
                                break;
                            case 2:
                                ps.changeStrategy(new ProgressiveRateStrategy());
                                System.out.print("\nRate Strategy has been changed to Betatown\n ");
                                break;
                            case 3:
                                ps.changeStrategy(new AlternatingRateStrategy());
                                System.out.print("\nRate Strategy has been changed to Gammatown\n ");
                                break;
                            case 4:
                                ps.changeStrategy(new LinearRateStrategy2());
                                System.out.print("\nRate Strategy has been changed to Deltatown\n ");
                                break;
                            case 5:
                                ps.changeStrategy(new AlternatingRateStrategy2());
                                System.out.print("\nRate Strategy has been changed to Omegatown\n ");
                                break;
                        }
                    } break;

                case 6:
                    System.out.print("\nExiting Program...");
                    scnr.close();
                    System.exit(0);
                    break;

                default :
                    System.out.println("\nOption not valid. Try again");
                    break;
            }
        }
    }
}
