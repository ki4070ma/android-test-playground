package amori.rssapp

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.IllegalArgumentException
import org.assertj.core.api.Assertions.*
import org.assertj.core.api.SoftAssertions

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
        assertThat(target.isValid("foo")).isTrue()
    }

    @Test
    fun isValid_givenAlphabetic_returnsTrue() {
        assertThat(target.isValid("abc")).isTrue()
    }

    @Test
    fun isValid_givenNumeric_returnsTrue() {
        assertThat(target.isValid("123")).isTrue()
    }

    @Test
    fun isValid_givenAlphaNumeric_returnsTrue() {
        assertThat(target.isValid("abc123")).isTrue()
    }

    @Test
    fun isValid_givenLessThan3_returnsFalse() {
        assertThat(target.isValid("ab")).isFalse()
    }

    @Test
    fun isVaid_givenNonAlphabetNumeric_returnsFalse() {
        assertThat(target.isValid("abc@")).isFalse()
    }

    @Test(expected = IllegalArgumentException::class)
    fun isValid_givenNull_throwsIllegalArgumentException() {
        target.isValid(null)
    }

    @Test
    fun testAssertionJ_String() {
        SoftAssertions().apply {
            assertThat("TOKYO")
                .`as`("TEXT CHECK TOKYO")
                .isEqualTo("TOKYO")
//                .isEqualTo("HOKKAIDO")
                .isEqualToIgnoringCase("tokyo")
                .isNotEqualTo("KYOTO")
                .isNotBlank()
                .startsWith("TO")
                .endsWith("YO")
                .contains("OKY")
                .matches("[A-Z]{5}")
                .isInstanceOf(String::class.java)
        }.assertAll()
    }

    @Test
    fun testAssertionJ_Numeric() {
        assertThat(3.14159)
            .isNotZero()
            .isNotNegative()
            .isGreaterThan(3.0)
            .isLessThanOrEqualTo(4.0)
            .isBetween(3.0, 3.2)
            .isCloseTo(Math.PI, within(0.001))
    }
}