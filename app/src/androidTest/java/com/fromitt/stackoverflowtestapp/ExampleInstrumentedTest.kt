package com.fromitt.stackoverflowtestapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fromitt.stackoverflowtestapp.network.WikipediaResponse
import com.fromitt.stackoverflowtestapp.utils.loadJSONFromAssets
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun gson_parser_test() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val rawJson = appContext.loadJSONFromAssets("typical_response.json")
        val type = object : TypeToken<WikipediaResponse>() {}.type
        val response = Gson().fromJson<WikipediaResponse>(
            rawJson,
            type
        )

        assertEquals(response?.query?.pages?.entries?.first()?.value?.pageid, 52669045)
        assertEquals(response?.query?.pages?.entries?.first()?.value?.title, "Badflower")
        assertEquals(response?.query?.pages?.entries?.first()?.value?.pageimage, "Badflower_live_at_The_Sinclair_in_Cambridge,_MA_2016.jpg")
    }
}