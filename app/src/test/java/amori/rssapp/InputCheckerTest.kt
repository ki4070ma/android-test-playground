package amori.rssapp

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.IllegalArgumentException

@RunWith(JUnit4::class)
class InputCheckerTest {

    lateinit var target: InputChecker

    @Before
    fun setUp() {
        target = InputChecker()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun isValid_given3_returnTrue() {
        assertThat(target.isValid("foo"), `is`(true))
    }

    @Test
    fun isValid_givenAlphabetic_returnsTrue() {
        assertThat(target.isValid("abc"), `is`(true))
    }

    @Test
    fun isValid_givenNumeric_returnsTrue() {
        assertThat(target.isValid("123"), `is`(true))
    }

    @Test
    fun isValid_givenAlphaNumeric_returnsTrue() {
        assertThat(target.isValid("abc123"), `is`(true))
    }

    @Test
    fun isValid_givenLessThan3_returnsFalse() {
        assertThat(target.isValid("ab"), `is`(false))
    }

    @Test
    fun isVaid_givenNonAlphabetNumeric_returnsFalse() {
        assertThat(target.isValid("abc@"), `is`(false))
    }

    @Test(expected = IllegalArgumentException::class)
    fun isValid_givenNull_throwsIllegalArgumentException() {
        target.isValid(null)
    }

    @Test
    fun testMachers() {
        assertThat(1 + 1, `is`(2))
        assertThat(100, greaterThan(50))
        assertThat(listOf("for", "bar", "baz"), hasItem("bar"))
    }
}