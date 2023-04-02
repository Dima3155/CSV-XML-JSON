import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        File file = new File("basket.bin");
        Scanner scanner = new Scanner(System.in);
        Basket basket = new Basket(new String[]{"Хлеб", "Яблоки", "Молоко"}, new int[]{50, 15, 70});
        if (file.exists()) {
            basket = Basket.loadFromBinFile(file);
        }
        System.out.println("Список возможных товаров для покупки");
        basket.printProducts();
        while (true) {
            System.out.println("Введите товар и количество или введите 'end'");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }
            String[] parts = input.split(" ");
            int productNumber = Integer.parseInt(parts[0]) - 1;
            int productCount = Integer.parseInt(parts[1]);
            basket.addToCard(productNumber, productCount);
        }
        System.out.println("Ваша корзина:");
        basket.printCart();
        basket.saveBin(file);
    }
}