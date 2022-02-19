package com.example.mvvmandroom.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmandroom.databinding.NoteItemBinding
import com.example.mvvmandroom.model.Note

class NoteAdapter(
    val noteClickInterface: NoteClickInterface,
    val noteClickDeleteInterface: NoteClickDeleteInterface
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

     var allNotes = emptyList<Note>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Note>) {
        this.allNotes = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(noteModel: Note) {
            binding.title.text = noteModel.noteTitle.toString()
            binding.description.text = noteModel.noteDescription.toString()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(allNotes[position])
        holder.binding.iconDelete.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes[position])
        }
        holder.binding.cardItem.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes[position])
        }
    }

    override fun getItemCount(): Int = allNotes.size
}

interface NoteClickDeleteInterface {
    fun onDeleteIconClick(noteModel: Note)
}

interface NoteClickInterface {
    fun onNoteClick(noteModel: Note)
}