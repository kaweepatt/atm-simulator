/**
 * 
 */
package org.kaweepatt.exciteholiday.exam.atm.model;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kaweepatt
 *
 */
public class ATMMachine {

    private PrintStream screenStream = System.out;
    private Map<BankNoteType, Integer> bankNotes = new HashMap<BankNoteType, Integer>();

    /**
     * 
     * @param screenStream
     */
    public ATMMachine(PrintStream screenStream) {
        this.screenStream = screenStream;
    }

    /**
     * 
     * @param bankNotes
     */
    public void initial(Map<BankNoteType, Integer> bankNotes) {
        this.bankNotes = bankNotes;
    }

    /**
     * 
     */
    public void printBankNotesReport() {
        screenStream.println(">> Number of Bank note");
        screenStream.println(this.bankNotes);
    }

    /**
     * 
     */
    public void printTotalBathAmount() {
        screenStream.println(">> Total Bath Amount");
        screenStream.println(this.getTotalBathAmount());
    }

    /**
     * 
     * @return
     */
    public double getTotalBathAmount() {
        double amount = 0;
        for (BankNoteType type : bankNotes.keySet()) {
            amount += type.getBathAmount() * bankNotes.get(type);
        }
        return amount;
    }

    /**
     * 
     * @param bankNoteType
     * @return
     */
    public int getBankNoteQuantityByType(BankNoteType bankNoteType) {
        return this.bankNotes.get(bankNoteType);
    }

    /**
     * 
     * @param amount
     * @return
     * @throws NotEnoughBankNoteException
     */
    public Map<BankNoteType, Integer> dispenseMoney(double amount) throws NotEnoughBankNoteException {
        double remainingAmount = amount;
        Map<BankNoteType, Integer> bankNotesToBeUse = new HashMap<BankNoteType, Integer>();
        for (BankNoteType eligibleBankNoteType : bankNotes.keySet()) {
            int numberOfBankNoteToUse = (int) (remainingAmount / eligibleBankNoteType.getBathAmount());
            int bankNoteQuantity = this.getBankNoteQuantityByType(eligibleBankNoteType);
            if (bankNoteQuantity < numberOfBankNoteToUse) {
                numberOfBankNoteToUse = bankNoteQuantity;
            }
            remainingAmount = remainingAmount - (numberOfBankNoteToUse * eligibleBankNoteType.getBathAmount());

            if (numberOfBankNoteToUse > 0) {
                bankNotesToBeUse.put(eligibleBankNoteType, numberOfBankNoteToUse);
            }
        }

        if (remainingAmount > 0) {
            throw new NotEnoughBankNoteException(amount - remainingAmount);
        }

        this.deductBankNote(bankNotesToBeUse);

        return bankNotesToBeUse;
    }

    /**
     * 
     * @param deductBankNotes
     */
    private void deductBankNote(Map<BankNoteType, Integer> deductBankNotes) {
        for (BankNoteType bankNoteType : deductBankNotes.keySet()) {
            int currentQuantity = this.bankNotes.get(bankNoteType);
            this.bankNotes.replace(bankNoteType, currentQuantity - deductBankNotes.get(bankNoteType));
        }
    }
}
