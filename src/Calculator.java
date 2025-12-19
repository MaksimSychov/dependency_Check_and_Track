import java.util.Scanner;

class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--------------Калькулятор---------------");
        System.out.println("Доступные операции: +, -, *, /");
        System.out.println("Для сброса введите 'C', для выхода - 'S'");
        System.out.println("----------------------------------------");

        double result = 0;
        boolean isRunning = true;
        boolean isNewCalculation = true;

        while (isRunning) {
            try {
                double operand1;
                char operation = ' ';
                double operand2;

                if (isNewCalculation) {
                    // Считывается первый операнд
                    while (true) {
                        System.out.print("Введите первое число: ");
                        String input = scanner.nextLine().trim();

                        // Проверка на пустую строку
                        if (input.isEmpty()) {
                            System.out.println("Ошибка: Вы не ввели число!");
                            continue;
                        }

                        try {
                            operand1 = Double.parseDouble(input);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка: введите корректное число!");
                        }
                    }
                } else {
                    // Продолжение вычислений (используется предыдущий результат)
                    operand1 = result;
                    System.out.println("Текущий результат: " + result);
                }

                // Считывается операция
                while (true) {
                    System.out.print("Введите операцию (+, -, *, /, C, S): ");
                    String input = scanner.nextLine().trim().toUpperCase();

                    // Проверка на пустую строку
                    if (input.isEmpty()) {
                        System.out.println("Ошибка: Вы ничего не ввели!");
                        continue;
                    }

                    // Проверка на введение 1 символа и на "+, -, *, /, C, S"
                    if (input.length() == 1 && "+-*/CS".contains(input)) {
                        operation = input.charAt(0);
                        break;
                    } else {
                        System.out.println("Ошибка: неподдерживаемая операция '" + input + "'!");
                    }
                }

                // Обработка команд C и S
                if (operation == 'C') {
                    result = 0;
                    isNewCalculation = true;
                    System.out.println("Результат сброшен. Текущее значение: " + result);
                    continue;
                }

                if (operation == 'S') {
                    System.out.println("Завершение работы калькулятора.");
                    isRunning = false;
                    continue;
                }

                // Считывается второй операнд
                while (true) {
                    System.out.print("Введите второе число: ");
                    String input = scanner.nextLine().trim();

                    // Проверка на пустую строку
                    if (input.isEmpty()) {
                        System.out.println("Ошибка: Вы не ввели число!");
                        continue;
                    }

                    try {
                        operand2 = Double.parseDouble(input);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: введите корректное число!");
                    }
                }

                // Выполняется операция
                switch (operation) {
                    case '+':
                        result = operand1 + operand2;
                        break;
                    case '-':
                        result = operand1 - operand2;
                        break;
                    case '*':
                        result = operand1 * operand2;
                        break;
                    case '/':
                        // Проверка на деление на 0
                        if (operand2 == 0) {
                            System.out.println("Ошибка: деление на ноль!");
                            continue;
                        }
                        result = operand1 / operand2;
                        break;
                }

                // Вывод результата
                System.out.println("Результат: " + operand1 + " " + operation + " " + operand2 + " = " + result);
                System.out.println("----------------------------------------");

                // После успешной операции переходим в режим продолжения вычислений
                isNewCalculation = false;

            } catch (Exception e) {
                System.out.println("Ошибка ввода! Пожалуйста, введите корректные данные.");
            }
        }

        scanner.close();
    }
}