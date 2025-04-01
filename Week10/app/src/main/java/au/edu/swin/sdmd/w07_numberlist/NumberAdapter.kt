package au.edu.swin.sdmd.w07_numberlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NumberAdapter(private var data: List<Int>, private val itemClickListener: (Int) -> Unit) : RecyclerView.Adapter<NumberAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.layout_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, itemClickListener)
    }

    class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        private val number: TextView = v.findViewById(R.id.number)

        fun bind(item: Int, clickListener: (Int) -> Unit) {
            number.text = item.toString()
            v.setOnClickListener { clickListener(item) }
        }
    }

    fun updateData(newData: List<Int>) {
        data = newData
        notifyDataSetChanged()
    }
}