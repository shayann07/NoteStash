package com.example.note_takingapp.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.note_takingapp.data.Note
import com.example.note_takingapp.databinding.ItemNoteBinding

class NoteAdapter(
    private val onDelete: (Note) -> Unit
) : ListAdapter<Note, NoteAdapter.VH>(DIFF) {

    private var deleteCallback: ((Note) -> Unit)? = null
    fun setDeleteCallback(cb: (Note) -> Unit) {
        deleteCallback = cb
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bind(getItem(position))

    inner class VH(private val b: ItemNoteBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(note: Note) = with(b) {
            tvTitle.text = note.title
            tvBody.text = note.body

            btnDelete.setOnClickListener {
                onDelete(note)
                deleteCallback?.invoke(note)
            }
        }
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(o: Note, n: Note) = o.id == n.id
            override fun areContentsTheSame(o: Note, n: Note) = o == n
        }
    }
}