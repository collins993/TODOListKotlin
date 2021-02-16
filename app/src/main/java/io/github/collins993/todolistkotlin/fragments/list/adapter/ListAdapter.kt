package io.github.collins993.todolistkotlin.fragments.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.collins993.todolistkotlin.data.models.ToDoData
import io.github.collins993.todolistkotlin.databinding.RowLayoutBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

     var dataList = emptyList<ToDoData>()

    class ListViewHolder(private val binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(toDoData: ToDoData){
            binding.toDoData = toDoData
            binding.executePendingBindings()
        }

        companion object {

            fun from(parent: ViewGroup): ListViewHolder {

                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
                return ListViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        return ListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val currentItem = dataList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {

       return dataList.size
    }

    fun setData(toDoData: List<ToDoData>){

        val todoDiffUtil = TodoDiffUtil(dataList, toDoData)
        val toDoDiffResult = DiffUtil.calculateDiff(todoDiffUtil)
        this.dataList = toDoData
        toDoDiffResult.dispatchUpdatesTo(this)
    }
}