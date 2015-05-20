package com.spindance.demo.scala.data

import java.util.Date

import scala.collection.mutable.ArrayBuffer

/**
 * Utility for managing list of TodoTasks. They are just stored in memory with no persistence
 */
object TodoSManager {
  // init with some dummy data
  var taskList:ArrayBuffer[TodoSTask] = ArrayBuffer(TodoSTask("Mow lawn", 1, daysFromToday(4)),
                                              TodoSTask("Do taxes", 3, daysFromToday(2)),
                                              TodoSTask("Grocery shopping", 2, daysFromToday(3)))

  def getTodoList: Array[TodoSTask] = {
    taskList.toArray
  }

  def addTask(task:TodoSTask) = {
    taskList += task
  }

  private def daysFromToday(days: Int): Date = {
    val dt: Long = System.currentTimeMillis + 1000 * 60 * 60 * 24 * days
    return new Date(dt)
  }
}