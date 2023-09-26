package com.example.homeworktodolistwithroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment:Fragment() {
    private lateinit var viewModel: EmployeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(EmployeeViewModel::class.java)
        val listView:RecyclerView = view.findViewById(R.id.list)
        val fab:FloatingActionButton = view.findViewById(R.id.fab)
        val adapter = EmployeeListAdapter()
        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.listState.observe(viewLifecycleOwner) {uaState ->
            when(uaState) {
                is EmployeeViewModel.ListState.EmptyList -> Unit
                is EmployeeViewModel.ListState.UpdateList -> {
                    adapter.updateItems(uaState.list)
                }
            }
        }

        fab.setOnClickListener {
            val fragment = AddEmployeeFragment()
            parentFragmentManager.beginTransaction()
                .add(R.id.container, fragment)
                .addToBackStack(fragment.javaClass.name)
                .commit()
        }
    }
}