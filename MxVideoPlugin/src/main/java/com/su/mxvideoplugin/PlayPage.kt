package com.su.mxvideoplugin

import com.su.basevideopluginframework.framework.VideoPlayFramework
import com.su.mediabox.pluginapi.IPluginFactory
import org.jsoup.nodes.Document


/**
 *
 * Created by Ryens.
 * https://github.com/RyensX
 */
@IPluginFactory.SingletonComponent
class PlayPage : VideoPlayFramework() {
    override suspend fun getVideoEpisodeName(doc: Document): String {
        return doc.getElementsByClass("hl-infos-title")
            .first()?.getElementsByTag("em")?.text() ?: ""
    }
}