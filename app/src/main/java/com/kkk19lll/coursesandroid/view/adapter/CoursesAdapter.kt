package com.kkk19lll.coursesandroid.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kkk19lll.coursesandroid.R
import com.kkk19lll.coursesandroid.model.Course
import java.text.SimpleDateFormat
import java.util.Locale

class CoursesAdapter(
    private var courses: List<Course>
) : RecyclerView.Adapter<CoursesAdapter.CourseViewHolder>() {

    class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tvTitleCourse)
        val description: TextView = view.findViewById(R.id.tvDescriptionCourse)
        val price: TextView = view.findViewById(R.id.tvPriseCourse)
        val rate: TextView = view.findViewById(R.id.tvRatingCourse)
        val date: TextView = view.findViewById(R.id.tvDateCourse)
        val favouriteBtn: ImageButton = view.findViewById(R.id.favouriteCourseBtn)
        val image: ImageView = view.findViewById(R.id.imageCourse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courses[position]
        holder.title.text = course.title
        holder.description.text = course.text
        holder.price.text = "${course.price} р"
        holder.rate.text = course.rate.toString()
        holder.date.text = formatDate(course.startDate)
        holder.favouriteBtn.isSelected = course.hasLike
    }

    override fun getItemCount(): Int = courses.size

    fun updateList(newCourses: List<Course>) {
        courses = newCourses
        notifyDataSetChanged()
    }

    fun formatDate(dateString: String): String {
        return try {
            val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = parser.parse(dateString)

            val formatter = SimpleDateFormat("dd MMMM yyyy", Locale("ru"))
            formatter.format(date!!)
        } catch (e: Exception) {
            dateString
        }
    }
}
