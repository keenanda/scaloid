package scaloid.example

import android.view.{LayoutInflater, ViewGroup, View, Gravity}
import android.widget._
import org.scaloid.common._
import scaloid.example.data.{TodoTaskScaloid, TodoManagerScaloid}

class TodoListScaloid extends SActivity {

  var sortBy:SSpinner = null
  var listView:SListView = null

  private var taskList: Array[TodoTaskScaloid] = Array()

  onCreate {

    val pad:Int = getResources.getDimensionPixelSize(R.dimen.layout_padding)

    contentView = new SVerticalLayout {
      this += new SLinearLayout {
        STextView(R.string.sort_by).wrap
        sortBy = SSpinner().wrap
      }.padding(0, 0, 0, 10 dip).orientation(HORIZONTAL)
      listView = SListView().fw.dividerHeight(1 dip).divider(R.color.black).background(R.color.white).wrap.choiceMode(AbsListView.CHOICE_MODE_SINGLE)
    }.padding(pad).backgroundResource(R.color.background)


    val adapter = SArrayAdapter(getResources.getStringArray(R.array.sort_options)).dropDownStyle(_.textSize(20 dip).padding(15 dip))
    sortBy.setAdapter(adapter)
  }

  onResume {
    taskList = TodoManagerScaloid.getTodoList
    listView.setAdapter(new AppsAdapter)
  }


  class AppsAdapter extends BaseAdapter {

    def getView(position:Int, convertView: View, parent: ViewGroup): View = {
      var result = convertView
      if (result == null)
        result = LayoutInflater.from(parent.getContext).inflate(R.layout.todo_listitem, null)

      result.findViewById(R.id.task_name).asInstanceOf[TextView].setText(taskList(position).taskName)
      result
    }

    def getCount(): Int = taskList.size

    def getItem(position: Int): AnyRef = taskList(position)

    def getItemId(position: Int): Long = position
  }

}