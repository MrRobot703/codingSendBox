package taskFive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Library {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        Library library = new Library();
        int[] params = library.parse(library.getUserInput());
        int daysOfHappiness = library.happiness(params[0], params[1], params[2]);
        System.out.println(daysOfHappiness);
    }

    public int happiness(int k, int m, int d) {
        int dayOfWeek = d - 1;
        int freshBooks = m;
        int booksToRead;
        int day = 1;
        while (freshBooks >= 0) {
            booksToRead = day;
            System.out.println(day + ": " + "m=" + freshBooks + ", dayOfTheWeek=" + dayOfWeek);
            freshBooks += k*contribution(dayOfWeek) - booksToRead;
            dayOfWeek = (dayOfWeek + 1)%7;
            day++;
        }
        return day - 2;
    }

    private int contribution(int dayOfWeek) {
        return dayOfWeek == 5 || dayOfWeek == 6 ? 0 : 1;
    }

    public int[] parse(String input) {
        return Arrays.stream(input.split("\s"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public String getUserInput() {
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
}
