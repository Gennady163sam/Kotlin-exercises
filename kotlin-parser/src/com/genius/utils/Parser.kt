package com.genius.utils

import com.genius.model.AvitoCategory
import java.net.URL
import javax.naming.directory.SearchResult

class Parser(private val city: String, private val category: AvitoCategory, private val query: String) {
    fun search() : List<SearchResult> {
        val httpResponse = URL("https://www.avito.ru/${city}/${category.code}?q=${query}")
            .readText()
        // TODO: Create parsing from site
        return listOf()
    }
}