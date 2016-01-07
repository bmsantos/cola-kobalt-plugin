package org.github.bmsantos.kobalt.cola.tests

import com.github.bmsantos.core.cola.story.annotations.Assigned
import com.github.bmsantos.core.cola.story.annotations.Given
import com.github.bmsantos.core.cola.story.annotations.Then
import com.github.bmsantos.core.cola.story.annotations.When
import com.github.bmsantos.kobalt.cola.tests.Person
import org.assertj.core.api.Assertions.assertThat
import kotlin.collections.forEach
import kotlin.text.split

class KotlinColaTest : BaseColaTest() {

    private val stories: String = """
       Feature: Introduce drinking

        Scenario: Should be happy
          Given a beer to enjoy
          When mixed with cachaca gin rum vodka sake alcoholic drinks
          Then one will be happy!

        Scenario: Should be sad
          Given a juice to enjoy
          When mixed with juice water kefir other beverages
          Then one will be sad!
    """

    private val dude = Person();

    @Given("a <drink> to enjoy")
    public fun givenADrink(@Assigned("drink") drink: String) = dude.drink(drink)

    @When("""mixed with <drinks> (alcoholic drinks|beverages)""")
    public fun whenMixed(@Assigned("drinks") beverages: String) = beverages.split(" ").forEach { dude.drink(it) }

    @Then("one will be happy!")
    public fun thenWillBeHappy() = assertThat(dude.totalAlcoholVolume).isGreaterThan(25.0)

    @Then("one will be sad!")
    public fun thenWillBeSad() = assertThat(dude.totalAlcoholVolume).isLessThan(25.0)
}