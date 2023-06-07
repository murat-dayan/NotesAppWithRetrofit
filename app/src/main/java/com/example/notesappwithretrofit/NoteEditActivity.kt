package com.example.notesappwithretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.notesappwithretrofit.databinding.ActivityNoteEditBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoteEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteEditBinding
    private lateinit var note: Notes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this@NoteEditActivity, R.layout.activity_note_edit)

        binding.toolbarNE.title= "Note Edit"
        setSupportActionBar(binding.toolbarNE)

        note= intent.getSerializableExtra("note") as  Notes

        if (note != null){
            binding.editTextLessonNameNE.setText(note.ders_adi)
            binding.editTextNote1NE.setText((note.not1).toString())
            binding.editTextNote2NE.setText((note.not2).toString())
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.not_edit_menu,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_delete ->{
                Snackbar.make(binding.constraint,"is Note Will Be Deleted!!",Snackbar.LENGTH_SHORT)
                    .setAction("OK"){
                        deleteNote()
                    }.show()
                    
                return true
            }
            R.id.action_edit ->{
                updateNote()
                return true
            }
            else -> return super.onOptionsItemSelected(item)

        }

    }

    fun deleteNote(){
        val ndi= ApiUtils.getNotesDaoInterface()

        ndi.deleteNote(note.not_id).enqueue(object : Callback<CRUDResponse>{
            override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>?) {
                if (response != null){
                    startActivity(Intent(this@NoteEditActivity, MainActivity::class.java))
                }
            }

            override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {
                Toast.makeText(this@NoteEditActivity, "Please Try Again", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun updateNote(){
        val ndi= ApiUtils.getNotesDaoInterface()

        val lessonName= binding.editTextLessonNameNE.text.toString()
        val note1= binding.editTextNote1NE.text.toString().toInt()
        val note2= binding.editTextNote2NE.text.toString().toInt()

        ndi.updateNote(note.not_id, lessonName,note1,note2 ).enqueue(object : Callback<CRUDResponse>{
            override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>?) {
                if (response != null){
                    startActivity(Intent(this@NoteEditActivity, MainActivity::class.java))
                }
            }

            override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {
                Toast.makeText(this@NoteEditActivity, "Please Try Again", Toast.LENGTH_SHORT).show()
            }

        })
    }


}