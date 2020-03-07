package com.moose.mindvalley.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.facebook.shimmer.ShimmerFrameLayout
import com.moose.mindvalley.R
import com.moose.mindvalley.models.Channel

class ChannelListAdapter(private val channels: List<Channel>):RecyclerView.Adapter<ChannelsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.channels_item, parent, false)
        return ChannelsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return channels.size
    }

    override fun onBindViewHolder(holder: ChannelsViewHolder, position: Int) {
        holder.bind(channels[position])
    }
}

class ChannelsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val icon: ImageView = itemView.findViewById(R.id.channel_icon)
    private val name: TextView = itemView.findViewById(R.id.channel_name)
    private val count: TextView = itemView.findViewById(R.id.items_count)
    private val shimmer: ShimmerFrameLayout = itemView.findViewById(R.id.image_shimmer)
    private val channelsRecycler:RecyclerView = itemView.findViewById(R.id.channel_items_recycler)

    fun bind(channel: Channel) {
        if (channel.title.isNotEmpty())
            name.text = channel.title
        else
            name.text = "Name not available"


        if (channel.series.isEmpty()){
            count.text = "${channel.mediaCount} Episodes"
            channelsRecycler.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = CourseListAdapter(channel.latestMedia)
            }

        }
        else{
            count.text = "${channel.mediaCount} Series"
            channelsRecycler.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = SeriesListAdapter(channel.latestMedia)
            }
        }

        //Handle null icons
        if (channel.iconAsset == null || channel.iconAsset!!.thumbnailUrl.isNullOrEmpty()){
            Glide.with(itemView.context).load(R.drawable.image_error)
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
                .into(icon)
        }
        else{
            Glide.with(itemView.context)
                .load(channel.iconAsset!!.thumbnailUrl)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .error(R.drawable.image_error)
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
                .into(icon)

        }
    }

}