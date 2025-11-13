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
import com.kkk19lll.coursesandroid.utils.FavoritesManager
import java.text.SimpleDateFormat
import java.util.Locale

class FavoritesAdapter(
    private var courses: List<Course>,
    private val favoritesManager: FavoritesManager,
    private val onRemove: (Course) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>() {

    class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tvTitleCourse)
        val description: TextView = view.findViewById(R.id.tvDescriptionCourse)
        val price: TextView = view.findViewById(R.id.tvPriseCourse)
        val rate: TextView = view.findViewById(R.id.tvRatingCourse)
        val date: TextView = view.findViewById(R.id.tvDateCourse)
        val favouriteBtn: ImageButton = view.findViewById(R.id.favouriteCourseBtn)
        val image: ImageView = view.findViewById(R.id.imageCourse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val course = courses[position]
        holder.title.text = course.title
        holder.description.text = course.text
        holder.price.text = "${course.price} р"
        holder.rate.text = course.rate.toString()
        holder.date.text = formatDate(course.startDate)

        val isFavorite = favoritesManager.isFavorite(course.id)
        updateFavoriteButton(holder, isFavorite)

        holder.favouriteBtn.setOnClickListener {
            favoritesManager.removeFavorite(course.id)
            onRemove(course) // вызываем callback во фрагмент
        }
    }

    private fun updateFavoriteButton(holder: FavoriteViewHolder, isFavorite: Boolean) {
        val colorRes = if (isFavorite) R.color.primary_color else R.color.white
        holder.favouriteBtn.setColorFilter(holder.itemView.context.getColor(colorRes))
    }

    override fun getItemCount(): Int = courses.size

    fun updateList(newCourses: List<Course>) {
        courses = newCourses
        notifyDataSetChanged()
    }

    private fun formatDate(dateString: String): String {
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
