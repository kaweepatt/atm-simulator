package org.kaweepatt.exciteholiday.exam.atm.model;

public enum BankNoteType {
    B20(20), B50(50), B100(100), B500(500), B1000(1000);

    private int bathAmount;

    private BankNoteType(int bathAmount) {
        this.bathAmount = bathAmount;
    }

    public int getBathAmount() {
        return this.bathAmount;
    }

}
