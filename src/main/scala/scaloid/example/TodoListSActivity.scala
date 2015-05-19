package scaloid.example

import java.text.{SimpleDateFormat, DateFormat}

import android.view._
import android.widget._
import org.scaloid.common._
import scaloid.example.data.{TodoTaskScaloid, TodoManagerScaloid}

class TodoListSActivity extends SActivity {

  var sortBy:SSpinner = null
  var listView:SListView = null
  var listAdapter: TodoTaskAdapter = null

  private val mDateFormat: DateFormat = DateFormat.getDateInstance
  private var taskList: Array[TodoTaskScaloid] = Array()

  onCreate {

    val pad:Int = getResources.getDimensionPixelSize(R.dimen.layout_padding)

    contentView = new SVerticalLayout {
      this += new SLinearLayout {
        STextView(R.string.sort_by).wrap
        sortBy = SSpinner().wrap
      }.padding(0, 0, 0, 10 dip).orientation(HORIZONTAL)
      listView = SListView().backgroundResource(R.color.white).fw.dividerHeight(1 dip).divider(R.color.black).wrap.choiceMode(AbsListView.CHOICE_MODE_SINGLE)
    }.padding(pad).backgroundResource(R.color.background)


    val adapter = SArrayAdapter(getResources.getStringArray(R.array.sort_options)).dropDownStyle(_.textSize(20 dip).padding(15 dip))
    sortBy.setAdapter(adapter)
    sortBy.onItemSelected(sortList)

    listAdapter = new TodoTaskAdapter
    listView.setAdapter(listAdapter)
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

    taskList = TodoManagerScaloid.getTodoList
    if (sortBy.getSelectedItemPosition == 0) {
      taskList = taskList.sortBy(_.dueDate)
    }
    else {
      taskList = taskList.sortBy(_.priority)
    }

    listAdapter.notifyDataSetChanged()
  }

  def showNewTask = {
    startActivity(SIntent[TodoItemSActivity])
  }

  class TodoTaskAdapter extends BaseAdapter {

    def getView(position:Int, convertView: View, parent: ViewGroup): View = {
      var result = convertView
      if (result == null)
        result = LayoutInflater.from(parent.getContext).inflate(R.layout.todo_listitem, null)

      result.findViewById(R.id.task_name).asInstanceOf[TextView].setText(taskList(position).taskName)
      result.findViewById(R.id.due_date).asInstanceOf[TextView].setText(mDateFormat.format(taskList(position).dueDate))
      result.findViewById(R.id.priority).getBackground.setLevel(taskList(position).priority)
      result
    }

    def getCount(): Int = taskList.size

    def getItem(position: Int): AnyRef = taskList(position)

    def getItemId(position: Int): Long = position
  }

}