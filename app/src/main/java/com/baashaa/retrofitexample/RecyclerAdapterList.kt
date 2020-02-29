package com.baashaa.retrofitexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_adapter.view.*

class RecyclerAdapterList : RecyclerView.Adapter<RecyclerAdapterList.ViewHolder>() {

    var totalvalues: List<SearchModel>?=null
    fun setData(totalhits: List<SearchModel>) {
        totalvalues=totalhits
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_adapter, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return totalvalues?.size!!
    }


    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.bindItems(totalvalues!![position])
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bindItems(item : SearchModel?) {
            val textViews=itemView.textView2 as TextView
            textViews.text=item?.title
        }



    }



}