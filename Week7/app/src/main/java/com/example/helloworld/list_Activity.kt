package com.example.helloworld
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class list_Activity : AppCompatActivity() {

    private lateinit var lsStudents: RecyclerView
    private val strList:Array<String> = arrayOf("AAA", "BBB", "CCC", "DDD", "EEE")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_layout)

        lsStudents = findViewById(R.id.lsStudent)
        lsStudents.layoutManager = LinearLayoutManager(this)
        lsStudents.setHasFixedSize(true)

        val data = ArrayList<DataClass>()

        val images = arrayOf(
            R.drawable.logo
        )

        for (i in strList.indices) {
            val imageIndex = i % images.size // Loops through images (0 -> 3)
            data.add(DataClass(images[imageIndex], strList[i]))
        }

        val ada = RecycleViewAdapter(data)
        lsStudents.adapter = ada

//        var arr = ArrayAdapter(this, R.layout.template_layout, strList)
//        lsStudents.setAdapter(arr)

//        lsStudents.setOnItemClickListener ({ parent, view, position, id ->
//            val element = arr.getItem(position) as String
//            Toast.makeText(this, element, Toast.LENGTH_LONG).show()
//        })
    }
}






