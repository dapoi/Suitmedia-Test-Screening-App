package com.daffa.suitmediascreentestapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.daffa.suitmediascreentestapp.R
import com.daffa.suitmediascreentestapp.databinding.FragmentEventMapBinding
import com.daffa.suitmediascreentestapp.ui.adapter.EventMapAdapter
import com.daffa.suitmediascreentestapp.viewmodel.EventViewModel


class EventMapFragment : Fragment(), EventMapAdapter.OnItemClicked {

    private lateinit var eventMapFragmentBinding: FragmentEventMapBinding
    private lateinit var eventViewModel: EventViewModel
    private lateinit var eventMapAdapter: EventMapAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        eventMapFragmentBinding = FragmentEventMapBinding.inflate(layoutInflater, container, false)
        return eventMapFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            eventViewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[EventViewModel::class.java]

            val listEvent = eventViewModel.getDataEvents()

            eventMapAdapter = EventMapAdapter()
            eventMapAdapter.setEvent(listEvent)
            eventMapAdapter.setOnItemClick(this)

            setRecyclerView()

        }
    }

    private fun setRecyclerView() {
        eventMapFragmentBinding.rvEventHorizontal.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = eventMapAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.move_list -> startActivity(Intent(context, EventActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

    override fun itemClicked(position: Int) {

    }
}