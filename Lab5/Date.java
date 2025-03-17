package Labs.Lab5;

public class Date {

    private int day;   // День
    private int month; // Месяц
    private int year;  // Год

    // Параметризованный конструктор
    public Date(int day, int month, int year) {
        setDay(day);
        setMonth(month);
        setYear(year);
    }

    // Конструктор по умолчанию
    public Date() {
        this(1, 1, 2000); // Инициализация значениями по умолчанию
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Неверный день. Должен быть от 1 до 31.");
        }
        this.day = day;
    }

    public void setMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Неверный месяц. Должен быть от 1 до 12.");
        }
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year; // Год может быть любым положительным значением
    }

    // Метод для проверки, является ли год високосным
    public boolean isLeapYear() {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // Метод для увеличения даты на заданное количество дней
    public void addDays(int days) {
        this.day += days;
        while (this.day > daysInMonth(month, year)) {
            this.day -= daysInMonth(month, year);
            month++;
            if (month > 12) {
                month = 1;
                year++;
            }
        }
    }

    // Метод для получения количества дней в месяце
    private int daysInMonth(int month, int year) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return isLeapYear() ? 29 : 28;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%d", day, month, year);
    }
}
