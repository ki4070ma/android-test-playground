package amori.rssapp

import android.text.TextUtils


class InputChecker {
    fun isValid(text: String): Boolean {
        if (TextUtils.isEmpty(text)) throw IllegalArgumentException("Cannot be null")
        return text.length >= 3 && text.matches(Regex("[a-zA-Z0-9]+"))
    }
}