package com.su.cooingplugin

import com.su.basevideopluginframework.framework.VideoPlayFramework
import org.jsoup.nodes.Document


/**
 *
 * Created by Ryens.
 * https://github.com/RyensX
 */
object PlayPage : VideoPlayFramework() {
    override suspend fun getVideoEpisodeName(doc: Document): String {
        return doc.getElementsByClass("hl-infos-title")
            .first()?.getElementsByTag("em")?.text() ?: ""
    }
}