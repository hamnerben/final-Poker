package com.usu.todosmvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView
import com.usu.todosmvvm.databinding.TodoListItemBinding
import com.usu.todosmvvm.models.Pastry

class PastryAdapter(val todos: ObservableArrayList<Pastry>): RecyclerView.Adapter<PastryAdapter.ViewHolder>() {
    class ViewHolder(val binding: TodoListItemBinding): RecyclerView.ViewHolder(binding.root)


    init {
        todos.addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableArrayList<Pastry>>() {
            override fun onChanged(sender: ObservableArrayList<Pastry>?) {
                notifyDataSetChanged()
            }

            override fun onItemRangeChanged(
                sender: ObservableArrayList<Pastry>?,
                positionStart: Int,
                itemCount: Int
            ) {
                notifyItemChanged(positionStart)
            }

            override fun onItemRangeInserted(
                sender: ObservableArrayList<Pastry>?,
                positionStart: Int,
                itemCount: Int
            ) {
                notifyItemInserted(positionStart)
            }

            override fun onItemRangeMoved(
                sender: ObservableArrayList<Pastry>?,
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int
            ) {

            }

            override fun onItemRangeRemoved(
                sender: ObservableArrayList<Pastry>?,
                positionStart: Int,
                itemCount: Int
            ) {
                notifyItemRemoved(positionStart)
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TodoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todos[position]
        holder.binding.checkBox.text = todo.description
        holder.binding.checkBox.isChecked = todo.completed
        // do some stuff when the user checks a box
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}