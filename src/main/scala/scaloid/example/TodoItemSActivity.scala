package scaloid.example

import org.scaloid.common._

class TodoItemSActivity extends SActivity {

  onCreate {

    val pad: Int = getResources.getDimensionPixelSize(R.dimen.layout_padding)

    contentView = new SVerticalLayout {
      this += new SLinearLayout {
      }.fill.padding(pad).backgroundResource(R.color.background)
    }
  }
}