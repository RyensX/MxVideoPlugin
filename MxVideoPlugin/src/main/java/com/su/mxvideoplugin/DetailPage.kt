package com.su.mxvideoplugin

import android.util.Log
import com.su.basevideopluginframework.framework.Const
import com.su.basevideopluginframework.framework.VideoDetailFramework
import com.su.basevideopluginframework.framework.data.VideoDetailInfo
import com.su.basevideopluginframework.framework.data.VideoPlayInfo
import com.su.basevideopluginframework.framework.data.VideoPlayListInfo
import org.jsoup.nodes.Document


/**
 *
 * Created by Ryens.
 * https://github.com/RyensX
 */
object DetailPage : VideoDetailFramework() {

    override fun videoDetail(document: Document): VideoDetailInfo? {
        var name = ""
        var desc = ""
        var cover = ""
        document.getElementsByClass("hl-dc-content").first()?.let { info ->
            name = info.getElementsByClass("hl-dc-title hl-data-menu").first()?.text() ?: ""
            desc = info.getElementsByClass("hl-col-xs-12 blurb").first()?.ownText() ?: ""
        }
        document.getElementsByClass("hl-item-thumb hl-lazy")
            .first()?.let {
                cover = it.attr("data-original")
            }

        val playList =
            document.getElementsByClass("hl-plays-from hl-tabs swiper-wrapper clearfix").first()
                ?.children()?.map {
                    it.attr("alt")
                } ?: emptyList()

        Log.d("videoDetail", "name=$name desc=$desc cover=$cover")

        return VideoDetailInfo(name, desc, 0F, Const.url(cover), "",
            document.getElementsByAttributeValueContaining("class", "hl-tabs-box hl-fadeIn")
                .mapIndexed { index, video ->
                    VideoPlayListInfo(
                        playList.getOrElse(index) { "播放列表${index + 1}" },
                        video.getElementsByTag("a").map {
                            VideoPlayInfo(
                                it.text(),
                                it.attr("href")
                            )
                        }.filter { !it.name.contains("全部") })
                })
    }

}