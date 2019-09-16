package wrapper;

import parser.TimeParser;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Date wrapper class for having a date object and string date
 */
public class MyDate {

    private boolean hasEndDate = false;
    private Date startdate = null;
    private Date enddate = null;
    private String startdateStr;
    private String enddateStr;

    /**
     * Constructor for class
     * @throws ParseException
     */
    public MyDate(String s) {


        this.startdateStr = s;
        this.enddateStr = s;

        this.startdate = TimeParser.convertStringToDate(s);
        this.enddate = TimeParser.convertStringToDate(s);


        if(this.startdate != null){
            this.startdateStr = TimeParser.convertDateToLine(this.startdate);
        }
        if(this.enddate != null){
            this.enddateStr = TimeParser.convertDateToLine(this.enddate);
        }

    }

    public MyDate(String startDate ,  String endDate) {


        hasEndDate = true;

        this.startdateStr = startDate;
        this.enddateStr = endDate;

        this.startdate = TimeParser.convertStringToDate(startDate);
        this.enddate = TimeParser.convertStringToDate(endDate);


        if(this.startdate != null){
            this.startdateStr = TimeParser.convertDateToLine(this.startdate);
        }

        if(this.enddate != null){
            this.enddateStr = TimeParser.convertDateToLine(this.enddate);
        }

    }

    /**
     * Returns the start date object
     * @return Date object of the Task
     */
    public Date getStartDate(){
        return this.startdate;

    }

    /**
     * Returns the end date object
     * @return Date object of the Task
     */
    public Date getEndDate(){
        return this.enddate;

    }

    /**
     * Returns the start date string
     * @return Date string of the Task
     */
    public String getStartDateStr(){
        return this.startdateStr;

    }

    /**
     * Returns the end date string
     * @return Date string of the Task
     */
    public String getEndDateStr(){
        return this.enddateStr;

    }

    @Override
    public String toString() {
        String returnStr = hasEndDate ? startdateStr + " to " + enddateStr : startdateStr;
        return returnStr;
    }
}