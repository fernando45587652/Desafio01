package com.example.desafio01

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
class DetailActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val bookId = intent.getStringExtra("book_id")
        if (bookId != null) {
            database =
                FirebaseDatabase.getInstance().getReference("book").child(bookId)
            database.addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot)
                {
                    val book =
                        snapshot.getValue(Book::class.java)
                    findViewById<TextView>(R.id.detail_titulo).text= book?.titulo

                    findViewById<TextView>(R.id.detail_autor).text = book?.autor
                }
                override fun onCancelled(error: DatabaseError) {}
            })
        }
    }
}