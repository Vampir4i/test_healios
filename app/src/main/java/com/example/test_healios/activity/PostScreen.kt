package com.example.test_healios.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import com.example.test_healios.R
import com.example.test_healios.controllers.ControllerDB
import com.example.test_healios.retrofit.model.SingleComment
import com.example.test_healios.retrofit.model.SinglePost
import com.example.test_healios.retrofit.model.SingleUser
import org.w3c.dom.Text

class PostScreen : AppCompatActivity() {
    lateinit var singlePost: SinglePost
    var singleUser: SingleUser? = null
    lateinit var allComments: List<SingleComment>

    lateinit var commentsList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_screen)

        val id = intent.getIntExtra("id", 1)
        singlePost = ControllerDB.getSinglePost(id)!!
        singleUser = ControllerDB.getSingleUser(singlePost.userId!!)
        allComments = ControllerDB.getPostsComments(singlePost.id!!)

        findViewById<TextView>(R.id.postTitle).text = singlePost.title
        findViewById<TextView>(R.id.postBody).text = singlePost.body
        findViewById<TextView>(R.id.postUser).text = singleUser?.name
        commentsList = findViewById(R.id.commentsList)

        setAdapter()
    }

    fun setAdapter() {
        val NAME_TAG = "name"
        val BODY_TAG = "body"
        val list: MutableList<MutableMap<String, String>> =
            mutableListOf<MutableMap<String, String>>()
        allComments.forEach {
            val map: MutableMap<String, String> =
                mutableMapOf(NAME_TAG to it.name!!, BODY_TAG to it.body!!)
            list.add(map)
        }

        val sAdapter = SimpleAdapter(
            this,
            list,
            android.R.layout.simple_list_item_2,
            arrayOf(NAME_TAG, BODY_TAG),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        commentsList.adapter = sAdapter
    }
}