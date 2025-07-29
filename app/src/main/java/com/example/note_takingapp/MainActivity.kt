package com.example.note_takingapp


import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.note_takingapp.databinding.ActivityMainBinding
import com.example.note_takingapp.ui.AddNoteActivity
import com.example.note_takingapp.ui.NoteVMFactory
import com.example.note_takingapp.ui.NoteViewModel
import com.example.note_takingapp.ui.adapter.NoteAdapter
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: NoteViewModel by viewModels { NoteVMFactory(application) }
    private val adapter by lazy {
        NoteAdapter(
            onDelete = { viewModel.delete(it) }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.recyclerView.adapter = adapter

        viewModel.notes.observe(this) { adapter.submitList(it) }

        binding.fab.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }

        // Undo delete example (optional)
        adapter.setDeleteCallback { note ->
            Snackbar.make(binding.root, "Note deleted", Snackbar.LENGTH_LONG)
                .setAction("Undo") { viewModel.add(note.title, note.body) }
                .show()
        }
    }
}