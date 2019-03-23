package amori.rssapp

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class InputCheckerTest {

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {

    }

    @Test
    fun isValid_given3_returnTrue() {
        val target = InputChecker()
        assertThat(target.isValid("foo"), `is`(true))
    }

    @Test
    fun isValid_givenAlphaNumeric_returnsTrue() {
        val target = InputChecker()
        assertThat(target.isValid("abc123"), `is`(true))
    }

    @Test
    fun isValid_givenLessThan3_returnsFalse() {
        val target = InputChecker()
        assertThat(target.isValid("ab"), `is`(false))
    }

    @Test
    fun isVaid_givenNonAlphabetNumeric_returnsFalse() {
        val target = InputChecker()
        assertThat(target.isValid("abc@"), `is`(false))
    }

    @Test
    fun testMachers() {
        assertThat(1 + 1, `is`(2))
        assertThat(100, greaterThan(50))
        assertThat(listOf("for", "bar", "baz"), hasItem("bar"))
    }
}