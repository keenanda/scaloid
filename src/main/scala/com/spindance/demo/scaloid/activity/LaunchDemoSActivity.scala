package com.spindance.demo.scaloid.activity

import com.spindance.demo.R
import com.spindance.demo.activity.LoginActivity
import com.spindance.demo.macroid.activity.LoginMActivity
import org.scaloid.common._

/**
 * Root activity for launching Java or Scaloid path through demo
 */
class LaunchDemoSActivity extends SActivity {

  onCreate {
    contentView = new SVerticalLayout {

      SButton(R.string.goto_java, loginJava)
      SButton(R.string.goto_scaloid, loginScala)
      SButton(R.string.goto_macroid, loginMacroid)
    } padding 20.dip
  }

  def loginJava = {
    startActivity(SIntent[LoginActivity])
  }

  def loginScala = {
    startActivity(SIntent[LoginSActivity])
  }

  def loginMacroid = {
    startActivity(SIntent[LoginMActivity])
  }
}


