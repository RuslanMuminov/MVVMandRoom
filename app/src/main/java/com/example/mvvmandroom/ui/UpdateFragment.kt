package com.example.mvvmandroom.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvmandroom.R
import com.example.mvvmandroom.databinding.FragmentUpdateBinding
import com.example.mvvmandroom.model.Note
import com.example.mvvmandroom.mvvm.NoteViewModel

class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(layoutInflater, container, false)

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        val data = arguments?.getSerializable("key") as Note

        binding.editNoteTitle.setText(data.noteTitle)
        binding.editNoteDescription.setText(data.noteDescription)

        binding.btnAddUpdate.setOnClickListener {
            val id = data.id
            val editTitle = binding.editNoteTitle.text.toString()
            val editDesc = binding.editNoteDescription.text.toString()

            val editModel = Note(id, editTitle, editDesc)
            viewModel.updateNote(editModel)
            findNavController().navigate(R.id.listFragment)
        }
        return binding.root
    }

}