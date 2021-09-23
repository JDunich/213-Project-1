package album;
import java.util.Calendar;

/**
 * This class defines the Date abstract data type with year, month, and day
 * @author Kiana Perst
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    
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
        
        final int THE_EIGHTYS = 1980;
        final int QUADRENNIAL = 4;
        final int CENTENNIAL = 100;
        final int QUATERCENTENNIAL = 400;
        
        Date curr_date = new Date();
        int max_days = 0;
        
        //check if the year is invalid
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
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                max_days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                max_days = 30;
                break;
            case 2:
                if((year % QUADRENNIAL) != 0) {
                    if ((year % CENTENNIAL) != 0) {
                        if((year % QUATERCENTENNIAL) != 0) {
                            max_days = 29;
                            break;
                        }
                    }
                }
                max_days = 28;
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
    
    //Returns the day of the Date object
    public int getDay() { 
        return day; 
    }
    
    //Returns the month of the Date object
    public int getMonth() {
        return month;
    }
    
    //Returns the year of the Date object
    public int getYear() {
        return year;
    }
    
    /**
     * This method returns a Date object back into a string
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
    
    // Testbed main for the Date class
    public static void main(String[] args) {
        
        //tests date creation
        Date date = new Date("11/31/1980");
        Date date2 = new Date();
        Date date3 = new Date("9/23/2021");
        Date date4 = new Date("9/23/1981");
        System.out.println(date.isValid());
        System.out.println(date2.isValid());
        System.out.println(date3.isValid());
        System.out.println(date4.isValid());
        
        
        
        System.out.println(date.getDay());
        System.out.print(date.getMonth());
        System.out.print(date.getYear());
        
    }

}
