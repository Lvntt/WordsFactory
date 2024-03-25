package dev.lantt.wordsfactory.dictionary.data.network

import dev.lantt.wordsfactory.core.Constants.EN_DICTIONARY_URL
import dev.lantt.wordsfactory.dictionary.data.model.DictionaryWordDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApiService {

    @GET("$EN_DICTIONARY_URL/{query}")
    suspend fun getDictionaryWord(
        @Path("query") query: String
    ): List<DictionaryWordDto>

}