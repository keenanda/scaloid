package scaloid.example;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import scaloid.example.data.TodoManager;

public class TodoItemActivity extends Activity {
    public static final String EXTRA_TASK = "TodoItem.Task";

    private TodoTask mTask;
    private EditText mName;
    private Spinner mPriority;
    private Button mDueDate;
    private Button mSave;

    public static Intent newCreateIntent(Context ctx) {
        Intent intent = new Intent(ctx, TodoItemActivity.class);
        return intent;
    }

    public static Intent newEditIntent(Context ctx, int taskId) {
        Intent intent = new Intent(ctx, TodoItemActivity.class);
        intent.putExtra(EXTRA_TASK, taskId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_todoitem);
        mDueDate = (Button) findViewById(R.id.due_date);
        mPriority = (Spinner) findViewById(R.id.priority);
        mName = (EditText) findViewById(R.id.task_name);
        mSave = (Button) findViewById(R.id.save_button);

        mTask = TodoManager.getTask(getIntent().getIntExtra(EXTRA_TASK, -1));
        initView();
    }

    private void initView() {
        ActionBar ab = getActionBar();
        ab.setTitle(mTask == null ? R.string.create_task : R.string.edit_task);
        if (mTask != null) {
            mName.setText(mTask.getTaskName());
            mPriority.setSelection(mTask.getPriority());
            mDueDate.setText(SimpleDateFormat.getDateInstance().format(mTask.getDueDate()));
        } else {
            mDueDate.setText(SimpleDateFormat.getDateInstance().format(new Date()));
        }

        mSave.setOnClickListener(saveClicked);
        mDueDate.setOnClickListener(dateClicked);
    }


    private View.OnClickListener saveClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                Date dueDate = SimpleDateFormat.getDateInstance().parse(mDueDate.getText().toString());
                if (mTask == null) {
                    mTask = new TodoTask(mName.getText().toString(), mPriority.getSelectedItemPosition(), dueDate);
                    TodoManager.addItem(mTask);
                } else {
                    mTask.setTaskName(mName.getText().toString());
                    mTask.setDueDate(dueDate);
                    mTask.setPriority(mPriority.getSelectedItemPosition());
                }
            } catch (Exception e) {
                Log.e(TodoItemActivity.class.getName(), e.getMessage());
            }
            finish();
        }
    };

    private View.OnClickListener dateClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                GregorianCalendar cal = new GregorianCalendar();
                cal.setTime(SimpleDateFormat.getDateInstance().parse(mDueDate.getText().toString()));

                DatePickerDialog dlg = new DatePickerDialog(TodoItemActivity.this, dateSelected, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                dlg.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    DatePickerDialog.OnDateSetListener dateSelected = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            GregorianCalendar cal = new GregorianCalendar();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, monthOfYear);
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            mDueDate.setText(SimpleDateFormat.getDateInstance().format(cal.getTime()));
        }
    };
}