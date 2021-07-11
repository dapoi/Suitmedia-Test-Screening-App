package com.daffa.suitmediascreentestapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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

        eventBinding.toolbar.setNavigationOnClickListener { onBackPressed() }

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

    override fun itemClick(event: EventEntity) {
        val resultIntent = Intent()
        resultIntent.putExtra(VALUE_EVENT, event.name)
        setResult(RESULT_CODE_EVENT, resultIntent)
        finish()
    }

    companion object {
        const val VALUE_EVENT = "value"
        const val RESULT_CODE_EVENT = 100
    }
}