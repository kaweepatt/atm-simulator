package org.kaweepatt.exciteholiday.exam.atm.model;

public class NotEnoughBankNoteException extends Exception {

    private static final long serialVersionUID = 2324712657097537391L;

    private double maximumAmountAvailable;

    public NotEnoughBankNoteException(double maximumAmountAvailable) {
        this.maximumAmountAvailable = maximumAmountAvailable;
    }

    public double getMaximumAmountAvailable() {
        return maximumAmountAvailable;
    }

    public void setMaximumAmountAvailable(double maximumAmountAvailable) {
        this.maximumAmountAvailable = maximumAmountAvailable;
    }

}
