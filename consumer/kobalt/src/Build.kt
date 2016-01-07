import com.beust.kobalt.*
import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.plugin.kotlin.*
import com.github.bmsantos.cola.kobalt.plugin.colac

val repos = repos("https://dl.bintray.com/bmsantos/maven/")
val plugins = plugins("com.github.bmsantos:cola-kobalt-plugin:0.5.0")

val kotlinProject = kotlinProject {

    name = "konsumer"
    group = "com.github.bmsantos"
    artifactId = name
    version = "0.1"

    sourceDirectories {
        path("src/main/kotlin")
        path("src/main/resources")
    }

    sourceDirectoriesTest {
        path("src/test/kotlin")
        path("src/test/resources")
    }

    dependenciesTest {
        compile("junit:junit:4.11")
        compile("com.github.bmsantos:cola-tests:0.5.0")
        compile("org.slf4j:slf4j-simple:1.7.7")
        compile("org.assertj:assertj-core:2.3.0")
    }

    colac {
        includes("**/*Test.class")
    }

    test {
        //jvmArgs("-javaagent:" + homeDir(".m2/repository/com/github/bmsantos/cola-tests/0.5.0/cola-tests-0.5.0.jar"))
        excludes("**/BaseColaTest.class")
    }
}