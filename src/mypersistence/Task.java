package mypersistence;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Task {

    private int id;
    private String name;
    private Timestamp startTimeStamp;
    private long startTime;
    private long lifeTimeLong;//long
    public static final DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.ENGLISH);

    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        c.set(2005, 4, 5);//текущая дата подменяется подставной
        Date today = c.getTime();
        System.out.println(today);
        c.set(2005, 4, 6);
        Date startDay = c.getTime();
        System.out.println(startDay);
        Task task = new Task();
        boolean b = task.checkIfExpired(today, startDay, 86500l);//86500 sometime more than 24h
    }

    public boolean checkIfExpired(Date today, Date startDay, long time ){
        Timestamp todayTime = new Timestamp(today.getTime());
        long todayTimeSeconds = todayTime.getTime();
        Timestamp startTime = new Timestamp(startDay.getTime());
        long startTimeSeconds = startTime.getTime();
        long expireTimeSeconds = startTimeSeconds + time;
        if(todayTimeSeconds > expireTimeSeconds){
            return true;
        }
        return false;
    }

    public Date stringToDate(String s){
        Date date = null;
        try {
            date = format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("date format is not valid, and cant be parsed");
        }
        return date;
    }

    public String dateToString(Date d){
        String reportDate = format.format(d);
        return reportDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Timestamp getStartTimeStamp() {
        return startTimeStamp;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getLifeTimeLong() {
        return lifeTimeLong;
    }
}