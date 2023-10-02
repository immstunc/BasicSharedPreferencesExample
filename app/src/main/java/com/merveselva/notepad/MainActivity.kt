package com.merveselva.notepad

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.merveselva.notepad.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferences : SharedPreferences
    var notesFromPref : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences = getSharedPreferences("com.merveselva.notepad", MODE_PRIVATE)
        notesFromPref = sharedPreferences.getString("notes" , "")

    }

    fun save(view : View){

        val savedNote = binding.savedNotesTextView.text.toString()
        val noteTitle = binding.noteTitleText.text.toString()
        val note = binding.notePath.text.toString()

        if ((noteTitle.isNullOrEmpty() )|| (note.isNullOrEmpty())) {
           binding.savedNotesTextView.text =  "title or note cannot be null!"

        }
        else {
            binding.savedNotesTextView.text = "Note Title: $noteTitle" + "\nYour note: $note"
            sharedPreferences.edit().putString("notes",savedNote).apply()

        }

    }

    fun delete(view: View) {

        notesFromPref = sharedPreferences.getString("notes" , "")

        if (notesFromPref!= "") {
            sharedPreferences.edit().remove("notes").apply()
            binding.savedNotesTextView.text = "Notes Deleted"

        }
        else{
            binding.savedNotesTextView.text = "No note to delete"
        }
        binding.noteTitleText.text.clear()
        binding.notePath.text.clear()

    }


}