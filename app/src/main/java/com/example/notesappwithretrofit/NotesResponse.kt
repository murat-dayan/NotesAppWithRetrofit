package com.example.notesappwithretrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NotesResponse(
    @SerializedName("notlar")
    @Expose
    var notlar: List<Notes>,
    @SerializedName("success")
    @Expose
    var success: Int
) {
}