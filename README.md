# cola-kobalt-plugin
COLA Test plugin for Kobalt build system

See **consumer** directory for a COLA Test Kobalt build example and execute the following command to execute it:

```bash
./kobaltw clean test
```


### Configuring COLA Tests Kobalt Plugin

```kotlin
val repos = repos("https://dl.bintray.com/bmsantos/maven/")
val plugins = plugins("com.github.bmsantos:cola-kobalt-plugin:0.5.0")

...

val kotlinProject = kotlinProject {

    ...

    dependenciesTest {
        compile("junit:junit:XXXX")
        compile("com.github.bmsantos:cola-tests:0.5.0")
        compile("org.slf4j:slf4j-simple:1.7.7")
    }

    ...

    colac {
        includes("**/*Test.class")
        excludes("**/BadTest.class")
    }

    ...
}

```

COLA Tests [Wiki](https://github.com/bmsantos/cola-tests/wiki)

Kobalt [site](http://beust.com/kobalt/home/index.html)

