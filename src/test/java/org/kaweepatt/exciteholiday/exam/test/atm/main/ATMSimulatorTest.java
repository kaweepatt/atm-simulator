/**
 * 
 */
package org.kaweepatt.exciteholiday.exam.test.atm.main;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.kaweepatt.exciteholiday.exam.atm.model.ATMMachine;
import org.kaweepatt.exciteholiday.exam.atm.model.BankNoteType;
import org.kaweepatt.exciteholiday.exam.atm.model.NotEnoughBankNoteException;

/**
 * @author Kaweepatt
 *
 */
public class ATMSimulatorTest {

    private ATMMachine atmMachine;

    @Before
    public void setupATM() {
        this.atmMachine = new ATMMachine(System.out);
        Map<BankNoteType, Integer> bankNotes = new LinkedHashMap<BankNoteType, Integer>();
        bankNotes.put(BankNoteType.B1000, 10);
        bankNotes.put(BankNoteType.B500, 10);
        bankNotes.put(BankNoteType.B100, 10);
        bankNotes.put(BankNoteType.B50, 10);
        bankNotes.put(BankNoteType.B20, 10);

        this.atmMachine.initial(bankNotes);
    }

    @Test
    public void testSetup() {
        atmMachine.printBankNotesReport();
        atmMachine.printTotalBathAmount();

        assertTrue(atmMachine.getBankNoteQuantityByType(BankNoteType.B100) == 10);

        try {
            Map<BankNoteType, Integer> bankNotes = atmMachine.dispenseMoney(16700);
            assertTrue(bankNotes.get(BankNoteType.B1000) == 10);
            assertTrue(bankNotes.get(BankNoteType.B500) == 10);
            assertTrue(bankNotes.get(BankNoteType.B100) == 10);
            assertTrue(bankNotes.get(BankNoteType.B50) == 10);
            assertTrue(bankNotes.get(BankNoteType.B20) == 10);
        } catch (NotEnoughBankNoteException e) {
            fail("This setup should support dispense this amount");
        }

    }

    @Test
    public void testWithdrawSuccess() {
        try {
            atmMachine.dispenseMoney(1520);
            atmMachine.dispenseMoney(3000);
            atmMachine.dispenseMoney(570);
        } catch (NotEnoughBankNoteException e) {
            fail("This setup should support dispense this amount");
        }
    }

    @Test
    public void testWithdrawTooMuchFail() {
        try {
            atmMachine.dispenseMoney(20000);
        } catch (NotEnoughBankNoteException e) {
            assertTrue(e.getMaximumAmountAvailable() == 16700);
        }
    }

    @Test
    public void testWithdrawFractionFail() {
        try {
            atmMachine.dispenseMoney(30);
        } catch (NotEnoughBankNoteException e) {
            assertTrue(e.getMaximumAmountAvailable() == 20);
        }
    }

    @Test
    public void testWithdrawUntilEmpty() {
        try {
            atmMachine.dispenseMoney(16000);
            atmMachine.dispenseMoney(700);
            assertTrue(atmMachine.getTotalBathAmount() == 0);
        } catch (NotEnoughBankNoteException e) {
            fail("This setup should support dispense this amount");
        }
    }

}
