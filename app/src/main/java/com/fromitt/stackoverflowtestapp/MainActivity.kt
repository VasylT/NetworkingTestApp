package com.fromitt.stackoverflowtestapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.fromitt.stackoverflowtestapp.network.ArtistData
import com.fromitt.stackoverflowtestapp.network.WikiService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
* @author Tkachov Vasyl
* @since 13.10.2020
*/
class MainActivity : AppCompatActivity(), ListAdapter.Listener {

    var retrofit: Retrofit? = null
    var wikiService: WikiService? = null
    var adapter: ListAdapter? = null

    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initWikiService()
        initList()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelJob.cancel()
    }

    private fun initWikiService() {
        retrofit = Retrofit.Builder()
            .baseUrl("https://en.wikipedia.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        wikiService = retrofit?.create(WikiService::class.java)
    }

    private fun initList() {
        recycler_view.layoutManager = GridLayoutManager(this, 3)
        adapter = ListAdapter(this)
        adapter?.items = ArrayList()
        adapter?.listener = this
        recycler_view.adapter = adapter

        viewModelScope.launch {
            progress_view.visibility = View.VISIBLE
            val wikiPages = getWikiPages()
            adapter?.items = wikiPages
            progress_view.visibility = View.GONE
        }
    }

    private suspend fun getWikiPages(): ArrayList<Item> {
        val newItems = ArrayList<Item>()

        withContext(IO) {
            ArtistData.artists.map { artist ->
                async { wikiService?.getWikiData(artist) }
            }.awaitAll().forEach { response ->
                val pages = response?.body()?.query?.pages
                pages?.let {
                    for (page in pages) {
                        val value = page.value
                        val id = value.pageid?.toLong() ?: value.title.hashCode().toLong()
                        val title = value.title ?: "Unknown"
                        val url = value.thumbnail?.source
                        newItems.add(Item(id, title, url))
                    }
                }
            }
        }
        return newItems
    }

    override fun onItemClicked(item: Item) {
    }
}