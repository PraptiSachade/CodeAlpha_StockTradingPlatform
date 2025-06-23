import java.util.*;

class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    double balance = 10000.0; // starting money

    void buyStock(String name, int quantity, double price) {
        double total = quantity * price;
        if (balance >= total) {
            balance -= total;
            holdings.put(name, holdings.getOrDefault(name, 0) + quantity);
            System.out.println("✅ Bought " + quantity + " shares of " + name);
        } else {
            System.out.println("❌ Insufficient funds!");
        }
    }

    void sellStock(String name, int quantity, double price) {
        if (holdings.containsKey(name) && holdings.get(name) >= quantity) {
            balance += quantity * price;
            holdings.put(name, holdings.get(name) - quantity);
            System.out.println("💰 Sold " + quantity + " shares of " + name);
        } else {
            System.out.println("❌ Not enough shares to sell.");
        }
    }

    void viewPortfolio() {
        System.out.println("\n📊 Portfolio:");
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " shares");
        }
        System.out.println("💵 Available Balance: ₹" + balance);
    }
}

public class StockTradingPlatform {
    static Scanner sc = new Scanner(System.in);
    static Map<String, Stock> market = new HashMap<>();
    static Portfolio portfolio = new Portfolio();

    public static void main(String[] args) {
        initializeMarket();

        while (true) {
            System.out.println("\n--- 📈 Stock Trading Menu ---");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    viewMarket();
                    break;
                case 2:
                    buyStock();
                    break;
                case 3:
                    sellStock();
                    break;
                case 4:
                    portfolio.viewPortfolio();
                    break;
                case 5:
                    System.out.println("📤 Exiting Trading Platform. Goodbye!");
                    return;
                default:
                    System.out.println("❗ Invalid choice.");
            }
        }
    }

    static void initializeMarket() {
        market.put("TCS", new Stock("TCS", 3500));
        market.put("INFY", new Stock("INFY", 1450));
        market.put("RELIANCE", new Stock("RELIANCE", 2800));
        market.put("WIPRO", new Stock("WIPRO", 420));
    }

    static void viewMarket() {
        System.out.println("\n📊 Market Stocks:");
        for (Stock s : market.values()) {
            System.out.println(s.name + " - ₹" + s.price);
        }
    }

    static void buyStock() {
        System.out.print("Enter stock name to buy: ");
        String name = sc.nextLine().toUpperCase();
        if (!market.containsKey(name)) {
            System.out.println("❌ Stock not found.");
            return;
        }

        System.out.print("Enter quantity to buy: ");
        int qty = sc.nextInt();
        Stock stock = market.get(name);
        portfolio.buyStock(name, qty, stock.price);
    }

    static void sellStock() {
        System.out.print("Enter stock name to sell: ");
        String name = sc.nextLine().toUpperCase();
        if (!market.containsKey(name)) {
            System.out.println("❌ Stock not found.");
            return;
        }

        System.out.print("Enter quantity to sell: ");
        int qty = sc.nextInt();
        Stock stock = market.get(name);
        portfolio.sellStock(name, qty, stock.price);
    }
}