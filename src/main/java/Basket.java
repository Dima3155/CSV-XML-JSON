import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class Basket {
    private String[] products;
    private int[] prices;
    private int[] count;
    private int sumProducts;

    public String[] getProducts() {
        return products;
    }

    public int[] getPrices() {
        return prices;
    }

    public int[] getCount() {
        return count;
    }

    public int getSumProducts() {
        return sumProducts;
    }

    public void setSumProducts(int sumProducts) {
        this.sumProducts = sumProducts;
    }

    public void setProducts(String[] products) {
        this.products = products;
    }

    public void setPrices(int[] prices) {
        this.prices = prices;
    }

    public void setCount(int[] count) {
        this.count = count;
    }

    public Basket() {
    }

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

    public void saveTxt() throws Exception {
        try (PrintWriter out = new PrintWriter("basket.txt")) {
            for (int i = 0; i < products.length; i++) {
                if (i != (products.length - 1)) {
                    out.print(products[i] + " ");
                } else {
                    out.println(products[i]);
                }
            }
            for (int j = 0; j < prices.length; j++) {
                if (j != (prices.length - 1)) {
                    out.print(prices[j] + " ");
                } else {
                    out.println(prices[j]);
                }
            }
            for (int c = 0; c < count.length; c++) {
                if (c != (count.length - 1)) {
                    out.print(count[c] + " ");
                } else {
                    out.println(count[c]);
                }
            }
            out.println(sumProducts);
        }
    }

    public static Basket loadFromTxtFile() throws Exception {
        try (BufferedReader in = new BufferedReader(new FileReader("basket.txt"))) {
            String[] product = in.readLine().split(" ");
            String[] pricesString = in.readLine().split(" ");
            int[] prices = new int[pricesString.length];
            for (int i = 0; i < pricesString.length; i++) {
                prices[i] = Integer.parseInt(pricesString[i]);
            }
            String[] countString = in.readLine().split(" ");
            int[] count = new int[countString.length];
            for (int j = 0; j < countString.length; j++) {
                count[j] = Integer.parseInt(countString[j]);
            }
            int sumProducts = Integer.parseInt(in.readLine());
            return new Basket(product, prices, count, sumProducts);
        }
    }

    public void saveJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("basket.json"), this);
    }

    public static Basket loadFromJson() throws NullPointerException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File("basket.json"), Basket.class);
    }
}