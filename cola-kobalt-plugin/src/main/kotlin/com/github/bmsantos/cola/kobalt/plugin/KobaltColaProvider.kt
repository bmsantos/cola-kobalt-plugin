package com.github.bmsantos.cola.kobalt.plugin

import com.beust.kobalt.misc.log
import com.github.bmsantos.core.cola.provider.IColaProvider
import org.codehaus.plexus.util.DirectoryScanner
import java.io.File
import java.net.URL
import java.net.URLClassLoader
import java.util.*
import kotlin.collections.*

public class KobaltColaProvider(val targetDir: String, val classpathElements: List<String>,
                                val includes: List<String>, val excludes: List<String>) : IColaProvider {

    override fun getTargetDirectory(): String? {
        return targetDir
    }

    override fun getTargetClassLoader(): URLClassLoader? {
        val urls = ArrayList<URL>()

        classpathElements.forEach {
            log(2, "*** Classpath Element: " + File(it).toURI().toURL())
            urls.add(File(it).toURI().toURL())
        }

        return URLClassLoader(urls.toTypedArray(), KobaltColaProvider::class.java.classLoader)
    }

    override fun getTargetClasses(): MutableList<String>? {
        val resolvedIncludes = resolveIncludes()

        val scanner = DirectoryScanner()
        scanner.apply {
            setIncludes(resolvedIncludes.toTypedArray())
            setExcludes(excludes.toTypedArray())
            setBasedir(targetDir)
            setCaseSensitive(true)
            scan()
        }

        return scanner.includedFiles.toArrayList()
    }

    private fun resolveIncludes(): List<String> {
        return includes
    }
}