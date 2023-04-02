import java.io.*;

public class Basket implements Serializable {
    private final String[] products;
    private final int[] prices;
    private final int[] count;

    private int sumProducts;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        this.count = new int[products.length];
    }

    public Basket(String[] products, int[] prices, int[] count, int sumProducts) {
        this.products = products;
        this.prices = prices;
        this.count = count;
        this.sumProducts = sumProducts;
    }

    public void printProducts() {
        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i] + " " + prices[i] + " руб/шт");
        }
    }

    public void addToCard(int product, int amount) {
        int currentPrice = prices[product];
        sumProducts += amount * currentPrice;
        count[product] += amount;
    }

    public void printCart() {
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                System.out.println(
                        products[i] + " " + count[i] + " шт " + prices[i] + " руб/шт " + (count[i] * prices[i]) + " руб в сумме");
            }
        }
        System.out.println("Итого: " + this.sumProducts + " руб");
    }

    public void saveBin(File file) throws Exception {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            Basket basket = new Basket(products, prices, count, sumProducts);
            out.writeObject(basket);
        }
    }

    public static Basket loadFromBinFile(File binFile) throws Exception {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(binFile))) {
            return (Basket) in.readObject();
        }
    }
}