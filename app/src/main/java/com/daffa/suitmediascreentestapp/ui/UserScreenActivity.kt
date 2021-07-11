package com.daffa.suitmediascreentestapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.daffa.suitmediascreentestapp.databinding.ActivityUserScreenBinding

class UserScreenActivity : AppCompatActivity() {

    private lateinit var userScreenBinding: ActivityUserScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userScreenBinding = ActivityUserScreenBinding.inflate(layoutInflater)
        setContentView(userScreenBinding.root)

        userScreenBinding.toolbar.setNavigationOnClickListener { onBackPressed() }

        val resultName = intent.extras
        if (resultName != null) {
            val getName = resultName.getString(NAME)
            userScreenBinding.tvName.text = getName
        }

        userScreenBinding.apply {
            btnEvent.setOnClickListener {
                Intent(this@UserScreenActivity, EventActivity::class.java).also {
                    startActivityForResult(it, CODE_EVENT)
                }
            }

            btnGuest.setOnClickListener {
                Intent(this@UserScreenActivity, GuestActivity::class.java).also {
                    startActivityForResult(it, CODE_GUEST)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CODE_EVENT -> {
                if (resultCode == EventActivity.RESULT_CODE_EVENT) {
                    val eventName = data?.getStringExtra(EventActivity.VALUE_EVENT)
                    userScreenBinding.btnEvent.text = eventName
                }
            }
            CODE_GUEST -> {
                if (resultCode == GuestActivity.RESULT_CODE_GUEST) {
                    val guestName = data?.getStringExtra(GuestActivity.VALUE_GUEST_NAME)
                    userScreenBinding.btnGuest.text = guestName
                    val guestBirthday = data?.getStringExtra(GuestActivity.VALUE_GUEST_BIRTHDATE)

                    val dateArray = guestBirthday!!.split("-")
                    val date = Integer.parseInt(dateArray[2])
                    when {
                        date % 2 == 0 && date % 3 == 0 -> {
                            Toast.makeText(applicationContext, "iOS", Toast.LENGTH_SHORT).show()
                        }
                        date % 2 == 0 -> {
                            Toast.makeText(applicationContext, "Blackberry", Toast.LENGTH_SHORT)
                                .show()
                        }
                        date % 3 == 0 -> {
                            Toast.makeText(applicationContext, "Android", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(applicationContext, "Feature phone", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val NAME = "name"
        const val CODE_EVENT = 1
        const val CODE_GUEST = 2
    }
}