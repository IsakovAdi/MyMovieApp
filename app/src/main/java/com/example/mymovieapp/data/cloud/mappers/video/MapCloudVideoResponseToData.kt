package com.example.mymovieapp.data.cloud.mappers.video

import com.example.mymovieapp.data.cloud.model.video.VideoCloud
import com.example.mymovieapp.data.cloud.model.video.VideosResponseCloud
import com.example.mymovieapp.data.models.video.VideoData
import com.example.mymovieapp.data.models.video.VideosResponseData
import com.example.mymovieapp.domain.Mapper

class MapCloudVideoResponseToData(private val mapper: Mapper<List<VideoCloud>, List<VideoData>>) :
    Mapper<VideosResponseCloud, VideosResponseData> {
    override fun map(from: VideosResponseCloud) = from.run {
        VideosResponseData(
            id = id,
            trailerList = mapper.map(trailerList)
        )
    }
}