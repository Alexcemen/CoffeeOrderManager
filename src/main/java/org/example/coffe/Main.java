package org.example.coffe;

import org.example.coffe.manager.CoffeeOrderManager;
import org.example.coffe.models.Order;
import org.example.coffe.models.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;


public class Main {

    public static void main(String[] args) {
        CoffeeOrderManager coffeeOrderManager = new CoffeeOrderManager();
        fillOrderByCustomerMap(coffeeOrderManager);

        System.out.println("Топ-5 самых популярных напитков за сегодня: " + coffeeOrderManager.getTopDrinksToday());
        System.out.println();

        System.out.println("Статистика по времени: " + coffeeOrderManager.getPeakHoursStats());
        System.out.println();

        System.out.println("Среднюю сумму заказа за последние 24 часа: " + coffeeOrderManager.getAverageOrderValue());
        System.out.println();

        coffeeOrderManager.exportToCsv();
        System.out.println();



        List<Order> allOrders = coffeeOrderManager.getOrdersByCustomer().values().stream()
                .flatMap(List::stream).toList();
        Order anyOrder = allOrders.get(new Random().nextInt(allOrders.size()));

        System.out.println("UUID выбранного заказа: " + anyOrder.getId());
        System.out.println("До удаления: " + anyOrder);

        coffeeOrderManager.cancelOrder(anyOrder.getId());
        System.out.println("После удаления: " + anyOrder);
    }




















    public static void fillOrderByCustomerMap(CoffeeOrderManager coffeeOrderManager) {
        coffeeOrderManager.addOrder(new Order("alex@coffee.com", "Latte", "Medium", 280, LocalDateTime.now().minusHours(1), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("alex@coffee.com", "Flat White", "Large", 320, LocalDateTime.now().minusHours(2), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("alex@coffee.com", "Cappuccino", "Small", 250, LocalDateTime.now().minusDays(1), OrderStatus.PENDING));
        coffeeOrderManager.addOrder(new Order("alex@coffee.com", "Espresso", "Small", 200, LocalDateTime.now().minusDays(2), OrderStatus.CANCELLED));

        coffeeOrderManager.addOrder(new Order("maria@coffee.com", "Latte", "Large", 300, LocalDateTime.now().minusHours(3), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("maria@coffee.com", "Cappuccino", "Medium", 260, LocalDateTime.now().minusDays(3), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("maria@coffee.com", "Americano", "Large", 270, LocalDateTime.now().minusHours(5), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("maria@coffee.com", "Latte", "Small", 250, LocalDateTime.now().minusDays(4), OrderStatus.COMPLETED));

        coffeeOrderManager.addOrder(new Order("ivan@coffee.com", "Espresso", "Small", 200, LocalDateTime.now().minusHours(1), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("ivan@coffee.com", "Flat White", "Large", 320, LocalDateTime.now().minusHours(8), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("ivan@coffee.com", "Cappuccino", "Medium", 260, LocalDateTime.now().minusDays(1), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("ivan@coffee.com", "Latte", "Medium", 280, LocalDateTime.now().minusDays(6), OrderStatus.COMPLETED));

        coffeeOrderManager.addOrder(new Order("sofia@coffee.com", "Cappuccino", "Large", 300, LocalDateTime.now().minusHours(2), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("sofia@coffee.com", "Americano", "Medium", 260, LocalDateTime.now().minusHours(4), OrderStatus.PENDING));
        coffeeOrderManager.addOrder(new Order("sofia@coffee.com", "Latte", "Small", 250, LocalDateTime.now().minusDays(3), OrderStatus.CANCELLED));

        coffeeOrderManager.addOrder(new Order("nikolay@coffee.com", "Espresso", "Small", 200, LocalDateTime.now().minusHours(7), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("nikolay@coffee.com", "Latte", "Large", 300, LocalDateTime.now().minusHours(6), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("nikolay@coffee.com", "Cappuccino", "Large", 320, LocalDateTime.now().minusDays(2), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("nikolay@coffee.com", "Latte", "Medium", 280, LocalDateTime.now().minusDays(5), OrderStatus.COMPLETED));

        coffeeOrderManager.addOrder(new Order("olga@coffee.com", "Flat White", "Large", 320, LocalDateTime.now().minusHours(1), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("olga@coffee.com", "Latte", "Small", 240, LocalDateTime.now().minusDays(1), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("olga@coffee.com", "Americano", "Medium", 260, LocalDateTime.now().minusDays(2), OrderStatus.PENDING));
        coffeeOrderManager.addOrder(new Order("olga@coffee.com", "Espresso", "Small", 200, LocalDateTime.now().minusDays(3), OrderStatus.CANCELLED));

        coffeeOrderManager.addOrder(new Order("denis@coffee.com", "Latte", "Medium", 280, LocalDateTime.now().minusHours(2), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("denis@coffee.com", "Flat White", "Medium", 290, LocalDateTime.now().minusDays(1), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("denis@coffee.com", "Cappuccino", "Small", 250, LocalDateTime.now().minusDays(4), OrderStatus.PENDING));
        coffeeOrderManager.addOrder(new Order("denis@coffee.com", "Espresso", "Small", 200, LocalDateTime.now().minusDays(6), OrderStatus.COMPLETED));

        coffeeOrderManager.addOrder(new Order("kate@coffee.com", "Americano", "Large", 300, LocalDateTime.now().minusHours(3), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("kate@coffee.com", "Latte", "Medium", 280, LocalDateTime.now().minusHours(5), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("kate@coffee.com", "Flat White", "Large", 320, LocalDateTime.now().minusHours(6), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("kate@coffee.com", "Cappuccino", "Medium", 260, LocalDateTime.now().minusDays(7), OrderStatus.COMPLETED));

        coffeeOrderManager.addOrder(new Order("alex@coffee.com", "Americano", "Small", 220, LocalDateTime.now().withHour(7).minusMinutes(10), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("maria@coffee.com", "Espresso", "Medium", 210, LocalDateTime.now().withHour(8).minusMinutes(5), OrderStatus.PENDING));
        coffeeOrderManager.addOrder(new Order("ivan@coffee.com", "Latte", "Large", 310, LocalDateTime.now().withHour(9), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("sofia@coffee.com", "Flat White", "Small", 230, LocalDateTime.now().withHour(10), OrderStatus.CANCELLED));
        coffeeOrderManager.addOrder(new Order("nikolay@coffee.com", "Cappuccino", "Medium", 270, LocalDateTime.now().withHour(8), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("olga@coffee.com", "Americano", "Large", 300, LocalDateTime.now().withHour(7), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("denis@coffee.com", "Espresso", "Small", 200, LocalDateTime.now().withHour(10), OrderStatus.PENDING));
        coffeeOrderManager.addOrder(new Order("kate@coffee.com", "Latte", "Small", 250, LocalDateTime.now().withHour(9), OrderStatus.COMPLETED));

        coffeeOrderManager.addOrder(new Order("alex@coffee.com", "Cappuccino", "Large", 320, LocalDateTime.now().withHour(12), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("maria@coffee.com", "Americano", "Medium", 260, LocalDateTime.now().withHour(13), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("ivan@coffee.com", "Flat White", "Medium", 290, LocalDateTime.now().withHour(11), OrderStatus.PENDING));
        coffeeOrderManager.addOrder(new Order("sofia@coffee.com", "Espresso", "Small", 200, LocalDateTime.now().withHour(14), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("nikolay@coffee.com", "Latte", "Large", 310, LocalDateTime.now().withHour(12), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("olga@coffee.com", "Cappuccino", "Small", 250, LocalDateTime.now().withHour(11), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("denis@coffee.com", "Americano", "Medium", 270, LocalDateTime.now().withHour(14), OrderStatus.CANCELLED));
        coffeeOrderManager.addOrder(new Order("kate@coffee.com", "Espresso", "Large", 210, LocalDateTime.now().withHour(13), OrderStatus.COMPLETED));

        coffeeOrderManager.addOrder(new Order("alex@coffee.com", "Flat White", "Medium", 290, LocalDateTime.now().withHour(17), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("maria@coffee.com", "Latte", "Large", 300, LocalDateTime.now().withHour(19), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("ivan@coffee.com", "Americano", "Small", 220, LocalDateTime.now().withHour(20), OrderStatus.PENDING));
        coffeeOrderManager.addOrder(new Order("sofia@coffee.com", "Cappuccino", "Large", 320, LocalDateTime.now().withHour(18), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("nikolay@coffee.com", "Espresso", "Medium", 210, LocalDateTime.now().withHour(21), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("olga@coffee.com", "Latte", "Small", 240, LocalDateTime.now().withHour(16), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("denis@coffee.com", "Flat White", "Large", 320, LocalDateTime.now().withHour(15), OrderStatus.PENDING));
        coffeeOrderManager.addOrder(new Order("kate@coffee.com", "Cappuccino", "Medium", 260, LocalDateTime.now().withHour(19), OrderStatus.COMPLETED));

        coffeeOrderManager.addOrder(new Order("alex@coffee.com", "Espresso", "Medium", 210, LocalDateTime.now().withHour(23), OrderStatus.CANCELLED));
        coffeeOrderManager.addOrder(new Order("maria@coffee.com", "Cappuccino", "Small", 250, LocalDateTime.now().withHour(2), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("ivan@coffee.com", "Latte", "Large", 300, LocalDateTime.now().withHour(3), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("sofia@coffee.com", "Americano", "Medium", 270, LocalDateTime.now().withHour(4), OrderStatus.PENDING));
        coffeeOrderManager.addOrder(new Order("nikolay@coffee.com", "Flat White", "Small", 230, LocalDateTime.now().withHour(5), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("olga@coffee.com", "Espresso", "Large", 210, LocalDateTime.now().withHour(22), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("denis@coffee.com", "Latte", "Medium", 280, LocalDateTime.now().withHour(1), OrderStatus.COMPLETED));
        coffeeOrderManager.addOrder(new Order("kate@coffee.com", "Americano", "Small", 220, LocalDateTime.now().withHour(6), OrderStatus.PENDING));
    }
}