package org.example.coffe.manager;

import org.example.coffe.models.Order;
import org.example.coffe.models.OrderStatus;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CoffeeOrderManager {

    private final Map<String, List<Order>> ordersByCustomer = new HashMap<>();
    private final TreeMap<LocalDateTime, Order> orderByTime = new TreeMap<>();

    public Map<String, List<Order>> getOrdersByCustomer() {
        return ordersByCustomer;
    }

    public void addOrder(Order order) {
        ordersByCustomer
                .computeIfAbsent(order.getEmail(), orders -> new ArrayList<>())
                .add(order);
        orderByTime.put(order.getTime(), order);
        checkSuspiciousActivity(order.getEmail());
    }

    private void checkSuspiciousActivity(String email) {
        List<Order> orders = ordersByCustomer.get(email);
        if (orders.size() < 5) return;

        LocalDateTime now = LocalDateTime.now();
        long count = orders.stream()
                .filter(recentOrder -> Duration.between(recentOrder.getTime(), now).toMinutes() <= 10)
                .count();

        if (count > 5) {
            System.out.println("Подозрительная активность у " + email);
        }
    }

    public void cancelOrder(String orderId) {
        ordersByCustomer.values().forEach(orders ->
                orders.stream()
                        .filter(order -> order.getId().equals(orderId))
                        .findFirst()
                        .ifPresent(order -> order.setStatus(OrderStatus.CANCELLED))
        );
    }

    public List<String> getTopDrinksToday() {
        LocalDate today = LocalDate.now();
        return orderByTime.values().stream()
                .filter(order -> order.getTime().toLocalDate().equals(today))
                .filter(order -> order.getStatus() == OrderStatus.COMPLETED)
                .collect(Collectors.groupingBy(Order::getDrink, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .map(Map.Entry::getKey)
                .toList();
    }

    public Set<String> getLoyalCustomers() {
        LocalDateTime weekAgo = LocalDateTime.now().minusDays(7);
        return ordersByCustomer.entrySet().stream()
                .filter(orders -> orders.getValue().stream()
                        .filter(order -> order.getTime().isAfter(weekAgo))
                        .count() >= 3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public Map<String, Long> getPeakHoursStats() {
        return orderByTime.values().stream()
                .collect(Collectors.groupingBy(order -> {
                    int hour = order.getTime().getHour();
                    if (hour >= 7 && hour < 11) return "morning";
                    if (hour >= 11 && hour < 15) return "lunch";
                    if (hour >= 15 && hour < 22) return "evening";
                    return "other";
                }, Collectors.counting()));
    }

    public double getAverageOrderValue() {
        LocalDateTime dayAgo = LocalDateTime.now().minusHours(24);
        double averageOrderValue = orderByTime.values().stream()
                .filter(order -> order.getTime().isAfter(dayAgo))
                .mapToInt(Order::getPrice)
                .average()
                .orElse(0.0);
        return Math.round(averageOrderValue * 100.0) / 100.0;
    }

    public void exportToCsv() {
        Set<String> loyal = getLoyalCustomers();
        System.out.println("Лояльные клиенты:");
        loyal.forEach(System.out::println);
    }
}
