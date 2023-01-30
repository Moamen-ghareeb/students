package com.example.student.studentDatabase.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.student.R
import com.example.student.databinding.FragmentStudentListBinding
import com.example.student.studentDatabase.adapter.StudentAdapter
import com.example.student.studentDatabase.viewModel.StudentListViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class StudentListFragment : Fragment() {
    private lateinit var binding: FragmentStudentListBinding
    private  val viewModel: StudentListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_student_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = StudentAdapter {
            val action =
                StudentListFragmentDirections.actionStudentListFragmentToStudentDetailFragment(it.id)
            this.findNavController().navigate(action)
        }
        binding.studentRecycleView.adapter = adapter
        binding.studentRecycleView.layoutManager = LinearLayoutManager(this.requireContext())

        viewModel.readAll()
        viewModel.readAllData.observe(this.viewLifecycleOwner) { students ->
            students.let {
                adapter.submitList(it)
            }
        }
        binding.add.setOnClickListener {
            val navHostFragment =
                StudentListFragmentDirections.actionStudentListFragmentToStudentAddFragment(getString(R.string.add_student_fragment_title))
            findNavController().navigate(navHostFragment)
        }
    }
}
