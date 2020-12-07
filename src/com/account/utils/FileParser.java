package com.account.utils;

import com.account.bean.AccountTransaction;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class FileParser {

    public static List<AccountTransaction> parseCsvFile(String filePath) {
        Path pathToFile = Paths.get(filePath);
        List<AccountTransaction> accountTransactionList = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String headLine = br.readLine();
            String line = br.readLine();
            while(line!= null) {
                //System.out.println(line);
                accountTransactionList.add(mapLineToAccountTransaction(line));
                line = br.readLine();
            }
        } catch (IOException e ) {
            System.out.println(e);
        }
        return accountTransactionList;
    }

    private static AccountTransaction mapLineToAccountTransaction(String line) {
        String[] lineSplit = line.split(",");
        String accountID = lineSplit[0];
        double closingBalance = getClosingBalance(lineSplit[1]);
        double amountCredit =  getAmountCredit(lineSplit[2]);
        double amountDebit =  getAmountDebit(lineSplit[3]);
        Date recordedDate = getDateInFormat(lineSplit[4]);

        return new AccountTransaction(accountID, closingBalance, amountCredit, amountDebit, recordedDate);
    }

    public static Date getDateInFormat(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date recordedDate = null;
        try {
            recordedDate = sdf.parse(s);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return recordedDate;
    }

    private static double getClosingBalance(String s) {
        double closingBalance = 0.0;
        try{
            closingBalance = Double.parseDouble(s);
        } catch(NumberFormatException nfe) {
            System.out.println(nfe);
        }
        return closingBalance;
    }

    private static double getAmountCredit(String s) {
        double amountCredit = 0.0;
        try{
            amountCredit = Double.parseDouble(s);
        } catch(NumberFormatException nfe) {
            System.out.println(nfe);
        }
        return amountCredit;
    }

    private static double getAmountDebit(String s) {
        double amountDebit = 0.0;
        try{
            amountDebit = Double.parseDouble(s);
        } catch(NumberFormatException nfe) {
            System.out.println(nfe);
        }
        return amountDebit;
    }
}
