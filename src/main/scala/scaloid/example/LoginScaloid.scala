package scaloid.example

import android.text.InputType
import android.widget.ImageView.ScaleType
import org.scaloid.common._

class LoginScaloid extends SActivity {

//  onCreate {
//
//    contentView = new SFrameLayout {
//      SImageView().<<.fill.>>.scaleType(ScaleType.CENTER_CROP).setImageResource(R.drawable.nyc)
//      this += new SVerticalLayout {
//        SImageView().<<.wrap.marginBottom(40 dip).marginTop(40 dip).>>.setImageResource(R.drawable.logo)
//        SEditText().<<.margin(10 dip).>>.inputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS).hint(R.string.email)
//        SEditText().<<.margin(10 dip).>>.inputType(InputType.TYPE_TEXT_VARIATION_PASSWORD).hint(R.string.password)
//        SButton(R.string.login).<<.wrap.marginRight(40 dip).marginLeft(40 dip).>>.setPadding(30 dip, 0, 30 dip, 0).se(R.drawable.bg_selector)
//      }.<<.gravity(Gravity.CENTER).>>.padding(30 dip)
//    }.<<.fill.>>
//  }

  var mEmail: SEditText = null
  var mPassword: SEditText = null

  onCreate {
    contentView = new SFrameLayout {
      SImageView().<<.fill.>>.scaleType(ScaleType.CENTER_CROP).imageResource(R.drawable.nyc)
      this += new SRelativeLayout {
        mEmail = SEditText().<<.centerInParent.margin(10 dip).>>.inputType(TEXT_EMAIL_ADDRESS).hint(R.string.email)
        mPassword = SEditText().<<.margin(10 dip).below(mEmail).>>.inputType(TEXT_PASSWORD).hint(R.string.password)
        SButton(R.string.login, signin(mEmail.text.toString, mPassword.text.toString)).<<.wrap.centerHorizontal.below(mPassword).>>.padding(30 dip, 0, 30 dip, 0).background(R.drawable.bg_selector)
        SImageView().<<.wrap.centerHorizontal.marginBottom(30 dip).above(mEmail).>>.imageResource(R.drawable.logo)
      }.<<.fill.>>.padding(30 dip)
    }
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
