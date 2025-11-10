package org.example.colors;

import java.util.Map;
import java.util.TreeSet;
import java.util.function.ToIntFunction;

public class Colors {

    public static void main(String[] args) {
        System.out.println("=== Через Map ===");
        printColors(Colors::getPositionByMap);
        System.out.println();

        System.out.println("=== Через switch ===");
        printColors(Colors::getPositionBySwitch);
        System.out.println();

        System.out.println("=== Через enum ===");
        printColors(ColorPriority::getOrder);
        System.out.println();
    }

    private static void printColors(ToIntFunction<Character> orderFunction) {

        TreeSet<String> colors = new TreeSet<>((color1, color2) -> {
            char first1 = Character.toUpperCase(color1.charAt(0));
            char first2 = Character.toUpperCase(color2.charAt(0));

            int position1 = orderFunction.applyAsInt(first1);
            int position2 = orderFunction.applyAsInt(first2);

            if (position1 == position2) {
                return color1.compareToIgnoreCase(color2);
            }

            return Integer.compare(position1, position2);
        });

        addColors(colors);
        System.out.println(colors);
    }

    private static void addColors(TreeSet<String> colors) {
        colors.add("Серебряный");
        colors.add("Синий");
        colors.add("Красный");
        colors.add("Фиолетовый");
        colors.add("Оранжевый");
        colors.add("Белый");
        colors.add("Голубой");
        colors.add("Желтый");
        colors.add("Черный");
        colors.add("Зеленый");
    }


    private static final Map<Character, Integer> ORDER_MAP = Map.of(
            'К', 1,
            'О', 2,
            'Ж', 3,
            'З', 4,
            'Г', 5,
            'С', 6,
            'Ф', 7
    );

    private static int getPositionByMap(char ch) {
        return ORDER_MAP.getOrDefault(ch, Integer.MAX_VALUE);
    }


    private static int getPositionBySwitch(char ch) {
        return switch (ch) {
            case 'К' -> 1;
            case 'О' -> 2;
            case 'Ж' -> 3;
            case 'З' -> 4;
            case 'Г' -> 5;
            case 'С' -> 6;
            case 'Ф' -> 7;
            default -> Integer.MAX_VALUE;
        };
    }
}

enum ColorPriority {
    RED('К', 1),
    ORANGE('О', 2),
    YELLOW('Ж', 3),
    GREEN('З', 4),
    LIGHT_BLUE('Г', 5),
    BLUE('С', 6),
    PURPLE('Ф', 7);

    private final char letter;
    private final int order;

    ColorPriority(char letter, int order) {
        this.letter = letter;
        this.order = order;
    }

    public static int getOrder(char ch) {
        for (ColorPriority colorPriority : values()) {
            if (colorPriority.letter == ch) {
                return colorPriority.order;
            }
        }
        return Integer.MAX_VALUE;
    }
}
