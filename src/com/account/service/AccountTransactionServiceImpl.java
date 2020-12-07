package com.account.service;

import com.account.bean.AccountTransaction;
import com.account.utils.FileParser;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class AccountTransactionServiceImpl implements AccountTransactionService {

    @Override
    public double findTotalClosingBalance(String csvFileName, String date) {

        List<AccountTransaction> accountTransactionList = FileParser.parseCsvFile(csvFileName);
        double totalClosingBalance =
                accountTransactionList.stream()
                .filter(e -> e.getRecordedDate().equals(FileParser.getDateInFormat(date)))
                .mapToDouble(AccountTransaction::getClosingBalance)
                .sum();
        //System.out.println(totalClosingBalance);
        return totalClosingBalance;
    }

    @Override
    public double findAverageMonthlyBalance(String csvFileName, String accountId, int month) {
        List<AccountTransaction> accountTransactionList = FileParser.parseCsvFile(csvFileName);
        Calendar cal = Calendar.getInstance();
        double monthlyBalance =
                accountTransactionList.stream()
                        .filter(e -> e.getAccountID().equalsIgnoreCase(accountId))
                        .filter(e -> {
                            cal.setTime(e.getRecordedDate());
                            return cal.get(Calendar.MONTH) == month-1;
                        })
                        .mapToDouble(AccountTransaction::getClosingBalance)
                        .sum();
        YearMonth yearMonthObject = YearMonth.of(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear(), month);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        double averageMonthlyBalance = monthlyBalance/daysInMonth;
        //System.out.println(averageMonthlyBalance);

        return averageMonthlyBalance;
    }

    @Override
    public List<String> findLowestMonthlyBalanceAccount(String csvFileName, int month) {
       List<AccountTransaction> accountTransactionList = FileParser.parseCsvFile(csvFileName);
        Calendar cal = Calendar.getInstance();

        Map<String, Double> accountTransactionForMonth = accountTransactionList.stream()
                        .filter(e -> {
                            cal.setTime(e.getRecordedDate());
                            return cal.get(Calendar.MONTH) == month-1;
                        })
                       .collect(Collectors.groupingBy(AccountTransaction::getAccountID, Collectors.summingDouble(AccountTransaction::getClosingBalance)));

        //accountTransactionForMonth.entrySet().stream().forEach(a -> System.out.println(a.getValue()));
        Optional<Map.Entry<String,Double>> minimum = accountTransactionForMonth
                            .entrySet()
                            .stream()
                            .min( (a,b) -> a.getValue().compareTo(b.getValue()));

        if(minimum.isEmpty()){
            return new ArrayList<>();
        }
        //minimum.stream().forEach(a -> System.out.println(a.getKey()));
        return minimum.stream().map(a -> a.getKey()).collect(Collectors.toList());
    }

    @Override
    public List<String> findTopSpender(String csvFileName, String fromDate, String toDate) {
        List<AccountTransaction> accountTransactionList = FileParser.parseCsvFile(csvFileName);
        Date fromDateObj = FileParser.getDateInFormat(fromDate);
        Date toDateObj = FileParser.getDateInFormat(toDate);

        Map<String, Double> amountDebitForDuration = accountTransactionList.stream()
                .filter(e -> e.getRecordedDate().equals(fromDateObj) || e.getRecordedDate().equals(toDateObj)
                        || (e.getRecordedDate().after(fromDateObj) && e.getRecordedDate().before(toDateObj)))
                .collect(Collectors.groupingBy(AccountTransaction::getAccountID,
                        Collectors.summingDouble(AccountTransaction::getAmountDebit)));

        //amountDebitForDuration.entrySet().stream().forEach(a -> System.out.println(a.getKey() + " " + a.getValue()));
        Optional<Map.Entry<String,Double>> topSpender = amountDebitForDuration
                .entrySet()
                .stream()
                .max((a,b) -> a.getValue().compareTo(b.getValue()));

        if(topSpender.isEmpty()){
            return new ArrayList<>();
        }
        //topSpender.stream().forEach(a -> System.out.println(a.getKey()));
        return topSpender.stream().map(a -> a.getKey()).collect(Collectors.toList());
    }
}
