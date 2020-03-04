package com.moose.mindvalley.models

data class NewEpisodes(
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