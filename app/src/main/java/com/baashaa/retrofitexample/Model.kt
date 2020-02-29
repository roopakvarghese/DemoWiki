package com.baashaa.retrofitexample


    data class Result(val query: Query)
    data class Query(val searchinfo : SearchInfo , val search :List<SearchModel>)
    data class SearchInfo(val totalhits: Int)
    data class Info(val searchList: List<SearchModel>)


