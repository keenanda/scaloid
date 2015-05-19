package scaloid.example.data

import java.util.Date

import scala.collection.mutable.ArrayBuffer

object TodoManagerScaloid {
  var taskList:ArrayBuffer[TodoTaskScaloid] = ArrayBuffer(TodoTaskScaloid("Mow Lawn", 1, daysFromToday(4)),
                                              TodoTaskScaloid("Do Taxes", 3, daysFromToday(2)),
                                              TodoTaskScaloid("Grocery Shopping", 2, daysFromToday(3)))

  private def daysFromToday(days: Int): Date = {
    val dt: Long = System.currentTimeMillis + 1000 * 60 * 60 * 24 * days
    return new Date(dt)
  }

  def getTodoList: Array[TodoTaskScaloid] = {
    taskList.toArray
  }
}