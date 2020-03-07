package com.moose.mindvalley.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moose.mindvalley.R
import com.moose.mindvalley.models.Category

class CategoryListAdapter(private val categories: List<Category>):
    RecyclerView.Adapter<CategoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categories_item, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        return holder.bind(categories[position])
    }
}

class CategoriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val title:TextView = itemView.findViewById(R.id.category_title)
    fun bind(category: Category) {
        title.text = category.name
    }

}