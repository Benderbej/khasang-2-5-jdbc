package mypersistence;

import java.sql.Timestamp;
import java.util.Date;


public class Task {

    private int id;
    private String name;
    private Timestamp startTimeStamp;
    private long startTime;
    private long lifeTimeLong;//long





    public boolean checkIfExpired(Date today, String startTimestampString, long time){
        //java.util.Date today = new java.util.Date();
        Timestamp todayTime = new Timestamp(today.getTime());
        long todayTimeSeconds = todayTime.getTime();
        Timestamp startTime = Timestamp.valueOf(startTimestampString);
        long startTimeSeconds = startTime.getTime();
        long expireTimeSeconds = startTimeSeconds + time;
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