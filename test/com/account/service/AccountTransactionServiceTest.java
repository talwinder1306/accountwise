package com.account.service;

import com.account.bean.AccountTransaction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AccountTransactionServiceTest {
    private AccountTransactionService accountTransactionService;
    private String fileName;
    private static final double DELTA = 1e-15;
    @Before
    public void setup(){
        fileName = "accounts.csv";
        accountTransactionService = new AccountTransactionServiceImpl();
    }

    @Test
    public void findTotalClosingBalanceShouldReturnValidResult() {
        System.out.println("findTotalClosingBalance should return Valid Result");
        System.out.println("Total balance for 2020-08-02 should be 4984");

        String date = "2020-08-02";
        double result = accountTransactionService
                .findTotalClosingBalance(fileName, date);

        assertEquals(4984, result, DELTA);

    }
    @Test
    public void findAverageMonthlyBalanceShouldReturnValidResult() {
        System.out.println("findAverageMonthlyBalance should return Valid Result");
        System.out.println("Total average balance for 0a46e798-2e5a-11eb-adc1-0242ac120002 " +
                "in month of August should be 1013.9032258064");

        String accountId = "0a46e798-2e5a-11eb-adc1-0242ac120002";

        int month = 8;
        double averageBal = accountTransactionService
                .findAverageMonthlyBalance(fileName, accountId, month);
        double expectedAverageBalance = 31431.0/31.0;

        assertEquals(expectedAverageBalance, averageBal, DELTA);

    }
    @Test
    public void findLowestMonthlyBalanceAccountShouldReturnValidResult() {
        System.out.println("findLowestMonthlyBalanceAccount should return Valid Result");
        System.out.println("Lowest Monthly Balance Account should be 0a46e888-2e5a-11eb-adc1-0242ac120002 " +
                "in month of August");

        int month = 8;
        List<String> accountwithLowestMonthlyBalance = accountTransactionService
                .findLowestMonthlyBalanceAccount(fileName, month);
        List<String> expectedAccount = new ArrayList<>();
        expectedAccount.add("0a46e888-2e5a-11eb-adc1-0242ac120002");
        for (String s : accountwithLowestMonthlyBalance) {
            System.out.println(s);
        }
        assertEquals(expectedAccount, accountwithLowestMonthlyBalance);
    }

    @Test
    public void findTopSpenderShouldReturnValidResult() {
        System.out.println("findTopSpender should return Valid Result");
        System.out.println("Top Spender Account between 2020-08-01 and 2020-08-25 " +
                "should be 0a46e798-2e5a-11eb-adc1-0242ac120002");

        String fromDate = "2020-08-01";

        String toDate = "2020-08-25";

        List<String> topSpender = accountTransactionService
                .findTopSpender(fileName, fromDate, toDate);

        List<String> expectedTopSpender = new ArrayList<>();
        expectedTopSpender.add("0a46e798-2e5a-11eb-adc1-0242ac120002");
        for(String s : topSpender){
            System.out.println(s);
        }

        assertEquals(expectedTopSpender, topSpender);
    }
}
