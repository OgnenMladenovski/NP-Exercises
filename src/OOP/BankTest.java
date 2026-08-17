package OOP;

import java.util.List;

enum AccountType {
    INTEREST,
    NON_INTEREST
}

abstract class Account {

    private static long ACCOUNT_NUMBER = 1;
    private String name;
    private long accountNumber;
    private double accountBalance;
    private AccountType accountType;

    public Account(String name, double accountBalance) {
        this.name = name;
        this.accountNumber = ACCOUNT_NUMBER++;
        this.accountBalance = accountBalance;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public double addCash(double amount) {
        accountBalance += amount;
        return accountBalance;
    }

    public double removeCash(double amount) {
        if(accountBalance >= amount)
        {
            accountBalance -= amount;
        }
        return accountBalance;
    }

    public AccountType getAccountType() {
        return accountType;
    }
}

interface InterestBearingAccount {
    void addInterest();
}

class InterestCheckingAccount extends Account implements InterestBearingAccount {

    private static final double INTEREST_RATE = 0.03;

    public InterestCheckingAccount(String name, double accountBalance) {
        super(name, accountBalance);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.INTEREST;
    }

    @Override
    public void addInterest() {
        super.setAccountBalance(super.getAccountBalance() * (1 + INTEREST_RATE));
    }
}

class PlatinumCheckingAccount extends InterestCheckingAccount {

    private static final double INTEREST_RATE = 0.03;

    public PlatinumCheckingAccount(String name, double accountBalance) {
        super(name, accountBalance);
    }

    @Override
    public void addInterest() {
        super.setAccountBalance(super.getAccountBalance() * (1 + INTEREST_RATE * 2));
    }
}

class NonInterestCheckingAccount extends Account {

    public NonInterestCheckingAccount(String name, double accountBalance) {
        super(name, accountBalance);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.NON_INTEREST;
    }
}

class Bank {

    private List<Account> accounts;

    public Bank(List<Account> accounts) {
        this.accounts = accounts;
    }

    public double totalAssets() {
        double count = 0.0;

        for (Account a : accounts)
        {
            count += a.getAccountBalance();
        }

        return count;
    }

    public void addInterest() {
        for (Account a : accounts) {
            if(a.getAccountType().equals(AccountType.INTEREST))
            {
                ((InterestBearingAccount) a).addInterest();
            }
        }
    }
}

public class BankTest {

    public static void main(String[] args) {

        Account a1 = new InterestCheckingAccount("John", 1000);
        Account a2 = new PlatinumCheckingAccount("Peter", 2000);
        Account a3 = new NonInterestCheckingAccount("Mark", 500);

        Bank bank = new Bank(List.of(a1, a2, a3));

        System.out.println(bank.totalAssets());

        bank.addInterest();

        System.out.println(a1.getAccountBalance());
        System.out.println(a2.getAccountBalance());
        System.out.println(a3.getAccountBalance());

    }
}