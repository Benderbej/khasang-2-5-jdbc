import mypersistence.Project;
import mypersistence.Responsible;
import mypersistence.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Main main = new Main();
        main.getProjectsInWork();
        main.getNumOfActiveTasks();
        main.getResponsiblesActiveTasks("Остап");
        main.getAllActiveTasksAndResponsibles();
        main.getExpiredResponsibles();


        Calendar c = Calendar.getInstance();
        //c.set(2017, 11, 6);//текущая дата подменяется подставной
        Date today = c.getTime();
        Timestamp todayTime = new Timestamp(today.getTime());
        long todayTimeSeconds = todayTime.getTime();

        Task task = new Task();
        System.out.println(today);
        System.out.println(todayTimeSeconds);
        System.out.println(task.dateToString(today));



    }

//    1. Какие проекты в работе (по которым есть хотя бы одна незавершенная задача).
    public ArrayList<Project> getProjectsInWork() {
        ArrayList<Project> projectList = new ArrayList<Project>();
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
            System.out.println("JDBC driver have not found");
        }
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/JAVA/khasang/projectmanagmentsystem.db")) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM projects JOIN tasks ON projects.id = tasks.project_id WHERE tasks.isfinished = ?");
            int i = 0;
            statement.setInt(1, i);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                projectList.add(new Project(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2)));
                String string1 = resultSet.getString(1);
                String string2 = resultSet.getString(2);
                System.out.println(string1 + " " + string2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(projectList.get(0).getName());
        return projectList;
    }

//    2. Сколько по данному проекту незавершенных задач.
    public int getNumOfActiveTasks() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
            System.out.println("JDBC driver have not found");
        }
        int num = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/JAVA/khasang/projectmanagmentsystem.db")) {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM tasks WHERE tasks.isfinished = 0");
            ResultSet resultSet = statement.executeQuery();
            num = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

//    3. Какие незавершенные задачи есть у некоторого ответственного, имя которого, я хочу ввести в программу.
    public ArrayList<Task> getResponsiblesActiveTasks(String responsiblesName) {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
            System.out.println("JDBC driver have not found");
        }
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/JAVA/khasang/projectmanagmentsystem.db")) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tasks JOIN responsibleworkers ON tasks.responsible_id = responsibleworkers.id WHERE responsibleworkers.name = ? AND tasks.isfinished = 0");
            String name = "Остап";
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                taskList.add(new Task(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2)));
                int string1 = resultSet.getInt(1);
                String string2 = resultSet.getString(2);
                System.out.println(string1 + " " + string2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }

//    4. Какие задачи есть на сегодня и кто за них отвечает.
    public ArrayList<Task> getAllActiveTasksAndResponsibles() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
            System.out.println("JDBC driver have not found");
        }
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/JAVA/khasang/projectmanagmentsystem.db")) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tasks JOIN responsibleworkers ON tasks.responsible_id = responsibleworkers.id WHERE tasks.isfinished = 0");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                taskList.add(new Task(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(10)));
                int string1 = resultSet.getInt(1);
                String string2 = resultSet.getString(2);
                String string3 = resultSet.getString(10);
                System.out.println(string1 + " " + string2 + " "+string3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }

//    5. Имена и контакты тех, кто просрочил свои задачи (текущая дата больше, чем дата старта задачи + ее длительность)
    public ArrayList<Responsible> getExpiredResponsibles() {
        ArrayList<Responsible> responsiblesList = new ArrayList<Responsible>();
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
            System.out.println("JDBC driver have not found");
        }
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/JAVA/khasang/projectmanagmentsystem.db")) {
            PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT responsibleworkers.name, responsibleworkers.contact_info FROM tasks JOIN responsibleworkers ON tasks.responsible_id = responsibleworkers.id " +
                    "WHERE (tasks.start_date + tasks.lifetime) < (strftime('%s','now')*1000);");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                responsiblesList.add(new Responsible(resultSet.getString(1), resultSet.getString(2)));
                String string1 = resultSet.getString(1);
                String string3 = resultSet.getString(2);
                System.out.println(string1+" "+string3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return responsiblesList;
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





