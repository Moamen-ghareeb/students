package com.example.student.studentDatabase.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.student.R
import com.example.student.studentDatabase.model.Student
import com.example.student.studentDatabase.viewModel.StudentListViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import  com.example.student.databinding.FragmentStudentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StudentDetailFragment : Fragment() {
    private lateinit var binding: FragmentStudentDetailBinding
    private  val viewModel: StudentListViewModel by viewModels ()
    private lateinit var student: Student
    private val args: StudentDetailFragmentArgs by navArgs()



    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    override  fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_student_detail, container, false)
      return binding.root
    }

    override  fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.studentId
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.detail(id).observe(viewLifecycleOwner) { selectedItem ->
                student = selectedItem
                bind(student)
            }
        }

    }

    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage(getString(R.string.delete_question))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                deleteItem()
            }
            .show()
    }

        private fun deleteItem() {
        viewModel.delete(student)
        findNavController().navigateUp()
    }

    private fun edit(studentId: Int){
     val action =   StudentDetailFragmentDirections.actionStudentDetailFragmentToStudentAddFragment(getString(R.string.edit_student_fragment_title),studentId)
        findNavController().navigate(action)
    }

    private  fun bind (student: Student){
        binding.studentNameDetail.text=student.name
        binding.studentDetailDetail.text=student.detail
        binding.studentGradeDetail.text=student.grade
        binding.deleteButton.setOnClickListener{showConfirmationDialog()}
        binding.editButton.setOnClickListener{edit((student.id))}
    }
}
