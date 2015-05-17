package scaloid.example;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import scaloid.example.data.TodoManager;

public class TodoListActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_todolist);
        setupActionBar();
        setupAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.todolist_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setupActionBar() {
        ActionBar ab = getActionBar();
        ab.setTitle(R.string.todo_title);
    }

    private void setupAdapter() {
        setListAdapter(new TodoListAdapter(this, TodoManager.getTodoList()));
    }

    private static class TodoListAdapter extends ArrayAdapter<TodoTask> {
        public TodoListAdapter(Context context, List<TodoTask> taskList) {
            super(context, R.layout.todo_listitem, taskList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_listitem, null);
            }
            TextView name = (TextView)convertView.findViewById(R.id.task_name);
            name.setText(getItem(position).getTaskName());
            TextView dueDate = (TextView)convertView.findViewById(R.id.due_date);
            dueDate.setText(SimpleDateFormat.getDateInstance().format(getItem(position).getDueDate()));
            TextView priority = (TextView)convertView.findViewById(R.id.priority);
            priority.setText("" + getItem(position).getPriority());
            return convertView;
        }
    }
}