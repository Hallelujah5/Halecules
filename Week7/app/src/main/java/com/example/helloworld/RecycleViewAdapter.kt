package com.example.helloworld
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecycleViewAdapter (private val mList: List<DataClass>) : RecyclerView.Adapter<RecycleViewAdapter.ViewHolderClass>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderClass {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.template_layout, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val Elements = mList[position]
        holder.textView.setText(Elements.StudentNames)
        holder.image.setImageResource(Elements.imgProfire)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.txtName)
        val image: ImageView = itemView.findViewById(R.id.imgPro)
    }

}