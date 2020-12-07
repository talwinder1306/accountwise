package com.account.service;

import java.util.Date;
import java.util.List;

public interface AccountTransactionService {

    double findTotalClosingBalance(String csvFileName, String date);

    double findAverageMonthlyBalance(String csvFileName, String accountId, int month);

    List<String> findLowestMonthlyBalanceAccount(String csvFileName, int month);

    List<String> findTopSpender(String csvFileName, String fromDate, String toDate);

}
