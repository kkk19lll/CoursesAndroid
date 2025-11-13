package com.kkk19lll.coursesandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kkk19lll.coursesandroid.api.ApiRetrofitClient
import com.kkk19lll.coursesandroid.model.Course
import com.kkk19lll.coursesandroid.model.CoursesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoursesViewModel : ViewModel() {

    private val _courses = MutableLiveData<List<Course>>()
    val courses: LiveData<List<Course>> get() = _courses

    fun loadCourses() {
        ApiRetrofitClient.instance.getCourses().enqueue(object : Callback<CoursesResponse> {
            override fun onResponse(
                call: Call<CoursesResponse>,
                response: Response<CoursesResponse>
            ) {
                response.body()?.let { data ->
                    _courses.value = data.courses
                }
            }

            override fun onFailure(call: Call<CoursesResponse>, t: Throwable) {
                _courses.value = emptyList()
            }
        })
    }

    fun sortByPublishDateDesc() {
        _courses.value = _courses.value?.sortedByDescending { it.publishDate }
    }
}
