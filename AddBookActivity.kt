package com.example.desafio01

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import java.util.*
class AddBookActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var tituloInput: EditText
    private lateinit var autorInput: EditText
    private lateinit var saveButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)
        tituloInput = findViewById(R.id.input_titulo)
        autorInput = findViewById(R.id.input_autor)
        saveButton = findViewById(R.id.save_book_button)
        database =
            FirebaseDatabase.getInstance().getReference("book")
        saveButton.setOnClickListener {
            val id = UUID.randomUUID().toString()
            val titulo = tituloInput.text.toString()
            val autor = autorInput.text.toString()
            if (titulo.isNotEmpty() && autor.isNotEmpty()) {
                val book = Book(id,titulo, autor)

                database.child(id).setValue(book).addOnSuccessListener {
                    Toast.makeText(this, "Libro agregado",
                        Toast.LENGTH_SHORT).show()
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this, "Error al guardar",
                        Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Completa todos los campos",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}