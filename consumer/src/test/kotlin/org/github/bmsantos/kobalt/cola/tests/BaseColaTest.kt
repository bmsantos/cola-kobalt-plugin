package org.github.bmsantos.kobalt.cola.tests

import com.github.bmsantos.core.cola.story.annotations.IdeEnabler
import org.junit.Test

open class BaseColaTest() {
    @IdeEnabler
    @Test
    public fun iWillBeRemoved() {
        println("This test should have not been executed")
    }
}
