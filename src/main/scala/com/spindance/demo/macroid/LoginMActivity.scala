package com.spindance.demo.macroid.activity

import android.app.Activity
import android.os.Bundle
import android.widget.{TextView, Button, LinearLayout}

import macroid._
import macroid.FullDsl._

class LoginMActivity extends Activity with Contexts[Activity] {
  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    // the layout goes here
    setContentView {
      getUi {
        l[LinearLayout](
          w[Button],
          w[TextView]
        )
      }
    }
  }
}