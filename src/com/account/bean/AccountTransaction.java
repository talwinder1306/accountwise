package com.account.bean;

import java.util.Date;
import java.util.Objects;

public class AccountTransaction {
    private String accountID;
    private double closingBalance;
    private double amountCredit;
    private double amountDebit;
    private final Date recordedDate;

    public AccountTransaction(String accountID, double closingBalance, double amountCredit, double amountDebit, Date recordedDate) {
        this.accountID = accountID;
        this.closingBalance = closingBalance;
        this.amountCredit = amountCredit;
        this.amountDebit = amountDebit;
        this.recordedDate = recordedDate;
    }

    public String getAccountID() {
        return accountID;
    }

    public double getClosingBalance() {
        return closingBalance;
    }

    public double getAmountCredit() {
        return amountCredit;
    }

    public double getAmountDebit() {
        return amountDebit;
    }

    public Date getRecordedDate() {
        return recordedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransaction that = (AccountTransaction) o;
        return Double.compare(that.closingBalance, closingBalance) == 0 &&
                Double.compare(that.amountCredit, amountCredit) == 0 &&
                Double.compare(that.amountDebit, amountDebit) == 0 &&
                accountID.equals(that.accountID) &&
                Objects.equals(recordedDate, that.recordedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountID, closingBalance, amountCredit, amountDebit, recordedDate);
    }
}
