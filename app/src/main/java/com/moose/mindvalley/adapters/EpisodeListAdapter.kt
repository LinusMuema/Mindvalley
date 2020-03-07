package com.moose.mindvalley.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.moose.mindvalley.R
import com.moose.mindvalley.models.Media
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


class EpisodeListAdapter(private val episodes: List<Media>): RecyclerView.Adapter<EpisodesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.episode_item, parent,false)
        return EpisodesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        return holder.bind(episodes[position])
    }
}

class EpisodesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    private val title: TextView = itemView.findViewById(R.id.episode_title)
    private val channel: TextView = itemView.findViewById(R.id.episode_channel)
    private val cover: ImageView = itemView.findViewById(R.id.episode_image)
    private val shimmer: ShimmerFrameLayout = itemView.findViewById(R.id.image_shimmer)

    fun bind(media: Media) {
        if (media.title.isNotEmpty())
            title.text = media.title
        else
            title.text = "Title not available"
        if (media.channel.title.isNotEmpty())
            channel.text = media.channel.title
        else
            channel.text = "Channel not available"
        Picasso.get().load(media.coverAsset.url).error(R.drawable.image_error).fit().into(cover, object: Callback{
            override fun onSuccess() {
                shimmer.hideShimmer()
            }

            override fun onError(e: Exception?) {
                shimmer.hideShimmer()
            }
        })
    }
}