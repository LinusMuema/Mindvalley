package com.moose.mindvalley.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Channels(
    val `data`: ChannelsData
)

data class ChannelsData(
    val channels: List<Channel>
)

data class Channel(
    val coverAsset: CoverAsset,
    val iconAsset: IconAsset,
    val id: String,
    val latestMedia: List<LatestMedia>,
    val mediaCount: Int,
    val series: List<Sery>,
    val slug: String,
    val title: String
)

data class CoverAsset(
    val url: String
)

data class IconAsset(
    val thumbnailUrl: String
)

data class LatestMedia(
    val coverAsset: CoverAssetX,
    val title: String,
    val type: String
)

data class CoverAssetX(
    val url: String
)

data class Sery(
    val coverAsset: CoverAssetXX,
    val title: String
)

data class CoverAssetXX(
    val url: String
)

@Entity
data class DbChannels(
    @PrimaryKey val id: Int,
    val channels: String
)