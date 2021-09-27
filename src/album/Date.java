package album;

import java.util.Calendar;

/**
 * This class defines the Date abstract data type with year, month, and day
 * @author Jack Dunich
 * @author Kiana Perst
 */
public class Date implements Comparable<Date> {
    /**
     * year global variable
     */
    private final int year;
    /**
     * month global variable
     */
    private final int month;
    /**
     * day global variable
     */
    private final int day;

    /**
     * constant for the 80s
     */
    private static final int THE_EIGHTYS = 1980;
    /**
     * constant for quadrennial
     */
    private static final int QUADRENNIAL = 4;
    /**
     * constant for centennial
     */
    private static final int CENTENNIAL = 100;
    /**
     * constant for quatercentennial
     */
    private static final int QUATERCENTENNIAL = 400;

    /**
     * constant for January
     */
    private static final int JANUARY = 1;
    /**
     * constant for February
     */
    private static final int FEBRUARY = 2;
    /**
     * constant for March
     */
    private static final int MARCH = 3;
    /**
     * constant for April
     */
    private static final int APRIL = 4;
    /**
     * constant for May
     */
    private static final int MAY = 5;
    /**
     * constant for June
     */
    private static final int JUNE = 6;
    /**
     * constant for July
     */
    private static final int JULY = 7;
    /**
     * constant for August
     */
    private static final int AUGUST = 8;
    /**
     * constant for September
     */
    private static final int SEPTEMBER = 9;
    /**
     * constant for October
     */
    private static final int OCTOBER = 10;
    /**
     * constant for November
     */
    private static final int NOVEMBER = 11;
    /**
     * constant for December
     */
    private static final int DECEMBER = 12;

    /**
     * constant for 31 days
     */
    private static final int THIRTY_ONE_DAYS = 31;
    /**
     * constant for 30 days
     */
    private static final int THIRTY_DAYS = 30;
    /**
     * constant for 29 days
     */
    private static final int TWENTY_NINE_DAYS = 29;
    /**
     * constant for 28 days
     */
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
     * @param args argument
     */
    public static void main(String[] args) {

        boolean result;
        
        //tests date with invalid format
        System.out.println("Test case #1: ");
        Date date = new Date("2001/9/28");
        result = date.isValid();
        if (!result) {
            System.out.println("Date: " + date.dateToString() + ". Failed! Please input the date using the correct format: MM/DD/YYYY");
        }
        else {
            System.out.println("Date: " + date.dateToString() + ". Passed! You used the correct format: MM/DD/YYYY");
        }
        
        //tests date with invalid month
        System.out.println("Test case #2: ");
        Date date2 = new Date("14/15/2003");
        result = date2.isValid();
        if (!result) {
            System.out.println("Date: " + date2.dateToString() + ". Failed! " + date2.getMonth() + " is not a valid month.");
        }
        else {
            System.out.println("Date: "+ date2.dateToString() + ". Passed! You used the correct format: MM/DD/YYYY");
        }
        
        //tests date with a year before 1980
        System.out.println("Test case #3: ");
        Date date3 = new Date("3/20/1979");
        result = date3.isValid();
        if (!result) {
            System.out.println("Date: " + date3.dateToString() + ". Failed! " + date3.getYear() + " occurs before 1980. It is not a valid year.");
        }
        else {
            System.out.println("Date: "+ date3.dateToString() + ". Passed! You entered a valid year.");
        }
        
        //tests date with date with day past current date
        System.out.println("Test case #4: ");
        Date date4 = new Date("12/20/2021");
        result = date4.isValid();
        if (!result) {
            System.out.println("Date: " + date4.dateToString() + ". Failed! The date you entered is past the current date.");
        }
        else {
            System.out.println("Date: "+ date4.dateToString() + ". Passed! You entered a valid date.");
        }
        
        //tests date with invalid leap year
        System.out.println("Test case #5: ");
        Date date5 = new Date("2/29/2018");
        result = date5.isValid();
        if (!result) {
            System.out.println("Date: " + date5.dateToString() + ". Failed! The day you entered exceeded 28 and the year is not a leap year.");
        }
        else {
            System.out.println("Date: "+ date5.dateToString() + ". Passed! You entered a valid date.");
        }
        
        //tests date with valid leap year and valid day
        System.out.println("Test case #6: ");
        Date date6 = new Date("2/29/2004");
        result = date6.isValid();
        if (result) {
            System.out.println("Date: " + date6.dateToString() + ". Passed! The day you entered was 29 and the year is a valid leap year.");
        }
        else {
            System.out.println("Date: "+ date6.dateToString() + ". Failed! You entered an invalid date.");
        }
        
        //tests date with valid leap year and invalid day
        System.out.println("Test case #7: ");
        Date date7 = new Date("2/30/2008");
        result = date7.isValid();
        if (!result) {
            System.out.println("Date: " + date7.dateToString() + ". Failed! The month you entered cannot exceeded 29 days, even if its a valid leap year.");
        }
        else {
            System.out.println("Date: "+ date7.dateToString() + ". Passed! You entered a valid date.");
        }
        
        //tests date with correct 30 days
        System.out.println("Test case #8: ");
        Date date8 = new Date("4/30/1999");
        result = date8.isValid();
        if (result) {
            System.out.println("Date: " + date8.dateToString() + ". Passed! The day you entered was 30 and the month you entered has a max of 30 days.");
        }
        else {
            System.out.println("Date: "+ date8.dateToString() + ". Failed! The month you entered cannot have 30 days.");
        }
        
        //tests date with incorrect 31 days
        System.out.println("Test case #9: ");
        Date date9 = new Date("9/31/1984");
        result = date9.isValid();
        if (!result) {
            System.out.println("Date: " + date9.dateToString() + ". Failed! The month you entered cannot exceeded 30 days.");
        }
        else {
            System.out.println("Date: "+ date9.dateToString() + ". Passed! You entered a valid date.");
        }
        
        //tests date with correct 31 days
        System.out.println("Test case #10: ");
        Date date10 = new Date("1/31/2019");
        result = date10.isValid();
        if (result) {
            System.out.println("Date: " + date10.dateToString() + ". Passed! The day you entered was 31 and the month you entered has a max of 31 days.");
        }
        else {
            System.out.println("Date: "+ date10.dateToString() + ". Failed! The month you entered cannot have 31 days.");
        }
        
        //tests date with invalid days
        System.out.println("Test case #11: ");
        Date date11 = new Date("8/32/2011");
        result = date11.isValid();
        if (!result) {
            System.out.println("Date: " + date11.dateToString() + ". Failed! Months cannot exceeded 31 days.");
        }
        else {
            System.out.println("Date: "+ date11.dateToString() + ". Passed! You entered a valid date.");
        }
        
    }

}
