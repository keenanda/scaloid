package scaloid.example.data

import java.util.Date

import scala.collection.mutable.ArrayBuffer

object TodoManagerScaloid {
  var taskList:ArrayBuffer[TodoTaskScaloid] = ArrayBuffer(TodoTaskScaloid("Mow Lawn", 3, daysFromToday(1)),
                                              TodoTaskScaloid("Do Taxes", 4, daysFromToday(4)))

  private def daysFromToday(days: Int): Date = {
    val dt: Long = System.currentTimeMillis + 1000 * 60 * 60 * 24 * days
    return new Date(dt)
  }

  def getTodoList: Array[TodoTaskScaloid] = {
    taskList.toArray
  }
}