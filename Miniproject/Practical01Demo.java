import java.time.DayOfWeek;
import java.time.LocalDate;

// Practical 1: BankInfo record
record BankInfo(String name, String branch) {
    @Override
    public String toString() {
        return "=== " + name + " | " + branch + " ===";
    }
}

// Practical 1: MenuOption enum
enum MenuOption {
    OPEN_ACCOUNT("Open account"),
    DEPOSIT("Deposit"),
    WITHDRAW("Withdraw"),
    TRANSFER("Transfer"),
    STATEMENT("Account statement"),
    LIST_ACCOUNTS("List accounts (by balance)"),
    SEARCH_BY_OWNER("Search account by owner name"),
    QUICK_COMMAND("Quick command (e.g. DEPOSIT AC0001 500)"),
    BATCH_PROCESS("Process a batch of transactions (thread pool)"),
    REPORT("End-of-day report"),
    WORKING_HOURS("Bank working hours"),
    RUN_DEMOS("Run checks for every practical"),
    EXIT("Exit");
                

    private final String label;

    MenuOption(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}

public class Practical01Demo {
    public static void main(String[] args) {
        BankInfo info = new BankInfo("CHARUSAT MiniBank - CSPIT", "Aayushi Branch (D26CS121)");
        System.out.println(info);
        System.out.println();

        System.out.println("--- MiniBank Menu Options ---");
        MenuOption[] options = MenuOption.values();
        for (int i = 0; i < options.length; i++) {
            System.out.printf("  %2d. %s%n", i + 1, options[i].label());
        }

        System.out.println("\n--- Testing Switch Expression on Menu Choice ---");
        MenuOption choice = MenuOption.DEPOSIT;
        String action = switch (choice) {
            case OPEN_ACCOUNT -> "Execute: openAccount()";
            case DEPOSIT      -> "Execute: deposit()";
            case WITHDRAW     -> "Execute: withdraw()";
            case TRANSFER     -> "Execute: transfer()";
            case STATEMENT    -> "Execute: statement()";
            default           -> "Execute: generalHandler()";
        };
        System.out.println("Selected Option : " + choice);
        System.out.println("Switch Result   : " + action);

        System.out.println("\n--- Bank Working Hours Check ---");
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        String hours = switch (today) {
            case SATURDAY -> "Saturday: 10:00 - 13:00";
            case SUNDAY   -> "Sunday: Closed";
            default       -> "Mon - Fri: 10:00 - 16:00";
        };
        System.out.println("Today is " + today + " -> " + hours);
    }
}