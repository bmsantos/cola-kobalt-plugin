import com.beust.kobalt.*
import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.plugin.java.*
import com.beust.kobalt.plugin.kotlin.kotlinProject
import com.beust.kobalt.plugin.publish.jcenter

val repos = repos()

val colaTestsPlugin = kotlinProject {

    name = "cola-kobalt-plugin"
    group = "com.github.bmsantos"
    artifactId = name
    version = "0.5.0"

    sourceDirectories {
        path("src/main/kotlin")
        path("src/main/resources")
    }

    sourceDirectoriesTest {
        path("src/test/kotlin")
        path("src/test/resources")
    }

    dependencies {
        provided("com.beust:kobalt:0.384")
        compile("com.github.bmsantos:cola-tests:0.5.0")
        compile("org.slf4j:slf4j-simple:1.7.7")
    }

    dependenciesTest {
    }

    assemble {
        jar {
            fatJar = true
        }
    }

    jcenter {
        sign = false
        publish = true
    }
}
