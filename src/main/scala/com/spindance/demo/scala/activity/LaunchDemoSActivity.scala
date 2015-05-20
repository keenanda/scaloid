package com.spindance.demo.scala.activity

import com.spindance.demo.R
import com.spindance.demo.activity.LoginActivity
import org.scaloid.common._

/**
 * Root activity for launching Java or Scaloid path through demo
 */
class LaunchDemoSActivity extends SActivity {

  onCreate {
    contentView = new SVerticalLayout {

      SButton(R.string.goto_java, loginJava)
      SButton(R.string.goto_scaloid, loginScala)
    } padding 20.dip
  }

  def loginJava = {
    startActivity(SIntent[LoginActivity])
  }

  def loginScala = {
    startActivity(SIntent[LoginSActivity])
  }
}


