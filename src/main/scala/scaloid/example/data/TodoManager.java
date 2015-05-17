package scaloid.example.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import scaloid.example.TodoTask;

/**
 * Created by davek on 5/17/15.
 */
public class TodoManager {

    private static List<TodoTask> mTodoList = new ArrayList<TodoTask>();

    static {
        mTodoList.add(new TodoTask("Grocery shopping", 8, daysFromToday(3)));
        mTodoList.add(new TodoTask("Mow lawn", 6, daysFromToday(5)));
    }

    public static List<TodoTask> getTodoList() {
        return Collections.unmodifiableList(mTodoList);
    }

    public static void addItem(TodoTask t) {
        mTodoList.add(t);
    }

    private static Date daysFromToday(int days) {
        long dt = System.currentTimeMillis() + 1000 * 60 * 60 * 24 * days;
        return new Date(dt);
    }
}
