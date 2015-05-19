package com.spindance.demo.scala.activity

import android.app.ProgressDialog
import android.view.Gravity
import android.widget.ImageView.ScaleType
import com.spindance.demo.R
import org.scaloid.common._

import scala.concurrent.Future
import AndroidExecutionContext.exec


class LoginSActivity extends SActivity {

  onCreate {
    contentView = new SFrameLayout {
      SImageView().<<.fill.>>.scaleType(ScaleType.CENTER_CROP).imageResource(R.drawable.nyc)
      this += new SVerticalLayout {
        SImageView().<<.wrap.marginBottom(40 dip).marginTop(40 dip).Gravity(Gravity.CENTER_HORIZONTAL).>>.setImageResource(R.drawable.logo)
        val mEmail = SEditText().<<.margin(10 dip).>>.inputType(TEXT_EMAIL_ADDRESS).hint(R.string.email)
        val mPassword = SEditText().<<.margin(10 dip).>>.inputType(TEXT_PASSWORD).hint(R.string.password)
        SButton(R.string.login, loginPressed(mEmail.text.toString, mPassword.text.toString))
          .<<.wrap.marginRight(40 dip).marginLeft(40 dip).Gravity(Gravity.CENTER_HORIZONTAL).>>.padding(30 dip, 0, 30 dip, 0).background(R.drawable.bg_selector)
      }.<<.Gravity(Gravity.CENTER).>>.padding(30 dip)
    }
  }

  def loginPressed(uname:String, pass:String) = {
    val dlg = ProgressDialog.show(this, null, getString(R.string.busy_login), true, true)

    Future {
      val result = performLogin
      dlg.dismiss()
      startActivity(SIntent[TodoListSActivity])
    }
  }

  def performLogin: String = {
    Thread.sleep(1000)   // fake some network delay
    scala.io.Source.fromURL(getString(R.string.dummy_login_url)).mkString
  }
}
