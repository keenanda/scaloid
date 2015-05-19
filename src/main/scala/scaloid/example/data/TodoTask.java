package scaloid.example.data;

import java.util.Date;

public class TodoTask {
    private String mTaskName;
    private int mPriority;
    private Date mDueDate;
    private boolean mCompleted;
    private int mId;

    private static int mIdCounter = 0;

    public TodoTask(String taskName, int priority, Date dueDate) {
        mTaskName = taskName;
        mPriority = priority;
        mDueDate = dueDate;
        mId = mIdCounter++;
    }

    public String getTaskName() {
        return mTaskName;
    }

    public void setTaskName(String taskName) {
        mTaskName = taskName;
    }

    public int getPriority() {
        return mPriority;
    }

    public void setPriority(int priority) {
        mPriority = priority;
    }

    public Date getDueDate() {
        return mDueDate;
    }

    public void setDueDate(Date dueDate) {
        mDueDate = dueDate;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean b) {
        mCompleted = b;
    }

    public int getId() {
        return mId;
    }
}
