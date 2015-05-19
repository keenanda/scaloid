package scaloid.example.data

import java.util.Date

class TodoTaskScaloid(var taskName:String, var priority:Int, var dueDate:Date, val id:Int) {
}

object TodoTaskScaloid {
  var count = 0
  def apply(taskName: String, priority:Int, dueDate:Date): TodoTaskScaloid = {
    count += 1
    new TodoTaskScaloid(taskName, priority, dueDate, count)
  }
}
