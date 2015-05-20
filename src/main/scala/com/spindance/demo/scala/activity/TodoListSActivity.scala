package com.spindance.demo.scala.activity

import java.text.DateFormat

import android.content.Intent
import android.view._
import android.widget.AdapterView.OnItemClickListener
import android.widget._
import com.spindance.demo.scala.data.{TodoSManager, TodoSTask}
import org.scaloid.common._
import com.spindance.demo.R

class TodoListSActivity extends SActivity with OnItemClickListener {

  var mSortBy:SSpinner = null
  var mListView:SListView = null
  var mListAdapter: TodoTaskAdapter = null

  private val mDateFormat: DateFormat = DateFormat.getDateInstance
  private var mTaskList: Array[TodoSTask] = Array()

  onCreate {

    val pad: Int = getResources.getDimensionPixelSize(R.dimen.layout_padding)

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
    mListView.setOnItemClickListener(this)
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
      mTaskList = mTaskList.sortWith((lhs, rhs) => lhs.priority > rhs.priority)
    }

    mListAdapter.notifyDataSetChanged()
  }

  def showNewTask = {
    startActivity(SIntent[TodoItemSActivity])
  }

  def onItemClick(parent: AdapterView[_], view: View, position: Int, id: Long) {
    val task_id = mTaskList(position).id
    new Intent().put(task_id).start[TodoItemSActivity]
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

//    //  implicit val ctx = parent.getContext
//      val view = new SLinearLayout {
//        val name = STextView("Grocery shopping").<<(0 dip, WRAP_CONTENT).>>.<<.Weight(5.0f).>>.textColor(R.color.black).gravity(Gravity.CENTER_VERTICAL).lines(2).padding(0, 10 dip, 0, 10 dip).textSize(20 sp)
//        val due = STextView("June 6, 2015").<<(0 dip, WRAP_CONTENT).>>.<<.Weight(2.0f).Gravity(Gravity.CENTER).>>.textColor(R.color.black)
//        due.setText(mDateFormat.format(mTaskList(position).dueDate))
//        name.setText(mTaskList(position).taskName)
//      }.padding(10 dip).backgroundResource(R.drawable.listitem_selector)
//      view
    }

    def getCount(): Int = mTaskList.size

    def getItem(position: Int): AnyRef = mTaskList(position)

    def getItemId(position: Int): Long = position
  }
}