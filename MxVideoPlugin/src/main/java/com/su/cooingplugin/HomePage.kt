package com.su.cooingplugin

import android.util.Log
import com.su.basevideopluginframework.framework.HomeFramework
import com.su.basevideopluginframework.framework.data.VideoClassifyInfo
import com.su.basevideopluginframework.framework.data.VideoInfo
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element


/**
 *
 * Created by Ryens.
 * https://github.com/RyensX
 */
object HomePage : HomeFramework() {

    override fun classifyList(doc: Document): List<VideoClassifyInfo> {
        val url = doc.baseUri()
        Log.d("获取首页分类", url)
        val infos = mutableListOf<VideoClassifyInfo>()
        doc.getElementsByClass("container")
            .forEach { element ->
                //推荐类型
                val name =
                    element.getElementsByClass("hl-rb-title").first()?.text()
                if (!name.isNullOrBlank()) {
                    Log.d("获取首页分类", "类型=$name")
                    VideoClassifyInfo(
                        name,
                        "",
                        element.getElementsByClass("hl-item-thumb hl-lazy")
                            .map { element ->
                                val vName = element.attr("title")
                                val vCover = element.attr("data-original")
                                val url = element.attr("href")
                                val ep = (element.nextSibling() as? Element)?.text() ?: ""
                                Log.d("获取首页分类", "作品: name=$vName cover=$vCover url=$url")
                                VideoInfo(vName, vCover, url, ep)
                            }).also {
                        infos.add(it)
                    }
                }
            }
        return infos
    }
}