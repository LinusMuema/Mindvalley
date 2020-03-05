package com.moose.mindvalley.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Categories(
    val `data`: CategoriesData
)

data class CategoriesData(
    val categories: List<Category>
)

data class Category(
    val name: String
)

@Entity(tableName = "db_categories")
class DbCategories(
    @PrimaryKey val id: Int,
    val categories: String
)