package com.su.mxvideoplugin

import org.junit.Test


class MediaUpdateDataComponentTest {

    @Test
    fun testTagMatch() {
        Regex("(?<=更新至)(第?)(.*)").find("状态：更新至第010集")?.also {
            println("match=${it.groups.last()?.value}")
        }
    }

}