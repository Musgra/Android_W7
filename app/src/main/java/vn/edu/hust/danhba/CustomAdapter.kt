package vn.edu.hust.danhba

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class MyCustomAdapter(private val items: ArrayList<ItemModel>, val context: Context) :
    RecyclerView.Adapter<MyCustomAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, OnLongClickListener {
        private val name: TextView = itemView.findViewById(R.id.item_title)
        private val itemAvatar: TextView = itemView.findViewById(R.id.item_avatar)

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        fun bindItem(item: ItemModel) {
            name.text = item.name
            itemAvatar.text = item.name[0].toString().uppercase()
        }

        override fun onClick(v: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val intent = Intent(context, ItemActivity::class.java)
                intent.putExtra("name", items[position].name)
                    .putExtra("phone", items[position].phoneNumber)
                    .putExtra("gmail", items[position].gmail)
                startActivity(context, intent, null)
            }
        }

        override fun onLongClick(v: View): Boolean {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                showPopupMenu(v, position)
            }
            return true
        }

        private fun showPopupMenu(view: View, position: Int) {
            val popup = PopupMenu(context, view)
            popup.inflate(R.menu.menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_call -> {
                        Toast.makeText(context, "call " + items[position].name, Toast.LENGTH_SHORT)
                            .show()
                    }
                    R.id.action_sendEmail -> {
                        Toast.makeText(context, "send email to " + items[position].name, Toast.LENGTH_SHORT)
                            .show()
                    }
                    else -> {
                        Toast.makeText(context, "send SMS to " + items[position].name, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                true
            }
            popup.show()
        }
    }
}
