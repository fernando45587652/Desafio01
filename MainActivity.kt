package com.example.desafio01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var contactList: MutableList<Book>
    private lateinit var addButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        contactList = mutableListOf()
        addButton = findViewById(R.id.add_book_button)
        database =
            FirebaseDatabase.getInstance().getReference("contacts")
        database.addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                contactList.clear()
                for (data in snapshot.children) {
                    val contact =
                        data.getValue(Book::class.java)
                    contact?.let { contactList.add(it) }
                }
                recyclerView.adapter =
                    BookAdapter(contactList) { book ->
                        val intent = Intent(this@MainActivity,
                            DetailActivity::class.java)
                        intent.putExtra("contact_id", book.id)
                        startActivity(intent)
                    }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "Error:${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
        addButton.setOnClickListener {
            val intent = Intent(this,
                AddBookActivity::class.java)
            startActivity(intent)
        }
    }
}