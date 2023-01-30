package com.example.student.studentDatabase.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.student.databinding.StudintListBinding
import com.example.student.studentDatabase.model.Student


class StudentAdapter (private val onItemClicked: (Student) -> Unit)
    : ListAdapter<Student, StudentAdapter.StudentViewHolder>(DiffCallback) {

    class StudentViewHolder( val binding: StudintListBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(student: Student) {
            binding.apply {
                studentName.text=student.name
                studentGrade.text=student.grade
            }
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder(
            StudintListBinding.inflate(LayoutInflater.from(viewGroup.context)))
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(item)
        }
        holder.bind(item)
    }
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem === newItem
            }
            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}

