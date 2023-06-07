package com.example.notesappwithretrofit

class ApiUtils {

    companion object{
        val BASE_URL= "http://kasimadalan.pe.hu/notlar/"

        fun getNotesDaoInterface(): NotesDaoInterface{
            return RetrofitClient.getClient(BASE_URL).create(NotesDaoInterface::class.java)
        }
    }

}