package com.fromitt.stackoverflowtestapp

import com.fromitt.stackoverflowtestapp.network.WikipediaResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader

class ExampleUnitTest {

    @Throws(IOException::class)
    fun readJsonFile(filename: String): String? {
        val br = BufferedReader(InputStreamReader(FileInputStream(ASSET_BASE_PATH + filename)))
        val sb = StringBuilder()
        var line: String = br.readLine()
        while (line != null) {
            sb.append(line)
            line = br.readLine()
        }
        return sb.toString()
    }

    @Test
    fun gson_parser_test() {
        val rawJson = readJsonFile("typical_response.json")
        val type = object : TypeToken<WikipediaResponse>() {}.type
        val response = Gson().fromJson<WikipediaResponse>(
            rawJson,
            type
        )

        assertEquals(response?.query?.pages?.get(0)?.pageid, 52669045)
    }

    companion object {
        private const val ASSET_BASE_PATH = "../app/src/main/assets/"
    }
}