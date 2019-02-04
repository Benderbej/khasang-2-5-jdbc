import mypersistence.Project;
import mypersistence.Responsible;
import mypersistence.Task;

import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.getProjectsInWork();
        main.getNumOfActiveTasks();
        main.getResponsiblesActiveTasks("Остап");
        main.getAllActiveTasksAndResponsibles();
        main.getExpiredResponsibles();
    }

    public ArrayList<Project> getProjectsInWork() {
        System.out.println("проекты в работе (по которым есть хотя бы одна незавершенная задача:");
        ArrayList<Project> projectList = new ArrayList<Project>();
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
            System.out.println("JDBC driver have not found");
        }
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:projectmanagmentsystem.db")) {
            PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT projects.id, projects.name FROM projects JOIN tasks ON projects.id = tasks.project_id WHERE tasks.isfinished = ?");
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
        return projectList;
    }

    public int getNumOfActiveTasks() {
        System.out.println("Задач в работе:");
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
            System.out.println("JDBC driver have not found");
        }
        int num = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:projectmanagmentsystem.db")) {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM tasks WHERE tasks.isfinished = 0");
            ResultSet resultSet = statement.executeQuery();
            num = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(num);
        return num;
    }

    public ArrayList<Task> getResponsiblesActiveTasks(String responsiblesName) {
        System.out.println("Получить задачи отсветственного Остап:");
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
            System.out.println("JDBC driver have not found");
        }
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:projectmanagmentsystem.db")) {
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

    public ArrayList<Task> getAllActiveTasksAndResponsibles() {
        System.out.println("Список задач в работе и их ответственных:");
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
            System.out.println("JDBC driver have not found");
        }
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:projectmanagmentsystem.db")) {
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

    public ArrayList<Responsible> getExpiredResponsibles() {
        System.out.println("Список ответственных просрочивших задачи с их контактами:");
        ArrayList<Responsible> responsiblesList = new ArrayList<Responsible>();
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
            System.out.println("JDBC driver have not found");
        }
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:projectmanagmentsystem.db")) {
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