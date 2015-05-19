package scaloid.example

import org.scaloid.common._

class HelloScaloid extends SActivity {

  onCreate {
    contentView = new SVerticalLayout {

      SButton("Login Java", loginJava)
      SButton("Login Scala", loginScala)
    } padding 20.dip
  }

  def loginJava = {
    startActivity(SIntent[LoginJava])
  }

  def loginScala = {
    startActivity(SIntent[LoginScaloid])
  }
}


