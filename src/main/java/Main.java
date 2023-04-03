import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Basket basket;
        basket = Shop.load();
        System.out.println("Список возможных товаров для покупки");
        basket.printProducts();
        ClientLog person1 = new ClientLog();
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
            person1.log(productNumber, productCount);
        }
        System.out.println("Ваша корзина:");
        basket.printCart();
        Shop.save(basket);
        Shop.log(person1);
    }
}