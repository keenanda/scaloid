package com.spindance.demo.scala.data

import java.util.Date

import scala.collection.mutable.ArrayBuffer

object TodoSManager {
  var taskList:ArrayBuffer[TodoSTask] = ArrayBuffer(TodoSTask("Mow lawn", 1, daysFromToday(4)),
                                              TodoSTask("Do taxes", 3, daysFromToday(2)),
                                              TodoSTask("Grocery shopping", 2, daysFromToday(3)))

  private def daysFromToday(days: Int): Date = {
    val dt: Long = System.currentTimeMillis + 1000 * 60 * 60 * 24 * days
    return new Date(dt)
  }

  def getTodoList: Array[TodoSTask] = {
    taskList.toArray
  }
}