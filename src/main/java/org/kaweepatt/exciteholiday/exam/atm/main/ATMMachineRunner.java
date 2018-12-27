/**
 * 
 */
package org.kaweepatt.exciteholiday.exam.atm.main;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import org.kaweepatt.exciteholiday.exam.atm.model.ATMMachine;
import org.kaweepatt.exciteholiday.exam.atm.model.BankNoteType;
import org.kaweepatt.exciteholiday.exam.atm.model.NotEnoughBankNoteException;

/**
 * @author Kaweepatt
 *
 */
public class ATMMachineRunner {

    public static void main(String[] args) {
        ATMMachine atmMachine = new ATMMachine(System.out);

        Map<BankNoteType, Integer> bankNotes = new LinkedHashMap<BankNoteType, Integer>();
        bankNotes.put(BankNoteType.B1000, 20);
        bankNotes.put(BankNoteType.B500, 20);
        bankNotes.put(BankNoteType.B100, 20);
        bankNotes.put(BankNoteType.B50, 20);
        bankNotes.put(BankNoteType.B20, 20);

        atmMachine.initial(bankNotes);

        System.out.println("Please select commands: ");
        System.out.println("R: Print bank note container report ");
        System.out.println("T: Print Total available amount ");
        System.out.println("Q: Exit program ");
        System.out.println("number: to withdraw money");

        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();
            try {
                double amount = Double.parseDouble(input);
                try {
                    System.out.println("You get following bank notes : " + atmMachine.dispenseMoney(amount));
                } catch (NotEnoughBankNoteException e) {
                    System.out.println("You do not get money, the maximum number you can enter is " + e.getMaximumAmountAvailable());
                }
            } catch (NumberFormatException notNumberException) {
                if ("R".equalsIgnoreCase(input)) {
                    atmMachine.printBankNotesReport();
                } else if ("T".equalsIgnoreCase(input)) {
                    atmMachine.printTotalBathAmount();
                } else if ("Q".equalsIgnoreCase(input)) {
                    System.out.println("Exit program!");
                    break;
                } else {
                    System.out.println("You do not enter the number, please try again");
                }
            }
        }

        in.close();
    }
}
