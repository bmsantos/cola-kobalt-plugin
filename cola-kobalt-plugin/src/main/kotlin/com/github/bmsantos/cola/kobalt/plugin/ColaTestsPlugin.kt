package com.github.bmsantos.cola.kobalt.plugin

import com.beust.kobalt.TaskResult
import com.beust.kobalt.api.BasePlugin
import com.beust.kobalt.api.KobaltContext
import com.beust.kobalt.api.Project
import com.beust.kobalt.api.annotation.Task
import com.beust.kobalt.misc.KFiles.Companion.CLASSES_DIR
import com.beust.kobalt.misc.KFiles.Companion.TEST_CLASSES_DIR
import com.beust.kobalt.misc.log
import com.github.bmsantos.core.cola.main.ColaMain
import java.io.File
import java.io.File.separator
import java.util.*
import kotlin.collections.*

public class ColaTestsPlugin : BasePlugin() {
    override val name: String = "cola-kobalt-plugin"

    override fun apply(project: Project, context: KobaltContext) {
        super.apply(project, context)
    }

    @Task(name = "colac", description = "Instrument COLA Tests", alwaysRunAfter = arrayOf("compileTest"))
    fun colac(project: Project): TaskResult {

        var classpathElements = ArrayList<String>();

        val binDir = File(project.directory + separator + project.buildDirectory + separator + CLASSES_DIR).absolutePath
        classpathElements.add(binDir)
        log(2, "*** colac bin dir: $binDir")

        val targetDir = File(project.directory + separator + project.buildDirectory + separator + TEST_CLASSES_DIR).absolutePath
        classpathElements.add(targetDir)
        log(2, "*** colac target dir: $targetDir")

        val dependencies = project.compileDependencies +
                project.compileProvidedDependencies +
                project.compileRuntimeDependencies +
                project.testDependencies +
                project.testProvidedDependencies

        classpathElements.addAll(dependencies.map { it.jarFile.get().absolutePath }.toArrayList())

        log(2, "*** Classpath elements: " + classpathElements.reduce { l, r -> l + ", " + r })

        project.testConfigs.forEach {
            val provider = KobaltColaProvider(targetDir + separator, classpathElements, it.testIncludes, it.testExcludes)
            ColaMain().execute(provider)
        }

        return TaskResult()
    }
}