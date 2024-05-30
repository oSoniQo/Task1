
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        scanner.close();
        String[] arr = str.split(" ");
        if (checkArray(arr) == -1) {
            System.out.println("Введенная строка содержит недостаточное количество данных");
        }
        else if (checkArray(arr) == -2) {
            System.out.println("Введенная строка содержит избыточное количество данных");
        }
        else {
            String lastName = arr[0];
            String firstName = arr[1];
            String surName = arr[2];
            LocalDate birthDate;
            int phoneNumber;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                birthDate = LocalDate.parse(arr[3], formatter);
                phoneNumber = Integer.parseInt(arr[4]);
            }
            catch (DateTimeParseException e) {
                System.out.println("Неверный формат даты рождения");
                return;
            }
            catch (NumberFormatException e) {
                System.out.println("Неверный формат номера телефона");
                return;
            }           
            String sex = arr[5];
            try {
                if (!"m".equals(sex) && !"f".equals(sex)) {
                    throw new Exception();
                }
            }
            catch (Exception e) {
                System.out.println("Неверный формат пола");
            }

            String fileName = lastName + ".txt";
            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName, true))) {
                String fileData = String.format("<%s><%s><%s><%s><%s><%s>", lastName, firstName, surName, birthDate, phoneNumber, sex);
                fileWriter.write(fileData);
                fileWriter.newLine();
            }
            catch (IOException e) {
                System.out.println("Ошибка при записи данных");

            }
        }

    }
    public static int checkArray(String[] arr) {
        if (arr.length < 6) {
            return -1;
        }
        else if (arr.length > 6) {
            return -2;
        }
        else {
            return 1;
        }
    }

}