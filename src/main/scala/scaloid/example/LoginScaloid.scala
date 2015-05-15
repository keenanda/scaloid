package scaloid.example

import android.view.Gravity
import android.widget.ImageView.ScaleType
import org.scaloid.common._

class LoginScaloid extends SActivity {

  onCreate {
    contentView = new SFrameLayout {
      SImageView().<<.fill.>>.scaleType(ScaleType.CENTER_CROP).imageResource(R.drawable.nyc)
      this += new SVerticalLayout {
        SImageView().<<.wrap.marginBottom(40 dip).marginTop(40 dip).Gravity(Gravity.CENTER_HORIZONTAL).>>.setImageResource(R.drawable.logo)
        val mEmail = SEditText().<<.margin(10 dip).>>.inputType(TEXT_EMAIL_ADDRESS).hint(R.string.email)
        val mPassword = SEditText().<<.margin(10 dip).>>.inputType(TEXT_PASSWORD).hint(R.string.password)
        SButton(R.string.login, signin(mEmail.text.toString, mPassword.text.toString))
          .<<.wrap.marginRight(40 dip).marginLeft(40 dip).Gravity(Gravity.CENTER_HORIZONTAL).>>.padding(30 dip, 0, 30 dip, 0).background(R.drawable.bg_selector)
      }.<<.Gravity(Gravity.CENTER).>>.padding(30 dip)
    }

    getActionBar.
  }

  onCreate {
    toast("Hey, we're created!")
  }

  onStop {
    toast("We're stopped")
  }

  def signin(uname:String, pass:String) = {
    toast("Sign in " + uname + ", " + pass)
  }
}
