package au.edu.swin.sdmd.w07_numberlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class BookAdapter(private val context: Context, private var data: List<Book>) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.layout_book_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        private val bookIcon: ImageView = v.findViewById(R.id.bookIcon)
        private val bookTitle: TextView = v.findViewById(R.id.bookTitle)
        private val bookRating: TextView = v.findViewById(R.id.bookRating)

        fun bind(item: Book) {
            bookTitle.text = item.title
            bookRating.text = item.rating

            Picasso.get()
                .load(item.imageURL)
                .placeholder(android.R.drawable.picture_frame)
                .error(android.R.drawable.alert_light_frame)
                .into(bookIcon)

        }
    }

    fun updateData(newData: List<Book>) {
        data = newData
        notifyDataSetChanged()
    }
}