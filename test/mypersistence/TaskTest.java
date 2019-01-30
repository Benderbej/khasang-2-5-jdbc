package mypersistence;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;


class TaskTest {
    @Test
    void checkIfExpiredTest() {
        //текущая дата подменяется подставной
        //java.util.Date today = new java.util.Date();
        java.util.Date today = new java.util.Date("2005-04-06 09:01:10");
        Task task = new Task();
        boolean b = task.checkIfExpired(today, "2005-04-05 09:01:10", 86500l);//86500 sometime more than 24h
        Assert.assertTrue(!b);
    }
}