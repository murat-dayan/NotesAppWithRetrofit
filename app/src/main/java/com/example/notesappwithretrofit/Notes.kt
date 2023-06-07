package com.example.notesappwithretrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Notes(
    @SerializedName("not_id")
    @Expose
    var not_id:Int,
    @SerializedName("ders_adi")
    @Expose
    var ders_adi:String,
    @SerializedName("not1")
    @Expose
    var not1:Int,
    @SerializedName("not2")
    @Expose
    var not2:Int,
): java.io.Serializable{}
