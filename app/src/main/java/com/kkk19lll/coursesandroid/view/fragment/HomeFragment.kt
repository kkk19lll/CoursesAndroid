package com.kkk19lll.coursesandroid.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kkk19lll.coursesandroid.R
import com.kkk19lll.coursesandroid.view.adapter.CoursesAdapter
import com.kkk19lll.coursesandroid.viewmodel.CoursesViewModel

class HomeFragment : Fragment() {

    private lateinit var adapter: CoursesAdapter
    private val viewModel: CoursesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        initRecyclerView(view)
        initSortButton(view)
        observeCourses()
        loadCourses()

        return view
    }

    private fun initRecyclerView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.relativeLayout)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = CoursesAdapter(emptyList())
        recyclerView.adapter = adapter
    }

    private fun initSortButton(view: View) {
        val sortBtn: ImageButton = view.findViewById(R.id.icoDateAddBtn)
        sortBtn.setOnClickListener {
            viewModel.sortByPublishDateDesc()
        }
    }

    private fun observeCourses() {
        viewModel.courses.observe(viewLifecycleOwner, Observer { courses ->
            adapter.updateList(courses)
        })
    }

    private fun loadCourses() {
        viewModel.loadCourses()
    }
}
