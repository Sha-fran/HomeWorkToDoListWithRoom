package com.example.homeworktodolistwithroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class AddEmployeeFragment:Fragment() {
    private lateinit var viewmodel:NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_note_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)
        val noteInput:EditText = view.findViewById(R.id.noteInputField)
        val addButton:Button = view.findViewById(R.id.addButton)

        addButton.setOnClickListener {
            val note = noteInput.text.toString()
            viewmodel.add(note)
            parentFragmentManager.popBackStack()
        }
    }
}
