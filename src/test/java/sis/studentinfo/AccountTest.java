package sis.studentinfo;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    @Test
    public void testTransactions() {
        Account account = new Account();
        account.credit(new BigDecimal("0.10"));
        account.credit(new BigDecimal("11.10"));
        assertEquals(new BigDecimal("11.1"), account.getBalance());
    }
    
    @Test
    public void testTransactionAverage() {
        Account account = new Account();
        account.credit(new BigDecimal(0.10));
        account.credit(new BigDecimal(11.00));
        account.credit(new BigDecimal(2.99));
        
        assertEquals(new BigDecimal(4.70), account.transactionAverage());
    }
}
