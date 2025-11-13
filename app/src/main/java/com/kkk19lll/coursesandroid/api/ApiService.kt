package com.kkk19lll.coursesandroid.api

import com.kkk19lll.coursesandroid.model.CoursesResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download")
    fun getCourses(): Call<CoursesResponse>
}
