package com.spindance.demo.scala.activity

import java.text.{SimpleDateFormat, DateFormat}
import java.util.{Calendar, GregorianCalendar, Date}

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.text.TextUtils
import android.view.Gravity
import android.widget.DatePicker
import com.spindance.demo.scala.data.{TodoSTask, TodoSManager}
import org.scaloid.common._
import com.spindance.demo.R

class TodoItemSActivity extends SActivity {

  private var mPrioritySpinner: SSpinner = null
  private var mDueDateButton: SButton = null
  private var mTaskName: SEditText = null

  private val mDateFormat: DateFormat = DateFormat.getDateInstance

  onCreate {
    setTitle(R.string.create_task)

    val pad: Int = getResources.getDimensionPixelSize(R.dimen.layout_padding)

    contentView = new SFrameLayout {
      this += new SVerticalLayout {
        mTaskName = SEditText().<<.marginBottom(pad).>>.hint(R.string.task_name).fw
        this += new SLinearLayout {
                    STextView(R.string.priority).wrap.marginRight(pad)
                    mPrioritySpinner = SSpinner().wrap.prompt(R.string.priority)
                  }.wrap.<<.marginBottom(pad).>>
        this += new SLinearLayout {
                  STextView(R.string.due_date).wrap.marginRight(pad)
                  mDueDateButton = SButton(mDateFormat.format(new Date()), dueDatePressed).wrap
                }.wrap.<<.marginBottom(pad).>>
        SButton(R.string.save, savePressed)
          .<<.wrap.marginRight(40 dip).marginLeft(40 dip).Gravity(Gravity.CENTER_HORIZONTAL).>>.padding(30 dip, 0, 30 dip, 0).background(R.drawable.bg_selector)

      }.backgroundResource(R.color.background).fill.padding(pad)
    }

    val adapter = SArrayAdapter(getResources.getStringArray(R.array.priorities)).dropDownStyle(_.textSize(20 dip).padding(15 dip))
    mPrioritySpinner.setAdapter(adapter)
  }


  def dueDatePressed = {
    val cal: GregorianCalendar = new GregorianCalendar
    cal.setTime(mDateFormat.parse(mDueDateButton.getText.toString))

    val dlg: DatePickerDialog = new DatePickerDialog(this, dateSelected, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
    dlg.show
  }

  def savePressed = {
    if (!TextUtils.isEmpty(mTaskName.getText.toString)) {
      TodoSManager.addTask(TodoSTask(mTaskName.getText.toString, mPrioritySpinner.getSelectedItemPosition, mDateFormat.parse(mDueDateButton.getText.toString)))
    }
    finish
  }

  var dateSelected: OnDateSetListener =  new OnDateSetListener {
    override def onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int): Unit = {
      val cal: GregorianCalendar = new GregorianCalendar
      cal.set(Calendar.YEAR, year)
      cal.set(Calendar.MONTH, monthOfYear)
      cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
      mDueDateButton.setText(mDateFormat.format(cal.getTime))
    }
  }
}