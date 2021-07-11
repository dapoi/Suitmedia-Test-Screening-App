package com.daffa.suitmediascreentestapp.ui

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.daffa.suitmediascreentestapp.databinding.ActivityUserScreenBinding
import java.util.*


class UserScreenActivity : AppCompatActivity() {

    private lateinit var userScreenBinding: ActivityUserScreenBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userScreenBinding = ActivityUserScreenBinding.inflate(layoutInflater)
        setContentView(userScreenBinding.root)

        userScreenBinding.toolbar.setNavigationOnClickListener { onBackPressed() }

        // greetings
        Calendar.getInstance().also {
            with(userScreenBinding.tvGreet) {
                when (it.get(Calendar.HOUR_OF_DAY)) {
                    in 0..11 -> text = "Selamat Pagi!"
                    in 11..15 -> text = "Selamat Siang!"
                    in 15..18 -> text = "Selamat Sore!"
                    in 18..24 -> text = "Selamat Malam!"
                }
            }
        }

        // received data that has been input
        val resultName = intent.extras
        if (resultName != null) {
            val getName = resultName.getString(NAME)
            userScreenBinding.tvName.text = getName

            val builder = AlertDialog.Builder(this)
            if (isInPalindromeString(getName)) {
                builder.setTitle("$getName is a Palindrome String")
                builder.setPositiveButton("Ok") { _: DialogInterface, _: Int ->
                    Toast.makeText(this, "Unique Name", Toast.LENGTH_SHORT).show()
                }
            } else {
                builder.setTitle("$getName is not a Palindrome String")
                builder.setPositiveButton("Ok") { _: DialogInterface, _: Int ->
                    Toast.makeText(this, "Good Name", Toast.LENGTH_SHORT).show()
                }
            }
            builder.show()
        }

        // for move activity and received the data based on code
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

    private fun isInPalindromeString(name: String?): Boolean {
        val sb = StringBuilder(name)

        // reverse the String
        val reverseString = sb.reverse().toString()

        // compare reversed string with input string
        return name.equals(reverseString, ignoreCase = true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // for display the data
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
                            Toast.makeText(this, "iOS", Toast.LENGTH_SHORT).show()
                        }
                        date % 2 == 0 -> {
                            Toast.makeText(this, "Blackberry", Toast.LENGTH_SHORT)
                                .show()
                        }
                        date % 3 == 0 -> {
                            Toast.makeText(this, "Android", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(this, "Feature phone", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    val month = Integer.parseInt(dateArray[1])
                    var prime = false
                    var n = 2
                    while (!prime && n < month) {
                        if (month % n == 0) {
                            prime = true
                        }
                        n++
                    }
                    when {
                        prime -> {
                            Toast.makeText(this, "Bulan tidak prima", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(this, "Bulan prima", Toast.LENGTH_SHORT).show()
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