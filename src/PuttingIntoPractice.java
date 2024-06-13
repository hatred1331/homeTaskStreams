

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

//        1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей
//        к большей).

        List<Transaction> task1 = transactions.stream()
                .filter(x -> x.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction :: getYear))
                .toList();
        System.out.println("Транзакции за 2011 год, отсортированные по сумме от меньшей к большей");
        System.out.println(task1);

//        2. Вывести список неповторяющихся городов, в которых работают трейдеры.

        List<String> task2 = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .toList();

        System.out.println("Список городов, в которых работают трейдеры");
        System.out.println(task2);


//        3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.

        List<Trader> task3 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .toList();

        System.out.println("Трейдеры из Кембриджа, отсортированные по именам");
        System.out.println(task3);
//        4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном
//        порядке.

        String task4 = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .map(Trader::getName)
                .collect(Collectors.joining(", "));

        System.out.println("Строка с именами трейдеров");
        System.out.println(task4);


//        5. Выяснить, существует ли хоть один трейдер из Милана.
        boolean task5 = transactions.stream()
                .map(Transaction::getTrader)
                        .anyMatch(x -> x.getCity().equals("Milan"));

        System.out.println("Выяснение, есть ли трейдер из Милана");
        System.out.println(task5); // Если трейдер из Милана существует - выведется true, иначе - false


//        6. Вывести суммы всех транзакций трейдеров из Кембриджа.

        int task6 = transactions.stream()
                .filter(x-> x.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(0, Integer :: sum);

        System.out.println("Суммы всех транзакций трейдеров из Кембриджа");
        System.out.println(task6);


//        7. Какова максимальная сумма среди всех транзакций?

        int task7 = transactions.stream()
                .map(Transaction :: getValue)
                .max(Integer :: compare)
                .get();

        System.out.println("Максимальная сумма среди всех транзакций");
        System.out.println(task7);

//        8. Найти транзакцию с минимальной суммой.

        Transaction task8 = transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue))
                .orElse(null);
        System.out.println("Сумма минимальной транзакции");
        System.out.println(task8);


    }
}


