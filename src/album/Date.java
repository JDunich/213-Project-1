package album;

import java.util.Calendar;

/**
 * This class defines the Date abstract data type with year, month, and day
 * @author Kiana Perst, Jack Dunich
 */
public class Date implements Comparable<Date> {
    private final int year;
    private final int month;
    private final int day;

    private static final int THE_EIGHTYS = 1980;
    private static final int QUADRENNIAL = 4;
    private static final int CENTENNIAL = 100;
    private static final int QUATERCENTENNIAL = 400;

    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int MARCH = 3;
    private static final int APRIL = 4;
    private static final int MAY = 5;
    private static final int JUNE = 6;
    private static final int JULY = 7;
    private static final int AUGUST = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    private static final int NOVEMBER = 11;
    private static final int DECEMBER = 12;

    private static final int THIRTY_ONE_DAYS = 31;
    private static final int THIRTY_DAYS = 30;
    private static final int TWENTY_NINE_DAYS = 29;
    private static final int TWENTY_EIGHT_DAYS = 28;

    /**
     * Constructor that takes a string in the form of "mm/dd/yyyy" and creates a Date object
     * @param date date input
     */
    public Date(String date) {

        //identify where each "/" is
        int first_slash, second_slash;
        first_slash = date.indexOf('/');
        second_slash = date.indexOf('/', first_slash + 1);

        String str_month = date.substring(0, first_slash);
        String str_day = date.substring(first_slash + 1, second_slash);
        String str_year = date.substring(second_slash + 1);

        this.month = Integer.parseInt(str_month);
        this.day = Integer.parseInt(str_day);
        this.year = Integer.parseInt(str_year);

    }

    /**
     * Constructor that creates a Date object with today's date using the Calendar class
     */
    public Date() {

        Calendar calendar = Calendar.getInstance();

        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);

        this.month = month;
        this.day = day;
        this.year = year;

    }

    /**
     * This method determines if the date
     * @return true if the date is valid, false if not
     */
    public boolean isValid() {

        Date curr_date = new Date();
        int max_days;

        //check if the year is invalid (before 1980 or past the current year)
        if ((year < THE_EIGHTYS) || (year > curr_date.year)) {
            return false;
        }

        //check if the date is beyond the current date
        if (year == curr_date.year) {
            if (month > curr_date.month) {
                return false;
            } else if (month == curr_date.month) {
                if (day > curr_date.day) {
                    return false;
                }
            }
        }

        //using the month, determine the max amount of days that should be in that month
        switch (month) {
            case JANUARY:
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER:
                max_days = THIRTY_ONE_DAYS;
                break;
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                max_days = THIRTY_DAYS;
                break;
            case FEBRUARY:
                if ((year % CENTENNIAL == 0 && year % QUATERCENTENNIAL != 0) || (year % QUADRENNIAL == 0)) {
                    max_days = TWENTY_NINE_DAYS;
                    break;
                } else {
                    max_days = TWENTY_EIGHT_DAYS;
                    break;
                }
            default:
                return false;
        }

        return max_days >= day;

    }

    /**
     * This method compares the two Date objects.
     * Returns 0 if the parameter date is equal to the other date
     * Returns -1 if the parameter date is less than the other date
     * Returns 1 if the parameter date is greater than the other date
     * @param date input
     * @return Returns 0 if the parameter date is equal to the other date, Returns -1 if the parameter date is less than the other date, Returns 1 if the parameter date is greater than the other date
     */
    @Override
    public int compareTo(Date date) {

        //checks if the years are equal
        if (date.year == year) {

            //if the years are equal, checks if the months are equal
            if (date.month == month) {

                //if the months are equal, checks if the days are equal
                if (date.day == day) {
                    return 0;
                } else if (date.day > day) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (date.month > month) {
                return 1;
            } else {
                return -1;
            }
        } else if (date.year > year) {
            return 1;
        } else {
            return -1;
        }

    }

    /**
     * Accessor method for the day field
     * @return int day of the month
     */
    public int getDay() {
        return day;
    }

    /**
     * Accessor method for the month field
     * @return int month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Accessor method for the year field
     * @return int year
     */
    public int getYear() {
        return year;
    }

    /**
     * This helper method returns a Date object back into a string in the form:
     * "mm/dd/yyyy"
     * @return string
     */
    public String dateToString() {

        String separator = "/";
        String month = String.valueOf(this.month);
        String day = String.valueOf(this.day);
        String year = String.valueOf(this.year);
        String result = month + separator + day + separator + year;
        return result;
    }

    /**
     * Testbed main for the Date class
     */
    public static void main(String[] args) {

        //tests date with invalid format
        //tests date with invalid leap year
        //tests date with valid leap year
        //tests date with a year before 1980
        //tests date with year 1980
        //tests date with date with day past current date
        //tests date with correct 31 days
        //tests date with incorrect 31 days
        //tests date with correct 30 days
        //tests date with incorrect 30 days
        //tests date with incorrect 28 days
        //tests date with correct 28 days
        Date date = new Date("3/4/1979");
        boolean expectedResult = false;
        boolean actualResult = date.isValid();
        System.out.print("Test case 1: ");
        if (actualResult == expectedResult) {
            System.out.print("Correct");
        } else {
            System.out.print("Fail");
        }

        //test case 2 tests a date with an invalid month
        date = new Date("0/12/2000");
        expectedResult = false;
        actualResult = date.isValid();
        System.out.print("Test case 2: ");
        if (actualResult == expectedResult) {
            System.out.print("Correct");
        } else {
            System.out.print("Fail");
        }

        //test case 3 tests a date with an invalid day
        date = new Date("10/32/2000");
        expectedResult = false;
        actualResult = date.isValid();
        System.out.print("Test case 2: ");
        if (actualResult == expectedResult) {
            System.out.print("Correct");
        } else {
            System.out.print("Fail");
        }

        System.out.println("Running test case #1");
        Date date2 = new Date("31/4/2000");
        Boolean Result = date2.isValid();
        if (!Result) {
            System.out.println("Test case #1, checking the correct format. Passed! The correct format is MM/DD/YYYY");
        } else {
            System.out.println("Test case #1, checking for the correct format. Failed!");
        }
        System.out.println("Running test case #2");
        Date date3 = new Date("2/29/2000");
        Boolean Result2 = date3.isValid();
        if (Result2) {
            System.out.println("Test case #2, checking for the correct leap year. Passed! 2000 is a leap year");
        } else {
            System.out.println("Test case #2, checking for the correct format. Failed!");
        }
        System.out.println("Running test case #3");
        Date date4 = new Date("3/2/1979");
        Boolean Result3 = date4.isValid();
        if (!Result3) {
            System.out.println("Test case #3, checking for the correct year before 1980. Passed! 1979 is before 1980");
        } else {
            System.out.println("Test case #3, checking for the correct year before 1980. Failed!");
        }
        System.out.println("Running test case #4");
        Date date5 = new Date("9/2/1981");
        Boolean Result4 = date5.isValid();
        if (Result4) {
            System.out.println("Test case #4, checking for the correct year after 1980. Passed!");
        } else {
            System.out.println("Test case #4, checking for the correct year after 1980. Failed!");
        }
        System.out.println("Running test case #5");
        Date date6 = new Date("8/3/2021");
        Boolean Result5 = date6.isValid();
        if (!Result5) {
            System.out.println("Test case #5, checking for the date after today. Passed!");
        } else {
            System.out.println("Test case #5, checking for the date after today. Failed!");
        }
        System.out.println("Running test case #6");
        Date date7 = new Date("9/28/2015");
        Boolean Result6 = date7.isValid();
        if (Result6) {
            System.out.println("Test case #6, checking for just a normal date. Passed!");
        } else {
            System.out.println("Test case #6, checking for just a normal date. Failed!");
        }
        System.out.println("Running test case #7");
        Date date8 = new Date("2/29/2016");
        Boolean Result7 = date8.isValid();
        if (Result7) {
            System.out.println("Test case #7, checking for the correct leap year. Passed! 2016 is a leap year");
        } else {
            System.out.println("Test case #7, checking for the correct leap year. Failed!");
        }
        System.out.println("Running test case #8");
        Date date9 = new Date("2/29/2001");
        Boolean Result8 = date9.isValid();
        if (!Result8) {
            System.out.println("Test case #8, checking for the correct leap year. Passed! 2001 is not a leap year");
        } else {
            System.out.println("Test case #8, checking for the correct leap year. Failed!");
        }
        System.out.println("Running test case #9");
        Date date10 = new Date("4/31/2012");
        Boolean Result9 = date10.isValid();
        if (!Result9) {
            System.out.println("Test case #9, checking for the correct number of days in April. Passed! There are only 30 days");
        } else {
            System.out.println("Test case #9, checking for the correct number of days in April. Failed!");
        }
        System.out.println("Running test case #10");
        Date date11 = new Date("2/29/1984");
        Boolean Result10 = date11.isValid();
        if (!Result10) {
            System.out.println("Test case #10, checking for the correct leap year. Passed! 1984 is a leap year");
        } else {
            System.out.println("Test case #10, checking for the correct leap year. Failed!");
        }

    }

}
