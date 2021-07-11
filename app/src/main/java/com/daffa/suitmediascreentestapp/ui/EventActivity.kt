package com.daffa.suitmediascreentestapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.daffa.suitmediascreentestapp.R
import com.daffa.suitmediascreentestapp.databinding.ActivityEventBinding
import com.daffa.suitmediascreentestapp.model.entity.EventEntity
import com.daffa.suitmediascreentestapp.ui.adapter.EventAdapter
import com.daffa.suitmediascreentestapp.viewmodel.EventViewModel

class EventActivity : AppCompatActivity(), EventAdapter.OnItemClick {

    private lateinit var eventBinding: ActivityEventBinding
    private lateinit var eventViewModel: EventViewModel
    private lateinit var eventAdapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventBinding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(eventBinding.root)

        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.ic_back)
            setHomeButtonEnabled(true)
        }

        eventViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[EventViewModel::class.java]

        val listEvent = eventViewModel.getDataEvents()

        eventAdapter = EventAdapter()
        eventAdapter.setEvent(listEvent)
        eventAdapter.setOnItemClick(this)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        eventBinding.rvEvent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = eventAdapter
            setHasFixedSize(true)
        }
    }

    private fun Boolean.rv() {
        eventBinding.rvEvent.visibility = if (this) View.GONE else return
    }

    override fun itemClick(event: EventEntity) {
        val resultIntent = Intent()
        resultIntent.putExtra(VALUE_EVENT, event.name)
        setResult(RESULT_CODE_EVENT, resultIntent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_event, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.move_map -> {
                val eventMapFragment = EventMapFragment()
                val transaction = supportFragmentManager
                val fragment =
                    transaction.findFragmentByTag(EventMapFragment::class.java.simpleName)

                if (fragment !is EventMapFragment) {
                    transaction
                        .beginTransaction()
                        .replace(
                            R.id.event_activity,
                            eventMapFragment,
                            EventMapFragment::class.java.simpleName
                        )
                        .commit()
                    true.rv()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val VALUE_EVENT = "value"
        const val RESULT_CODE_EVENT = 100
    }
}