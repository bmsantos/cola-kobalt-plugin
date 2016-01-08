package com.github.bmsantos.cola.kobalt.plugin

import com.beust.kobalt.TaskResult
import com.beust.kobalt.api.BasePlugin
import com.beust.kobalt.api.KobaltContext
import com.beust.kobalt.api.Project
import com.beust.kobalt.api.annotation.Task
import com.beust.kobalt.misc.KFiles.Companion.CLASSES_DIR
import com.beust.kobalt.misc.KFiles.Companion.TEST_CLASSES_DIR
import com.github.bmsantos.core.cola.exceptions.ColaExecutionException
import com.github.bmsantos.core.cola.main.ColaMain
import java.io.File
import java.io.File.separator
import kotlin.collections.*

public class ColaTestsPlugin : BasePlugin() {
    companion object {
        val PLUGIN_NAME = "cola-kobalt-plugin"
    }

    override val name: String = PLUGIN_NAME

    public val options = ColacConfig()

    @Task(name = "colac", description = "Instrument COLA Tests", alwaysRunAfter = arrayOf("compileTest"))
    fun colac(project: Project): TaskResult {

        val targetDir = generateBuildPath(project, TEST_CLASSES_DIR)
        val dependencies = project.compileDependencies +
                project.compileProvidedDependencies +
                project.compileRuntimeDependencies +
                project.testDependencies +
                project.testProvidedDependencies

        val classpath =
                listOf(targetDir) +
                generateBuildPath(project, CLASSES_DIR) +
                dependencies.map { it.jarFile.get().absolutePath }.toArrayList()

        var error: String? = null
        project.testConfigs.forEach {
            val provider = KobaltColaProvider(targetDir, classpath, options.colacIncludes, options.colacExcludes)
            try {
                ColaMain().execute(provider)
            } catch (e: ColaExecutionException) {
                error = e.message
            }
        }

        return TaskResult(error == null, error)
    }

    private fun generateBuildPath(project: Project, postfix: String): String =
            File(project.directory + separator + project.buildDirectory + separator + postfix).absolutePath + separator
}