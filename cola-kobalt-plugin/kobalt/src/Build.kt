import com.beust.kobalt.*
import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.plugin.java.*

val repos = repos()

val colaTestsPlugin = kotlinProject {

    name = "cola-kobalt-plugin"
    group = "com.github.bmsantos"
    artifactId = name
    version = "0.5.0-SNAPSHOT"

    sourceDirectories {
        path("src/main/kotlin")
        path("src/main/resources")
    }

    sourceDirectoriesTest {
        path("src/test/kotlin")
        path("src/test/resources")
    }

    dependencies {
        compile("com.beust:kobalt:0.385")
    }

    dependenciesTest {
    }

    assemble {
        jar {
        }
    }

    jcenter {
        publish = true
    }
}
