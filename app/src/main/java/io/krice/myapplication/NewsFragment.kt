package io.krice.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*


class NewsFragment: Fragment() {

    private var recyclerView: RecyclerView? = null
    private var mAdapter: RecyclerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)

        val mLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.layoutManager = mLayoutManager

        val database = FirebaseDatabase.getInstance()

        val newsList: MutableList<News> = mutableListOf()

        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                newsList.clear()
                Log.d("news", dataSnapshot.toString())
                dataSnapshot.children.mapNotNullTo(newsList) { it.getValue<News>(News::class.java) }
                mAdapter?.updateContent(newsList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }

        mAdapter = RecyclerAdapter()
        recyclerView?.adapter = mAdapter
        database.reference.child("news").addValueEventListener(listener)

        return view
    }

    companion object {
        fun newIntance() : NewsFragment {
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