package com.example.notesappwithretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesappwithretrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var notesList: ArrayList<Notes>
    private lateinit var rvNAdapter: RVNotesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.toolbar.title= "Notes"
        setSupportActionBar(binding.toolbar)

        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = LinearLayoutManager(this)

        getNotesWithRetrofit()

        binding.fab.setOnClickListener {
            startActivity(Intent(this@MainActivity, NoteRecordActivity::class.java))
        }


    }

    override fun onResume() {
        super.onResume()

        // RecyclerView'ı yenile
        getNotesWithRetrofit()
    }

    fun getNotesWithRetrofit() {

        val ndi = ApiUtils.getNotesDaoInterface()

        ndi.getNotes().enqueue(object : Callback<NotesResponse> {
            override fun onResponse(
                call: Call<NotesResponse>?, response: Response<NotesResponse>?
            ) {
                if (response != null) {

                    val responseList = response.body().notlar
                    notesList = ArrayList<Notes>(responseList)
                    rvNAdapter = RVNotesAdapter(this@MainActivity, notesList)
                    binding.rv.adapter = rvNAdapter

                }
            }

            override fun onFailure(call: Call<NotesResponse>?, t: Throwable?) {
                TODO("Not yet implemented")
            }

        })

    }

}