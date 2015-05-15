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

  onCreate {

    contentView = new SFrameLayout {
      SImageView().<<.fill.>>.scaleType(ScaleType.CENTER_CROP).imageResource(R.drawable.nyc)
      this += new SVerticalLayout {
        SImageView().<<.wrap.marginBottom(40 dip).>>.imageResource(R.drawable.logo).focusableInTouchMode(true)
        var mEmail = SEditText().<<.margin(10 dip).>>.inputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS).hint(R.string.email)
        var mPassword = SEditText().<<.margin(10 dip).>>.inputType(InputType.TYPE_TEXT_VARIATION_PASSWORD).hint(R.string.password)
        SButton(R.string.login, signin(mEmail.text.toString(), mPassword.text.toString())).<<.wrap.>>.padding(30 dip, 0, 30 dip, 0).background(R.drawable.bg_selector)
      }.padding(30 dip)
    }
  }

  onCreate {
    toast("Hey, we're created!")
  }

  onStop {
    toast("We're stopped")
  }

//  onCreate {
//
//    contentView = new SVerticalLayout {
//      STextView("Sign in").textSize(24.5 sp).<<.marginBottom(25 dip).>>
//      STextView("ID")
//      val userId = SEditText()
//      STextView("Password")
//      val pass = SEditText() inputType TEXT_PASSWORD
//      SButton("Sign in", signin(userId.text.toString(), pass.text.toString()))
//      this += new SLinearLayout {
//        SButton("Help", openUri("http://help.url"))
//        SButton("Sign up", openUri("http://signup.uri")).gravity(0)
//      }.wrap
//    }.padding(20 dip)
//  }

  def signin(uname:String, pass:String) = {
    toast("Sign in " + uname + ", " + pass)
  }
}
