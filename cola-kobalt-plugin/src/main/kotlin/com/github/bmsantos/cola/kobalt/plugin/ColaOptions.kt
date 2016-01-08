package com.github.bmsantos.cola.kobalt.plugin

import com.beust.kobalt.api.Kobalt.Companion.findPlugin
import com.beust.kobalt.api.Project
import com.beust.kobalt.api.annotation.Directive

// I usually call these classes "Config". Just a convention, you don't have to follow it
class ColacConfig() {
    public val colacIncludes = arrayListOf<String>()
    public val colacExcludes = arrayListOf<String>()

    @Directive
    fun includes(vararg arg: String) = colacIncludes.apply {
        // Why clear()? Only necessary if this field contains default values, which it doesn't appear to
        clear()
        addAll(arg)
    }

    @Directive
    fun excludes(vararg arg: String) = colacExcludes.apply {
        // Ditto
        clear()
        addAll(arg)
    }
}

@Directive
public fun Project.colac(init: ColacOptions.() -> Unit) = ColacOptions().apply {
    init()
    (findPlugin(ColaTestsPlugin.PLUGIN_NAME) as ColaTestsPlugin).options = this
}
