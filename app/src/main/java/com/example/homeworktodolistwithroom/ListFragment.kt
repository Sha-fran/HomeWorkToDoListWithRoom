package com.example.homeworktodolistwithroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Collections

class ListFragment : Fragment() {
    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView: RecyclerView = view.findViewById(R.id.list)
        val fab: FloatingActionButton = view.findViewById(R.id.fab)
        val adapter = EmployeeListAdapter()

        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int =
                makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.END)

            override fun onMove(
                recyclerView: RecyclerView,
                startViewHolder: RecyclerView.ViewHolder,
                targetViewHolder: RecyclerView.ViewHolder
            ): Boolean {
                val fromIndex = startViewHolder.adapterPosition
                val toIndex = targetViewHolder.adapterPosition
                Collections.swap(adapter.items, fromIndex, toIndex)
                adapter.notifyItemMoved(fromIndex, toIndex)

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.END) {
                    viewModel.remove(adapter.items.get(viewHolder.adapterPosition))
                }
            }
        })

        itemTouchHelper.attachToRecyclerView(listView)

        viewModel.listState.observe(viewLifecycleOwner) { uaState ->
            when (uaState) {
                is NoteViewModel.ListState.EmptyList -> Unit
                is NoteViewModel.ListState.UpdateList -> {
                    adapter.updateItems(uaState.list)
                }
            }
        }

        fab.setOnClickListener {
            val fragment = AddNoteFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(fragment.javaClass.name)
                .commit()
        }
    }
}
