package com.github.bmsantos.cola.kobalt.plugin

import com.beust.kobalt.api.Kobalt.Companion.findPlugin
import com.beust.kobalt.api.Project
import com.beust.kobalt.api.annotation.Directive
import kotlin.collections.arrayListOf

class ColacOptions() {
    public val colacIncludes = arrayListOf<String>()
    public val colacExcludes = arrayListOf<String>()

    @Directive
    fun includes(vararg arg: String) = colacIncludes.apply {
        clear()
        addAll(arg)
    }

    @Directive
    fun excludes(vararg arg: String) = colacExcludes.apply {
        clear()
        addAll(arg)
    }
}

@Directive
public fun Project.colac(init: ColacOptions.() -> Unit) = ColacOptions().apply {
    init()
    (findPlugin("cola-kobalt-plugin") as ColaTestsPlugin).options = this
}
