package com.moose.mindvalley.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.facebook.shimmer.ShimmerFrameLayout
import com.moose.mindvalley.R
import com.moose.mindvalley.models.LatestMedia

class CourseListAdapter(private val courses: List<LatestMedia>): RecyclerView.Adapter<CourseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.course_item, parent, false)
        return CourseViewHolder(view)
    }

    override fun getItemCount(): Int {
        val limit = 6
        return if (courses.size > 6)
            limit
        else
            limit
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        return holder.bind(courses[position])
    }
}

class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val cover: ImageView = itemView.findViewById(R.id.course_image)
    private val title: TextView = itemView.findViewById(R.id.course_title)
    private val shimmer: ShimmerFrameLayout = itemView.findViewById(R.id.image_shimmer)

    fun bind(course: LatestMedia) {
        if (course.title.isNullOrEmpty())
            title.text = "Title not available"
        else
            title.text = course.title

        val requestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(16))

        if (course.coverAsset.url.isEmpty()){
            Glide.with(itemView.context).load(R.drawable.image_error).centerCrop().apply(requestOptions)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        shimmer.hideShimmer()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        shimmer.hideShimmer()
                        return false
                    }

                })
                .into(cover)
        }
        else{
            Glide.with(itemView.context)
                .load(course.coverAsset.url)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .error(R.drawable.image_error)
                .centerCrop()
                .apply(requestOptions)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        shimmer.hideShimmer()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        shimmer.hideShimmer()
                        return false
                    }

                })
                .into(cover)
        }
    }
}