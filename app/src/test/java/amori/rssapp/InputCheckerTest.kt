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
    fun isValid() {
    }

    @Test
    fun testMachers() {
        assertThat(1 + 1, `is`(2))
        assertThat(100, greaterThan(50))
        assertThat(listOf("for", "bar", "baz"), hasItem("bar"))
    }
}