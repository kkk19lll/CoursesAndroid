package com.kkk19lll.coursesandroid.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kkk19lll.coursesandroid.R
import com.kkk19lll.coursesandroid.model.Course
import com.kkk19lll.coursesandroid.utils.FavoritesManager
import com.kkk19lll.coursesandroid.view.adapter.FavoritesAdapter
import com.kkk19lll.coursesandroid.viewmodel.CoursesViewModel

class FavoritesFragment : Fragment() {

    private lateinit var adapter: FavoritesAdapter
    private lateinit var favoritesManager: FavoritesManager
    private val viewModel: CoursesViewModel by viewModels()

    private var allCourses: List<Course> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        favoritesManager = FavoritesManager(requireContext())
        initRecyclerView(view)
        observeCourses()
        return view
    }

    private fun initRecyclerView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerFavorites)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = FavoritesAdapter(emptyList(), favoritesManager) { removedCourse ->
            loadFavorites()
        }
        recyclerView.adapter = adapter
    }

    private fun observeCourses() {
        viewModel.courses.observe(viewLifecycleOwner) { courses ->
            allCourses = courses
            loadFavorites()
        }
        viewModel.loadCourses()
    }

    private fun loadFavorites() {
        val favoriteIds = favoritesManager.getFavorites().map { it.toInt() }
        val favoriteCourses = allCourses.filter { favoriteIds.contains(it.id) }

        Log.d("FavoritesFragment", "allCourses: ${allCourses.size}, favorites: ${favoriteCourses.size}")

        adapter.updateList(favoriteCourses)
    }
}
