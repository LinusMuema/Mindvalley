package com.moose.mindvalley.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Episodes(
    val `data`: EpisodesData
)

data class EpisodesData(
    val media: List<Media>
)

data class Media(
    val channel: EpisodesChannel,
    val coverAsset: EpisodesCoverAsset,
    val title: String,
    val type: String
)

data class EpisodesChannel(
    val title: String
)

data class EpisodesCoverAsset(
    val url: String
)

@Entity(tableName = "db_episodes")
data class DbEpisodes(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val episodes: String
)