package mypersistence;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;


public class Task {

    private int id;
    private String name;
    private Timestamp startTimeStamp;
    private long startTime;
    private long lifeTimeLong;//long

    public static void main(String[] args) {
        java.util.Date today = new java.util.Date(2005, 4, 6);

        Calendar c = Calendar.getInstance();
        c.set(2005, 4, 6);
        today = c.getTime();


        Task task = new Task();
        boolean b = task.checkIfExpired(today, "2005-04-05 09:01:10", 86500l);//86500 sometime more than 24h
    }

    public boolean checkIfExpired(Date today, String startTimestampString, long time){
        //java.util.Date today = new java.util.Date();
        Timestamp todayTime = new Timestamp(today.getTime());
        long todayTimeSeconds = todayTime.getTime();
        System.out.println("todayTimeSeconds"+todayTimeSeconds);

        Timestamp startTime = Timestamp.valueOf(startTimestampString);
        long startTimeSeconds = startTime.getTime();

        System.out.println("startTimeSeconds"+startTimeSeconds);
        long expireTimeSeconds = startTimeSeconds + time;

        System.out.println("expireTimeSeconds"+expireTimeSeconds);
        if(todayTimeSeconds > expireTimeSeconds){
            return true;
        }
        return false;
    }

    public boolean checkIfExpired(Date today, Date startDay, long time ){
        Timestamp todayTime = new Timestamp(today.getTime());
        long todayTimeSeconds = todayTime.getTime();
        System.out.println("todayTimeSeconds"+todayTimeSeconds);

        Timestamp startTime = new Timestamp(startDay.getTime());
        long startTimeSeconds = startTime.getTime();
        System.out.println("startTimeSeconds"+startTimeSeconds);

        long expireTimeSeconds = startTimeSeconds + time;
        System.out.println("expireTimeSeconds"+expireTimeSeconds);

        if(todayTimeSeconds > expireTimeSeconds){
            return true;
        }
        return false;
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