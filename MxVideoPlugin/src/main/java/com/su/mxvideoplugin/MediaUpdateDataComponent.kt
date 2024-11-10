package com.su.mxvideoplugin

import com.su.basevideopluginframework.framework.Const
import com.su.basevideopluginframework.util.JsoupUtil
import com.su.mediabox.pluginapi.IPluginFactory
import com.su.mediabox.pluginapi.components.IMediaUpdateDataComponent

@IPluginFactory.SingletonComponent
class MediaUpdateDataComponent : IMediaUpdateDataComponent {

    private val updateRegex = Regex("(?<=更新至)(.*)")

    override suspend fun getUpdateTag(detailUrl: String): String? {
        val doc = JsoupUtil.getDocument(Const.url(detailUrl))
        val sInfo = doc.getElementsByClass("hl-dc-content").first()
            ?.getElementsByClass("hl-text-conch")?.first()
            ?: return null
        return try {
            val rawText = sInfo.text()
            updateRegex.find(rawText)?.value ?: rawText
        } catch (_: Exception) {
            null
        }
    }

    override suspend fun enableUpdateCheck(updateTag: String?): Boolean {
        return super.enableUpdateCheck(updateTag) && updateTag?.run { !contains("HD") } ?: true
    }

}