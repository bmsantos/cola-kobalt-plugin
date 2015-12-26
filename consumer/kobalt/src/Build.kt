import com.beust.kobalt.*
import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.plugin.java.*

val repos = repos()


val p = javaProject {

    name = "konsumer"
    group = "com.github.bmsantos"
    artifactId = name
    version = "0.1"

    sourceDirectories {
        path("src/main/java")
        path("src/main/resources")
    }

    sourceDirectoriesTest {
        path("src/test/java")
        path("src/test/resources")
    }

    dependencies {
//        compile("com.beust:jcommander:1.48")
    }

    dependenciesTest {
        compile("junit:junit:4.11")
        compile("com.github.bmsantos:cola-tests:0.5.0")
    }

    assemble {
        jar {
        }
    }

    test {
        jvmArgs("-javaagent:" + homeDir(".m2/repository/com/github/bmsantos/cola-tests/0.5.0/cola-tests-0.5.0.jar"))
    }
}