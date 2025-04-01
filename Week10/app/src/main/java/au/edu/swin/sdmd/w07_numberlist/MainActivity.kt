package au.edu.swin.sdmd.w07_numberlist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var numberList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        numberList = findViewById(R.id.numberList) // Initialize numberList
        numberList.layoutManager = LinearLayoutManager(this)

        val data = IntArray(100) { it }.filter { it % 3 == 0 || it % 5 == 0 }

        numberList.adapter = NumberAdapter(data) { number ->
            val intent = Intent(this, NumberFactActivity::class.java)
            intent.putExtra("number", number)
            startActivity(intent)
        }

        val books = listOf(
            Book("https://covers.openlibrary.org/b/id/9999512-L.jpg", "Book 1", "Rating: 4.5"),
            Book("https://covers.openlibrary.org/b/id/9999512-L.jpg", "Book 2", "Rating: 3.5"),
            Book("https://covers.openlibrary.org/b/id/9999512-L.jpg", "Book 3", "Rating: 5.0"),
            Book("https://covers.openlibrary.org/b/id/9999512-L.jpg", "Book 4", "Rating: 4.0"),
            Book("https://covers.openlibrary.org/b/id/9999512-L.jpg", "Book 5", "Rating: 3.0"),
            Book("https://covers.openlibrary.org/b/id/9999512-L.jpg", "Book 6", "Rating: 4.5"),
            Book("https://covers.openlibrary.org/b/id/9999512-L.jpg", "Book 7", "Rating: 3.5"),
            Book("https://covers.openlibrary.org/b/id/9999512-L.jpg", "Book 8", "Rating: 5.0"),
            Book("https://covers.openlibrary.org/b/id/9999512-L.jpg", "Book 9", "Rating: 4.0"),
            Book("https://covers.openlibrary.org/b/id/9999512-L.jpg", "Book 10", "Rating: 3.0")
        )

        val bookList = findViewById<RecyclerView>(R.id.bookList)
        bookList.layoutManager = LinearLayoutManager(this)
        bookList.adapter = BookAdapter(this, books)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_short_list -> {
                updateList(50)
                true
            }
            R.id.menu_long_list -> {
                updateList(100)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateList(size: Int) {
        val data = IntArray(size) { it }.filter { it % 3 == 0 || it % 5 == 0 }
        (numberList.adapter as NumberAdapter).updateData(data)
    }
}