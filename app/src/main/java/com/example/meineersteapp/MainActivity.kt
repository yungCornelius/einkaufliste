package com.example.meineersteapp

import android.os.Bundle
import android.text.InputType
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.meineersteapp.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.AdapterView.OnItemLongClickListener

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var lvTodoList: ListView
    private lateinit var fab: FloatingActionButton
    private lateinit var shoppingItems: ArrayList<String>
    private lateinit var itemAdapter: ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        lvTodoList = findViewById(R.id.lvTodoList)
        fab = findViewById(R.id.floatingActionButton)
        shoppingItems = ArrayList()

        shoppingItems.add("Apfel")
        shoppingItems.add("Banane")

        itemAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, shoppingItems)
        lvTodoList.adapter = itemAdapter


        lvTodoList.setOnItemClickListener { _, _, pos, _ ->
            shoppingItems.removeAt(pos) // Artikel aus der Liste entfernen
            itemAdapter.notifyDataSetChanged() // Adapter informieren, dass sich die Daten geändert haben
            Toast.makeText(applicationContext, "Element gelöscht", Toast.LENGTH_SHORT).show()
        }


        fab.setOnClickListener {
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Hinzufügen")

            var input = EditText(this)
            input.hint = "Test eingeben"
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)

            builder.setPositiveButton("OK") { dialog, which ->

                val newItem = input.text.toString()
                if (newItem.isNotEmpty()) {
                    shoppingItems.add(newItem) // Artikel zur Liste hinzufügen
                    itemAdapter.notifyDataSetChanged() // Adapter informieren, dass sich die Daten geändert haben
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Eingabe darf nicht leer sein",
                        Toast.LENGTH_SHORT
                    ).show()
                }


            }

            builder.setNegativeButton("Abbrechen") { dialog, which ->
                Toast.makeText(applicationContext, "Abgebrochen", Toast.LENGTH_SHORT).show()
            }

            builder.show()

        }


    }

}