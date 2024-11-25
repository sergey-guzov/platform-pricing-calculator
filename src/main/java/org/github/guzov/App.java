package org.github.guzov;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String input = "asia-east1";
        String formatted = input.toLowerCase() // Привести к нижнему регистру
                .replaceAll("[^a-z0-9\\s-]", "") // Удалить все кроме букв, цифр и пробелов
                .replaceAll("\\s+", "-"); // Заменить пробелы на дефисы
        System.out.println(formatted);

    }
}
