package com.example.notesappwithretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.notesappwithretrofit.databinding.ActivityNoteRecordBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoteRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_note_record)

        binding.toolbarNR.title= "Note Record"
        setSupportActionBar(binding.toolbarNR)


        binding.buttonRecordNote.setOnClickListener {
            val lesson= binding.editTextLessonName.text.toString()
            val note1= binding.editTextNote1.text.toString()
            val note2= binding.editTextNote2.text.toString()

            if (lesson.isEmpty() || note1.isEmpty() || note2.isEmpty()) {
                Toast.makeText(this@NoteRecordActivity, "please fill in the blanks", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                insertNoteWithRetrofit(lesson, note1.toInt(), note2.toInt())

            }
        }

    }

    fun insertNoteWithRetrofit(lessonName:String,note1:Int,note2:Int){
        val ndi= ApiUtils.getNotesDaoInterface()

        ndi.insertNote(lessonName,note1,note2).enqueue(object : Callback<CRUDResponse>{
            override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>?) {
                if (response != null){
                    startActivity(Intent(this@NoteRecordActivity,MainActivity::class.java))
                    finish()
                }
            }

            override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {
                Toast.makeText(this@NoteRecordActivity, "Please Try Again", Toast.LENGTH_SHORT).show()
            }

        })
    }
}