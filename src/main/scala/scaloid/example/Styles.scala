package scaloid.example

import org.scaloid.common._
import android.graphics.Color

object Styles  {
  def bigredtxt(txt: STextView) = {
    txt.textColor(Color.RED)
    txt.textSize(20)
    txt
  }
}