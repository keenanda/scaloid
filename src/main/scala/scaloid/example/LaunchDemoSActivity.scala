package scaloid.example

import com.spindance.demo.activity.LoginActivity
import org.scaloid.common._

class LaunchDemoSActivity extends SActivity {

  onCreate {
    contentView = new SVerticalLayout {

      SButton("Login Java", loginJava)
      SButton("Login Scala", loginScala)
    } padding 20.dip
  }

  def loginJava = {
    startActivity(SIntent[LoginActivity])
  }

  def loginScala = {
    startActivity(SIntent[LoginScaloid])
  }
}


