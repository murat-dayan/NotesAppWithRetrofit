package com.example.notesappwithretrofit

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class RVNotesAdapter(private val mContext:Context, private val notesList: List<Notes>)
    : RecyclerView.Adapter<RVNotesAdapter.CardViewNotesHolder>(){


        inner class CardViewNotesHolder(view:View): RecyclerView.ViewHolder(view){

            var cardView: CardView
            var cardViewLesson: TextView
            var cardViewNote1: TextView
            var cardViewNote2: TextView


            init {
                cardView= view.findViewById(R.id.cardViewId)
                cardViewLesson= view.findViewById(R.id.cardViewLessonId)
                cardViewNote1= view.findViewById(R.id.cardViewNote1)
                cardViewNote2= view.findViewById(R.id.cardViewNote2)
            }


        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewNotesHolder {
        val design= LayoutInflater.from(mContext).inflate(R.layout.card_view,parent,false)
        return CardViewNotesHolder(design)
    }

    override fun getItemCount(): Int {
        return  notesList.size
    }

    override fun onBindViewHolder(holder: CardViewNotesHolder, position: Int) {
        val note= notesList.get(position)

        holder.cardViewLesson.text= note.ders_adi
        holder.cardViewNote1.text= note.not1.toString()
        holder.cardViewNote2.text= note.not2.toString()

        holder.cardView.setOnClickListener {
            val intent= Intent(mContext,NoteEditActivity::class.java)
            intent.putExtra("note", note)
            mContext.startActivity(intent)
        }
    }
}