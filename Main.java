import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Читаем количество занятых мест
        int N = scanner.nextInt();

        // Используем Map для хранения занятых мест по рядам
        Map<Integer, Set<Integer>> rowSeats = new HashMap<>();

        // Читаем пары (ряд, место) и заполняем Map
        for (int i = 0; i < N; i++) {
            System.out.println("ряд");
            int row = scanner.nextInt();
            System.out.println("место");// номер ряда
            int seat = scanner.nextInt(); // номер места
            // Добавляем место в множество занятых мест данного ряда
            rowSeats.putIfAbsent(row, new HashSet<>());
            rowSeats.get(row).add(seat);
        }

        // Переменные для хранения результатов
        int maxRow = Integer.MIN_VALUE; // максимальный номер ряда
        int minSeat = Integer.MAX_VALUE; // минимальный номер места среди найденных пар

        // Проходим по каждому ряду
        for (Map.Entry<Integer, Set<Integer>> entry : rowSeats.entrySet()) {
            int row = entry.getKey(); // текущий ряд
            Set<Integer> seats = entry.getValue(); // занятые места в ряду
            List<Integer> sortedSeats = new ArrayList<>(seats); // преобразуем в список
            Collections.sort(sortedSeats); // сортируем список

            // Ищем пары соседних мест
            for (int j = 0; j < sortedSeats.size() - 1; j++) {
                int seat1 = sortedSeats.get(j);
                int seat2 = sortedSeats.get(j + 1);

                // Проверяем, являются ли места соседними
                if (seat2 == seat1 + 1) {
                    // Проверяем занятые места слева и справа
                    if (seats.contains(seat1 - 1) && seats.contains(seat2 + 1)) {
                        // Обновляем результаты, если нашли более высокий ряд
                        // или в том же ряду меньшее место
                        if (row > maxRow || (row == maxRow && seat1 < minSeat)) {
                            maxRow = row; // обновляем максимальный ряд
                            minSeat = seat1; // обновляем минимальное место
                        }
                    }
                }
            }
        }

        // Выводим результат
        System.out.println(maxRow + " " + minSeat);
    }
}
