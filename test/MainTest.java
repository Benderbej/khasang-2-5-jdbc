import mypersistence.Project;
import mypersistence.Responsible;
import mypersistence.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MainTest {
    Main main;

    @Before
    public void beforeTest(){
        main = new Main();
    }

    @Test
    public void getProjectsInWorkTest() {//TODO тест зависит от конкретных изменяемых данных - возможно имеет смысл дежрать тестовую базу данных заполненную тестовыми данными
        ArrayList<Project> projectArrayList = main.getProjectsInWork();
        assertTrue(projectArrayList != null);
        assertEquals("рога", projectArrayList.get(0).getName());
    }

    @Test
    public void getNumOfActiveTasks() throws Exception {//TODO тест зависит от конкретных изменяемых данных - возможно имеет смысл дежрать тестовую базу данных заполненную тестовыми данными
        assertEquals(4, main.getNumOfActiveTasks());
    }

    @Test
    public void getResponsiblesActiveTasks() throws Exception {//TODO тест зависит от конкретных изменяемых данных - возможно имеет смысл дежрать тестовую базу данных заполненную тестовыми данными
        ArrayList<Task> resp1ActiveTasks = main.getResponsiblesActiveTasks("Остап");
        assertTrue(resp1ActiveTasks != null);
        assertEquals("подпилка рогов", resp1ActiveTasks.get(0).getName());
    }

    @Test
    public void getAllActiveTasksAndResponsibles() throws Exception {//TODO тест зависит от конкретных изменяемых данных - возможно имеет смысл дежрать тестовую базу данных заполненную тестовыми данными
        ArrayList<Task> activeTasks = main.getAllActiveTasksAndResponsibles();
        assertEquals("шлифовка рогов", activeTasks.get(1).getName());
        assertEquals("Егор", activeTasks.get(1).getRespName());
    }

    @Test
    public void getExpiredResponsibles() throws Exception {//TODO тест зависит от конкретных изменяемых данных - возможно имеет смысл дежрать тестовую базу данных заполненную тестовыми данными
        ArrayList<Responsible> responsibles = main.getExpiredResponsibles();
        assertEquals("Остап", responsibles.get(0).getName());
        assertEquals("911", responsibles.get(0).getContactInfo());
    }
}