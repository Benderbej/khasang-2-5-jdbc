package mypersistence;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void checkIfExpiredTest() throws Exception {
        Calendar c = Calendar.getInstance();
        c.set(2005, 4, 5);//текущая дата подменяется подставной
        Date today = c.getTime();
        c.set(2005, 4, 6);
        Date startDay = c.getTime();
        Task task = new Task();
        boolean b = task.checkIfExpired(today, startDay, 86500l);//86500 sometime more than 24h
        Assert.assertTrue(!b);
    }

    @Test
    public void getId() throws Exception {
    }

    @Test
    public void getName() throws Exception {
    }

    @Test
    public void getStartTimeStamp() throws Exception {
    }

    @Test
    public void getStartTime() throws Exception {
    }

    @Test
    public void getLifeTimeLong() throws Exception {
    }
}