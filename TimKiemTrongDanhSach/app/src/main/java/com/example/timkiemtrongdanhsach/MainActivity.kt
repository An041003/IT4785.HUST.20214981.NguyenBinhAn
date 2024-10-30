
package com.example.timkiemtrongdanhsach

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.timkiemtrongdanhsach.R

class MainActivity : AppCompatActivity() {

    data class Student(
        val name: String,
        val mssv: String
    ) {
        override fun toString(): String {
            return "$name - $mssv"
        }
    }

    private lateinit var studentList: List<Student>
    private lateinit var filteredList: MutableList<Student>
    private lateinit var arrayAdapter: ArrayAdapter<Student>
    private lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchEditText = findViewById(R.id.searchEditText)
        val listView = findViewById<ListView>(R.id.listView)

        studentList = listOf(
            Student("Nguyen Van A", "20210001"),
            Student("Tran Thi B", "20210002"),
            Student("Le Van C", "20210003"),
            Student("Pham Thi D", "20210004"),
            Student("Hoang Van E", "20210005"),
            Student("Nguyen Thi F", "20210006"),
            Student("Dang Van G", "20120007"),
            Student("Vu Thi H", "20210008"),
            Student("Nguyen Van I", "20210009"),
            Student("Tran Thi J", "20210010"),
            Student("Le Van K", "20210011"),
            Student("Pham Thi L", "20210012"),
            Student("Hoang Van M", "20210013")
        )

        filteredList = studentList.toMutableList()

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredList)
        listView.adapter = arrayAdapter

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterList(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterList(query: String) {
        filteredList.clear()
        if (query.length > 2) {
            val lowerCaseQuery = query.lowercase()
            filteredList.addAll(studentList.filter {
                it.name.lowercase().contains(lowerCaseQuery) || it.mssv.contains(query)
            })
        } else {
            filteredList.addAll(studentList)
        }
        arrayAdapter.notifyDataSetChanged()
    }
}


