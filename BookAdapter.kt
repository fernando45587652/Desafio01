package com.example.desafio01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class BookAdapter(private val book: List<Book>, private
val listener: (Book) -> Unit) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val name: TextView = view.findViewById(R.id.add_book_button)
        val icon: ImageView =
            view.findViewById(R.id.book_icon)
        fun bind(book: Book, listener: (Book) -> Unit) {
            name.text = book.titulo
            icon.setImageResource(R.drawable.ic_book)
            itemView.setOnClickListener { listener(book) }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_book
                , parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position:
    Int) {
        holder.bind(book[position], listener)
    }
    override fun getItemCount() = book.size
}
