package com.github.bmsantos.cola.kobalt.plugin

import com.beust.kobalt.api.BasePlugin
import com.beust.kobalt.api.KobaltContext
import com.beust.kobalt.api.Project

public class ColaTestsPlugin : BasePlugin() {
    override val name: String = "cola-kobalt-plugin"

    override fun apply(project: Project, context: KobaltContext) {
        super.apply(project, context)
        println("*** Applying plugin $name with project $project")
    }
}