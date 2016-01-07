package com.github.bmsantos.cola.kobalt.plugin

import com.beust.kobalt.TaskResult
import com.beust.kobalt.api.BasePlugin
import com.beust.kobalt.api.KobaltContext
import com.beust.kobalt.api.Project
import com.beust.kobalt.api.annotation.Task
import com.beust.kobalt.misc.KFiles.Companion.CLASSES_DIR
import com.beust.kobalt.misc.KFiles.Companion.TEST_CLASSES_DIR
import com.github.bmsantos.core.cola.main.ColaMain
import java.io.File
import java.io.File.separator
import kotlin.collections.*

public class ColaTestsPlugin : BasePlugin() {
    override val name: String = "cola-kobalt-plugin"

    public var options: ColacOptions = ColacOptions()

    override fun apply(project: Project, context: KobaltContext) {
        super.apply(project, context)
    }

    @Task(name = "colac", description = "Instrument COLA Tests", alwaysRunAfter = arrayOf("compileTest"))
    fun colac(project: Project): TaskResult {

        val targetDir = generateBuldPath(project, TEST_CLASSES_DIR)
        val dependencies = project.compileDependencies +
                project.compileProvidedDependencies +
                project.compileRuntimeDependencies +
                project.testDependencies +
                project.testProvidedDependencies

        val classpath = arrayListOf<String>();
        classpath.add(targetDir)
        classpath.add(generateBuldPath(project, CLASSES_DIR))
        classpath.addAll(dependencies.map { it.jarFile.get().absolutePath }.toArrayList())

        project.testConfigs.forEach {
            val provider = KobaltColaProvider(targetDir, classpath, options.colacIncludes, options.colacExcludes)
            ColaMain().execute(provider)
        }

        return TaskResult()
    }

    private fun generateBuldPath(project: Project, postfix: String): String =
            File(project.directory + separator + project.buildDirectory + separator + postfix).absolutePath + separator
}