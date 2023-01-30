package com.example.student.studentDatabase.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.student.R
import com.example.student.databinding.FragmentStudentAddBinding
import com.example.student.studentDatabase.model.Student
import com.example.student.studentDatabase.viewModel.StudentListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class StudentAddFragment : Fragment() {

    private lateinit var binding: FragmentStudentAddBinding
    private  val viewModel: StudentListViewModel by viewModels()
    private val args: StudentAddFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_add, container, false)
        return binding.root
    }

    private fun addNewItem() {
        val name = binding.name.text.toString()
        val grad = binding.grade.text.toString()
        val detail = binding.detail.text.toString()
        val student = Student(0, name, grad, detail)
        viewModel.insert(student)

    }

    private fun bind(student: Student) {
        binding.apply {
            name.setText(student.name.toString())
            grade.setText(student.grade.toString())
            detail.setText(student.detail.toString())
            add.setOnClickListener {
                update()
                val action =
                    StudentAddFragmentDirections.actionStudentAddFragmentToStudentListFragment()
                findNavController().navigate(action)
                Toast.makeText(
                    activity?.applicationContext,
                    "Student updated successfully",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun update() {
        val student = Student(
            this.args.studentId,
            this.binding.name.text.toString(),
            this.binding.grade.text.toString(),
            this.binding.detail.text.toString()
        )
        viewModel.update(student)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id: Int = args.studentId
        if (id >= 0) {
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.detail(id).observe(viewLifecycleOwner) { st ->
                    bind(st)
                }
            }
        } else {
            binding.add.setOnClickListener {
                addNewItem()
                findNavController().navigateUp()
                Toast.makeText(
                    activity?.applicationContext,
                    "Student add successfully",
                    Toast.LENGTH_LONG
                ).show()

            }

        }
    }
}
