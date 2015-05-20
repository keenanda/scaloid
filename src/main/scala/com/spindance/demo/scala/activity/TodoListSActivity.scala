package com.spindance.demo.scala.activity

import java.text.DateFormat

import android.view._
import android.widget._
import com.spindance.demo.scala.data.{TodoSManager, TodoSTask}
import org.scaloid.common._
import com.spindance.demo.R

class TodoListSActivity extends SActivity {

  var mSortBy:SSpinner = null
  var mListView:SListView = null
  var mListAdapter: TodoTaskAdapter = null

  private val mDateFormat: DateFormat = DateFormat.getDateInstance
  private var mTaskList: Array[TodoSTask] = Array()

  onCreate {

    val pad:Int = getResources.getDimensionPixelSize(R.dimen.layout_padding)

    contentView = new SVerticalLayout {
      this += new SLinearLayout {
        STextView(R.string.sort_by).wrap
        mSortBy = SSpinner().wrap
      }.padding(0, 0, 0, 10 dip).orientation(HORIZONTAL)
      mListView = SListView().backgroundResource(R.color.white).fw.dividerHeight(1 dip).divider(R.color.black).wrap.choiceMode(AbsListView.CHOICE_MODE_SINGLE)
    }.padding(pad).backgroundResource(R.color.background)


    val adapter = SArrayAdapter(getResources.getStringArray(R.array.sort_options)).dropDownStyle(_.textSize(20 dip).padding(15 dip))
    mSortBy.setAdapter(adapter)
    mSortBy.onItemSelected(sortList)

    mListAdapter = new TodoTaskAdapter
    mListView.setAdapter(mListAdapter)
  }

  onResume {
    sortList
  }

  override def onCreateOptionsMenu(menu: Menu) = {
    getMenuInflater.inflate(R.menu.todolist_menu, menu)
    true
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    item.getItemId match {
      case R.id.action_newtask =>
        showNewTask
        return true
      case _ =>
        return super.onOptionsItemSelected(item)
    }
  }

  def sortList = {

    mTaskList = TodoSManager.getTodoList
    if (mSortBy.getSelectedItemPosition == 0) {
      mTaskList = mTaskList.sortBy(_.dueDate)
    }
    else {
      mTaskList = mTaskList.sortBy(_.priority)
    }

    mListAdapter.notifyDataSetChanged()
  }

  def showNewTask = {
    startActivity(SIntent[TodoItemSActivity])
  }

  class TodoTaskAdapter extends BaseAdapter {

    def getView(position:Int, convertView: View, parent: ViewGroup): View = {
      var result = convertView
      if (result == null)
        result = LayoutInflater.from(parent.getContext).inflate(R.layout.todo_listitem, null)

      result.findViewById(R.id.task_name).asInstanceOf[TextView].setText(mTaskList(position).taskName)
      result.findViewById(R.id.due_date).asInstanceOf[TextView].setText(mDateFormat.format(mTaskList(position).dueDate))
      result.findViewById(R.id.priority).getBackground.setLevel(mTaskList(position).priority)
      result
    }

    def getCount(): Int = mTaskList.size

    def getItem(position: Int): AnyRef = mTaskList(position)

    def getItemId(position: Int): Long = position
  }
}