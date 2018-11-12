package io.krice.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

class NewsFragment: Fragment() {

    private var recyclerView: RecyclerView? = null
    private var mAdapter: RecyclerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)

        mAdapter = RecyclerAdapter()
        recyclerView?.adapter = mAdapter

        val mLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.layoutManager = mLayoutManager

        var newsList: List<News> = listOf(
                News(title = "MAJOR BREAKTHROUGH IN PHYSICS", author = "Sheldon Cooper", date = Date(12-11-2018)),
                News(title = "Is 'Are' the new 'is' and are 'Is' the new 'Are", author = "A 2 years old kid", date = Date(11-11-2018)),
                News(title = "'Japan and China don't exist", author = "Donald Trump", date = Date(10-11-2018)),
                News(title = "Purge the Heretics", author = "Some Imperium dude", date = Date(9-11-2018)),
                News(title = "Come, i have a 12 people cake for you", author = "My grandma", date = Date(8-11-2018))
        )

        for (news in newsList) {
            mAdapter!!.addData(news)
        }

        return view
    }

    companion object {
        fun newIntance() : NewsFragment {
            return NewsFragment()
        }
    }
}

open class News(title: String, author: String, date: Date) {

    private var author: String = author
    private var date: Date = date
    private var title: String = title

    override fun toString(): String {
        return "$title by $author the $date"
    }

    fun getTitle(): CharSequence? {
        return title
    }

    fun getDate(): Any {
        return date
    }

    fun getAuthor(): CharSequence? {
        return author
    }
}
