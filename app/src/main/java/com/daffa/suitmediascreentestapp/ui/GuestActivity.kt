package com.daffa.suitmediascreentestapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.daffa.suitmediascreentestapp.databinding.ActivityGuestBinding
import com.daffa.suitmediascreentestapp.model.entity.GuestEntity
import com.daffa.suitmediascreentestapp.ui.adapter.GuestAdapter
import com.daffa.suitmediascreentestapp.viewmodel.GuestViewModel
import com.daffa.suitmediascreentestapp.viewmodel.ViewModelFactory

class GuestActivity : AppCompatActivity(), GuestAdapter.OnItemClick {

    private lateinit var guestBinding: ActivityGuestBinding
    private lateinit var guestAdapter: GuestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        guestBinding = ActivityGuestBinding.inflate(layoutInflater)
        setContentView(guestBinding.root)

        guestBinding.toolbar.setNavigationOnClickListener { onBackPressed() }

        true.progressBar()

        val factory = ViewModelFactory.getInstance(this)
        val guestViewModel = ViewModelProvider(this, factory)[GuestViewModel::class.java]

        guestAdapter = GuestAdapter()

        guestViewModel.getListGuest().observe(this, {
            guestAdapter.apply {
                false.progressBar()
                setListGuest(it)
                notifyDataSetChanged()
                setOnItemClick(this@GuestActivity)
            }
            setRecyclerView()
        })
    }

    private fun setRecyclerView() {
        guestBinding.rvGuest.apply {
            layoutManager = GridLayoutManager(this@GuestActivity, 2)
            adapter = guestAdapter
            setHasFixedSize(true)
        }
    }

    private fun Boolean.progressBar() {
        guestBinding.progressShimmer.visibility = if (this) View.VISIBLE else View.GONE
    }

    override fun itemClick(guest: GuestEntity) {
        val resultIntent = Intent()

        resultIntent.putExtra(VALUE_GUEST_BIRTHDATE, guest.birthdate)
        resultIntent.putExtra(VALUE_GUEST_NAME, guest.name)

        setResult(RESULT_CODE_GUEST, resultIntent)
        finish()
    }

    companion object {
        const val VALUE_GUEST_NAME = "value"
        const val VALUE_GUEST_BIRTHDATE = "YYYY-MM-DD"
        const val RESULT_CODE_GUEST = 10
    }
}
