import mypersistence.Project;
import mypersistence.Task;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");


    }

//    1. Какие проекты в работе (по которым есть хотя бы одна незавершенная задача).
    public ArrayList<Project> getProjectsInWork(){
        ArrayList<Project> projectList = new ArrayList<Project>();
        return null;
    }

//    2. Сколько по данному проекту незавершенных задач.
    public int getNumOfActiveTasks(){
       return 0;
    }

//    3. Какие незавершенные задачи есть у некоторого ответственного, имя которого, я хочу ввести в программу.
    public ArrayList<Task> getResponsiblesActiveTasks(String responsiblesName){
        ArrayList<Task> projectList = new ArrayList<Task>();
        return null;
    }

//    4. Какие задачи есть на сегодня и кто за них отвечает.
    public ResultSet getAllActiveTasksAndResponsibles(){
        return null;
    }

//    5. Имена и контакты тех, кто просрочил свои задачи (текущая дата больше, чем дата старта задачи + ее длительность)
    public ResultSet getExpiredResponsibles(){
        return null;
    }





















}




//
//
//    statement.executeUpdate("DROP TABLE IF EXISTS `projects`");
//    statement.executeUpdate("CREATE TABLE IF NOT EXISTS `projects` ( `id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, `name`	TEXT);";
//
//    statement.executeUpdate("DROP TABLE IF EXISTS `tasks`");
//    statement.executeUpdate("CREATE TABLE IF NOT EXISTS `tasks` (`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, `name`	TEXT, `project_id`	INTEGER, `start_date`	TEXT, `lifetime`	TEXT, `targetinfo`	TEXT);"
//
//    statement.executeUpdate("DROP TABLE IF EXISTS `responsibleworkers`");
//    statement.executeUpdate("CREATE TABLE IF NOT EXISTS `responsibleworkers` ( `id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, `name`	TEXT, `contact_info`	TEXT );"
//
//



/*

К вам приходит заказчик и говорит, что у него есть Система управления проектами, которую он ведет в экселе.В одной таблице.

И просит написать программу на Java, которая бы более надежная,
автоматизировала этот процесс, храня данные в локальной базе данных SQLite,
и позволяла быстро узнать некоторые обобщенные данные.

Заказчик хотел бы получать быстрые ответы на вопросы:

1. Какие проекты в работе (по которым есть хотя бы одна незавершенная задача).
2. Сколько по данному проекту незавершенных задач.
3. Какие незавершенные задачи есть у некоторого ответственного, имя которого, я хочу ввести в программу.
4. Какие задачи есть на сегодня и кто за них отвечает.
5. Имена и контакты тех, кто просрочил свои задачи (текущая дата больше, чем дата старта задачи + ее длительность)

Под надежностью проекта Заказчик подразумевает наличие юнит-тестов как минимум те на критические вопросы, которые он перечислил выше.

Ограничения:

1. В простом варианте достаточно будет консольной версии проекта. Если будет время и возможности: добавьте графический интерфейс пользователя.
2. Интерфейс по добавлению данных по Проектам и Ответственным писать не нужно.
Предполагается, что список Ответственных меняться не будет (текучка нулевая) и список Проектов тоже не меняется.
Но в базе данных должен быть некоторый предварительный набор Проектов и Ответственных.


 */





