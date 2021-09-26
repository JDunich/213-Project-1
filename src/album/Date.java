package album;
import java.util.Calendar;

/**
 * This class defines the Date abstract data type with year, month, and day
 * @author Kiana Perst, Jack Dunich
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    
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
     * @param date
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
        int max_days = 0;
        
        //check if the year is invalid (before 1980 or past the current year)
        if((year < THE_EIGHTYS) || (year > curr_date.year)) {
            return false;  
        }
        
        //check if the date is beyond the current date
        if(year == curr_date.year) {
            if(month > curr_date.month) {
                return false;
            }
            else if(month == curr_date.month) {
                if(day > curr_date.day) {
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
                if((year % QUADRENNIAL) != 0) {
                    if ((year % CENTENNIAL) != 0) {
                        if((year % QUATERCENTENNIAL) != 0) {
                            max_days = TWENTY_NINE_DAYS;
                            break;
                        }
                    }
                }
                max_days = TWENTY_EIGHT_DAYS;
            default:
                return false;
        }
        
        if (max_days <= day) {
            return false;
        }
        else {
            return true;
        }
        
    }
    
    /**
     * This method compares the two Date objects.
     * Returns 0 if the parameter date is equal to the other date
     * Returns -1 if the parameter date is less than the other date
     * Returns 1 if the parameter date is greater than the other date
     * @param date
     */
    @Override
    public int compareTo(Date date) {
        
        //checks if the years are equal
        if(date.year == year) {
            
            //if the years are equal, checks if the months are equal
            if(date.month == month) {
                
                //if the months are equal, checks if the days are equal
                if(date.day == day) {
                    return 0;
                }
                else if(date.day > day) {
                    return 1;
                }
                else {
                    return -1;
                }
            }
            else if(date.month > month) {
                return 1;
            }
            else {
                return -1;
            }
        }
        else if (date.year > year) {
            return 1;
        }
        else {
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
    public String dateToString(){
        
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
        
        //tests date with a year before 1980
        Date date = new Date("3/4/1979");
        boolean expectedResult = false;
        boolean actualResult = date.isValid();
        System.out.print("Test case 1: ");
        if(actualResult == expectedResult) {
            System.out.print("Correct");  
        }
        else {
            System.out.print("Fail");
        }
        
        //test case 2 tests a date with an invalid month
        date = new Date("0/12/2000");
        expectedResult = false;
        actualResult = date.isValid();
        System.out.print("Test case 2: ");
        if(actualResult == expectedResult) {
            System.out.print("Correct");  
        }
        else {
            System.out.print("Fail");
        }
        
      //test case 3 tests a date with an invalid day
        date = new Date("0/12/2000");
        expectedResult = false;
        actualResult = date.isValid();
        System.out.print("Test case 2: ");
        if(actualResult == expectedResult) {
            System.out.print("Correct");  
        }
        else {
            System.out.print("Fail");
        }
        
    }

}
