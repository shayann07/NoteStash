package com.example.note_takingapp.ui


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.note_takingapp.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private val viewModel: NoteViewModel by viewModels { NoteVMFactory(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val body = binding.etBody.text.toString().trim()
            if (title.isNotEmpty() || body.isNotEmpty()) {
                viewModel.add(title, body)
                finish()
            } else {
                binding.etTitle.error = "Enter something"
            }
        }
    }
}