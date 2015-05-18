package scaloid.example.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import scaloid.example.TodoTask;

public class TodoManager {

    private static List<TodoTask> mTodoList = new ArrayList<TodoTask>();

    static {
        mTodoList.add(new TodoTask("Grocery shopping", 2, daysFromToday(3)));
        mTodoList.add(new TodoTask("Mow lawn", 1, daysFromToday(5)));
        mTodoList.add(new TodoTask("Do taxes", 3, daysFromToday(2)));
    }

    public static List<TodoTask> getTodoList() {
        return Collections.unmodifiableList(mTodoList);
    }

    public static void addItem(TodoTask t) {
        mTodoList.add(t);
    }

    public static TodoTask getTask(int id) {
        for (TodoTask t : mTodoList) {
            if (t.getId() == id)
                return t;
        }
        return null;
    }

    private static Date daysFromToday(int days) {
        long dt = System.currentTimeMillis() + 1000 * 60 * 60 * 24 * days;
        return new Date(dt);
    }
}
