import java.util.HashMap;
import java.util.Map;

class CheckingModule {
    private double balance;

    public CheckingModule(double initialBalance) {
        this.balance = initialBalance;
    }

    public double checkBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class SavingModule {
    private double balance;

    public SavingModule(double initialBalance) {
        this.balance = initialBalance;
    }

    public double checkBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class InvestmentModule {
    private double balance;
    private Map<String, Double> investments;

    public InvestmentModule(double initialBalance) {
        this.balance = initialBalance;
        this.investments = new HashMap<>();
    }

    public double checkBalance() {
        return balance;
    }

    public void invest(String investmentName, double amount) {
        if (balance >= amount) {
            balance -= amount;
            investments.put(investmentName, investments.getOrDefault(investmentName, 0.0) + amount);
        }
    }

    public boolean withdrawFromInvestment(String investmentName, double amount) {
        if (investments.containsKey(investmentName) && investments.get(investmentName) >= amount) {
            investments.put(investmentName, investments.get(investmentName) - amount);
            balance += amount;
            return true;
        }
        return false;
    }
}


class BankingFacade {
    private CheckingModule checkingModule;
    private SavingModule savingModule;
    private InvestmentModule investmentModule;

    public BankingFacade() {
        checkingModule = new CheckingModule(1000);
        savingModule = new SavingModule(5000);
        investmentModule = new InvestmentModule(10000);
    }

    public double checkCheckingBalance() {
        return checkingModule.checkBalance();
    }

    public void depositToChecking(double amount) {
        checkingModule.deposit(amount);
    }

    public boolean withdrawFromChecking(double amount) {
        return checkingModule.withdraw(amount);
    }

    public double checkSavingBalance() {
        return savingModule.checkBalance();
    }

    public void depositToSaving(double amount) {
        savingModule.deposit(amount);
    }

    public boolean withdrawFromSaving(double amount) {
        return savingModule.withdraw(amount);
    }

    public double checkInvestmentBalance() {
        return investmentModule.checkBalance();
    }

    public void investInInvestment(String investmentName, double amount) {
        investmentModule.invest(investmentName, amount);
    }

    public boolean withdrawFromInvestment(String investmentName, double amount) {
        return investmentModule.withdrawFromInvestment(investmentName, amount);
    }
}


public class Main {
    public static void main(String[] args) {
        BankingFacade bankingFacade = new BankingFacade();
        System.out.println("Checking Account Operations:");
        System.out.println("Balance: $" + bankingFacade.checkCheckingBalance());
        bankingFacade.depositToChecking(2000);
        System.out.println("Deposited $2000");
        System.out.println("Balance: $" + bankingFacade.checkCheckingBalance());
        boolean withdrawn = bankingFacade.withdrawFromChecking(500);
        System.out.println("Withdrawn $500: " + (withdrawn ? "Success" : "Insufficient Funds"));
        System.out.println("Balance: $" + bankingFacade.checkCheckingBalance());
        System.out.println("\nSaving Account Operations:");
        System.out.println("Balance: $" + bankingFacade.checkSavingBalance());
        bankingFacade.depositToSaving(1000);
        System.out.println("Deposited $1000");
        System.out.println("Balance: $" + bankingFacade.checkSavingBalance());
        withdrawn = bankingFacade.withdrawFromSaving(300);
        System.out.println("Withdrawn $300: " + (withdrawn ? "Success" : "Insufficient Funds"));
        System.out.println("Balance: $" + bankingFacade.checkSavingBalance());
        System.out.println("\nInvestment Account Operations:");
        System.out.println("Balance: $" + bankingFacade.checkInvestmentBalance());
        bankingFacade.investInInvestment("TechStocks", 3000);
        System.out.println("Invested $3000 in market");
        System.out.println("Balance: $" + bankingFacade.checkInvestmentBalance());
        withdrawn = bankingFacade.withdrawFromInvestment("market", 1000);
        System.out.println("Withdrawn $1000 from market: " + (withdrawn ? "Success" : "Insufficient Funds"));
        System.out.println("Balance: $" + bankingFacade.checkInvestmentBalance());
    }
}
