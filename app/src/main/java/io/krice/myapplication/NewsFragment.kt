package io.krice.myapplication

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class NewsFragment: Fragment() {

    private var recyclerView: RecyclerView? = null
    private var mAdapter: RecyclerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)

        val mLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL , false)
        recyclerView?.layoutManager = mLayoutManager

        NewsLiveData().observe(this, Observer<MutableList<News>> { it ->
            if (it != null) {
                mAdapter?.updateContent(it)
            }
        })


        mAdapter = RecyclerAdapter()
        recyclerView?.adapter = mAdapter

        return view
    }

    companion object {
        fun newInstance() : NewsFragment {
            return NewsFragment()
        }
    }
}

open class News {
    lateinit var author: String
    lateinit var content : String
    lateinit var summary : String
    lateinit var date: String
    lateinit var picture: String
    lateinit var title: String

    fun News(): News { return this }

    fun News(author: String, content: String, summary: String, title: String, picture: String): News {
        this.author = author
        this.content = content
        this.summary = summary
        this.title = title
        this.picture = picture
        return this
    }

    override fun toString(): String {
        return "$title by $author the $date"
    }
}