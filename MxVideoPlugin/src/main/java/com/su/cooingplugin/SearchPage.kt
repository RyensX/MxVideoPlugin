package com.su.cooingplugin

import com.su.basevideopluginframework.framework.Const
import com.su.basevideopluginframework.framework.VideoSearchFramework
import com.su.basevideopluginframework.framework.data.VideoSearchInfo
import com.su.basevideopluginframework.framework.data.VideoTag
import org.jsoup.nodes.Document


/**
 *
 * Created by Ryens.
 * https://github.com/RyensX
 */
object SearchPage : VideoSearchFramework() {

    override val searchUrlTemplate: String = "https://www.mxdmp.com/search/%key----------%page---/"

    override suspend fun search(document: Document): List<VideoSearchInfo> {
        val searchResult = mutableListOf<VideoSearchInfo>()
        document.getElementsByClass("hl-list-item hl-col-xs-12").forEach { element ->
            var name = ""
            var desc = ""
            var cover = ""
            var url = ""
            element.getElementsByClass("hl-item-thumb hl-lazy").first()?.let {
                cover = it.attr("data-original")
                url = it.attr("href")
            }
            val tags = mutableListOf<VideoTag>()
            element.getElementsByClass("hl-item-content").first()?.let {
                name = it.getElementsByClass("hl-item-title hl-text-site hl-lc-2").first()?.text()
                    ?: name
                desc = it.getElementsByClass("hl-item-sub hl-text-muted hl-lc-2").first()?.text()
                    ?: desc

                val tmpTags =
                    it.getElementsByClass("hl-item-sub hl-lc-1").first()?.ownText()?.trim()
                        ?.split("·")
                tmpTags?.forEach {
                    val tmp = it.trim()
                    if (tmp.isNotBlank()) {
                        if (tmp.contains(" ")) {
                            tags.addAll(tmp.split(" ").map { VideoTag(it) })
                        } else {
                            tags.add(VideoTag(tmp))
                        }
                    }
                }
            }
            val ep = element.getElementsByClass("hl-lc-1 remarks").first()?.text() ?: ""

            searchResult.add(
                VideoSearchInfo(name, cover, Const.url(url), ep, desc, tags)
            )
        }
        return searchResult
    }
}