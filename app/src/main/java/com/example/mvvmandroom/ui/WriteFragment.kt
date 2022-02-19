package com.example.mvvmandroom.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvmandroom.R
import com.example.mvvmandroom.databinding.FragmentWriteBinding
import com.example.mvvmandroom.model.Note
import com.example.mvvmandroom.mvvm.NoteViewModel

class WriteFragment : Fragment() {

    private lateinit var binding: FragmentWriteBinding
    lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWriteBinding.inflate(layoutInflater, container, false)

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        binding.btnAddUpdate.setOnClickListener {

            val title = binding.editNoteTitle.text.toString()
            val desc = binding.editNoteDescription.text.toString()
            val model = Note(null, title, desc)
            viewModel.addNote(model)
            findNavController().navigate(R.id.listFragment)

        }
        return binding.root
    }
}