package com.fromitt.stackoverflowtestapp.utils

import android.content.Context

/*
* @author Tkachov Vasyl
* @since 15.10.2020
*/
fun Context.loadJSONFromAssets(fileName: String): String {
    return applicationContext.assets.open(fileName).bufferedReader().use { reader ->
        reader.readText()
    }
}
