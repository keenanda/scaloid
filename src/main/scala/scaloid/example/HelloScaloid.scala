package scaloid.example

import org.scaloid.common._

class HelloScaloid extends SActivity {

  onCreate {
    contentView = new SVerticalLayout {
//      style {
//        case b: SButton => b.textColor(Color.RED).onClick(toast("Bang!"))
//        case t: STextView => t.textSize(10 dip)
//        case v => v.backgroundColor(Color.YELLOW)
//      }

//      style {
//        case b: SButton => b.textColor(Color.RED).onClick(toast("Bang!"))
//        case t: STextView => t textSize 10.dip
//        case e: SEditText => e.backgroundColor(Color.YELLOW).textColor(Color.BLACK)
//      }

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


