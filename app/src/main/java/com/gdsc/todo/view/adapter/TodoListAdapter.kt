package com.gdsc.todo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdsc.todo.databinding.TodoItemListBinding
import com.gdsc.todo.model.TodoModel


//  model의 TodoModel 리스트를 생성자로부터 전달받아 RecyclerView.Adapter를 상속받고,
//  RecyclerView.ViewHolder를 뷰홀더로 갖는 클래스를 구현합니다.
class TodoListAdapter(val deleteItem: (TodoModel) -> Unit) : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    private var todoItems: List<TodoModel> = listOf()

    inner class TodoViewHolder(private val binding: TodoItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todoModel: TodoModel) {
            binding.todoItemListTitleTV.text = todoModel.title
            binding.todoItemListTimeStampTV.text = todoModel.timeStamp
            binding.todoItemListCheckCB.isChecked = todoModel.checked
            binding.todoItemListDeleteBT.setOnClickListener {
                deleteItem(todoModel)
            }
        }
    }

    override fun getItemCount(): Int = todoItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val viewHolder = TodoItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todoModel = todoItems[position]
        holder.bind(todoModel)
    }

    fun setTodoItems(todoItems: List<TodoModel>) {
        this.todoItems = todoItems
        notifyDataSetChanged()
    }
}