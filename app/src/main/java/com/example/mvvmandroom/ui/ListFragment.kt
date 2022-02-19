package com.example.mvvmandroom.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvmandroom.mvvm.NoteViewModel
import com.example.mvvmandroom.R
import com.example.mvvmandroom.adapter.NoteAdapter
import com.example.mvvmandroom.adapter.NoteClickDeleteInterface
import com.example.mvvmandroom.adapter.NoteClickInterface
import com.example.mvvmandroom.databinding.FragmentListBinding
import com.example.mvvmandroom.model.Note

class ListFragment : Fragment(), NoteClickInterface, NoteClickDeleteInterface {

    private lateinit var binding: FragmentListBinding
    lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater, container, false)

        val adapter = NoteAdapter(this, this)
        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        viewModel.allNotes.observe(viewLifecycleOwner) { list ->
            list?.let { adapter.setData(it) }
        }

        binding.rv.adapter = adapter

        binding.addBtn.setOnClickListener {
            findNavController().navigate(R.id.writeFragment)
        }
        return binding.root
    }

    override fun onNoteClick(noteModel: Note) {
        val bundle = Bundle()
        bundle.putSerializable("key", noteModel)
        findNavController().navigate(R.id.updateFragment, bundle)
    }

    override fun onDeleteIconClick(noteModel: Note) {
        viewModel.deleteNote(noteModel)
    }
}