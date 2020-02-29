package com.baashaa.retrofitexample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var disposable: Disposable? = null
    val wikiApiServe by lazy {
        WikiApiService.create()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loaddatas()
    }

    private fun loaddatas() {
        btnSearch.setOnClickListener {
            beginSearch(editTextSearch.text.toString())
        }

    }
    private fun beginSearch(srsearch: String) {
        disposable =
            wikiApiServe.hitCountCheck("query", "json", "search", srsearch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> textView.text="${result.query.searchinfo.totalhits} results  found"
                            var list=result.query.search
                          recyclerviewShow(list)
                    },
                    { error -> Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()}
                )
    }


    private fun recyclerviewShow(totalhits: List<SearchModel>) {
        rvList.layoutManager =LinearLayoutManager(this)
        val adapter = RecyclerAdapterList()
        adapter.setData(totalhits)
        rvList.adapter=adapter
    }
}
