package com.example.notesappwithretrofit

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface NotesDaoInterface {

    @GET("tum_notlar.php")
    fun getNotes(): Call<NotesResponse>

    @POST("insert_not.php")
    @FormUrlEncoded
    fun insertNote(@Field("ders_adi") ders_adi:String,
                   @Field("not1") not1:Int,
                   @Field("not2") not2:Int ):Call<CRUDResponse>

    @POST("delete_not.php")
    @FormUrlEncoded
    fun deleteNote(@Field("not_id") not_id:Int): Call<CRUDResponse>

    @POST("update_not.php")
    @FormUrlEncoded
    fun updateNote(@Field("not_id") not_id:Int,
                   @Field("ders_adi") ders_adi:String,
                   @Field("not1") not1:Int,
                   @Field("not2") not2: Int ) : Call<CRUDResponse>

}