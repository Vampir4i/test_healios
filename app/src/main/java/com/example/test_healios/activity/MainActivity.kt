package com.example.test_healios.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.test_healios.R
import com.example.test_healios.controllers.ControllerDB
import com.example.test_healios.retrofit.Endpoints
import com.example.test_healios.retrofit.RetrofitClient
import com.example.test_healios.retrofit.model.SingleComment
import com.example.test_healios.retrofit.model.SinglePost
import com.example.test_healios.retrofit.model.SingleUser

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var allPosts: List<SinglePost>
    lateinit var client: Endpoints

    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ControllerDB.deleteData()

        listView = findViewById(R.id.postsList)

        loadAllData()
    }

    fun getAllPosts() {
        allPosts = ControllerDB.getPostsList()
    }

    fun setAdapter() {
        val TITLE_TAG = "title"
        val BODY_TAG = "body"
        val list: MutableList<MutableMap<String, String>> =
            mutableListOf<MutableMap<String, String>>()
        allPosts.forEach {
            val map: MutableMap<String, String> =
                mutableMapOf(TITLE_TAG to it.title!!, BODY_TAG to it.body!!)
            list.add(map)
        }

        val sAdapter = SimpleAdapter(
            this,
            list,
            android.R.layout.simple_list_item_2,
            arrayOf(TITLE_TAG, BODY_TAG),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        listView.adapter = sAdapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long ->
            val intent = Intent(this, PostScreen::class.java).apply {
                putExtra("id", allPosts[p2].id)
            }
            startActivity(intent)
        }
    }

    fun loadAllData() {
        client = RetrofitClient.client
        client.getPostsList().enqueue(object : Callback<List<SinglePost>> {
            override fun onResponse(
                call: Call<List<SinglePost>>,
                response: Response<List<SinglePost>>
            ) {
                val posts = response.body() ?: listOf()
                ControllerDB.addPostsList(posts)
                getAllPosts()
                setAdapter()
            }

            override fun onFailure(call: Call<List<SinglePost>>, t: Throwable) {
                Log.d("myLog", t.message)
                Toast.makeText(this@MainActivity, "Error posts load", Toast.LENGTH_SHORT).show()
            }

        })

        client.getCommentsList().enqueue(object : Callback<List<SingleComment>> {
            override fun onResponse(
                call: Call<List<SingleComment>>,
                response: Response<List<SingleComment>>
            ) {
                val comments = response.body() ?: listOf()
                ControllerDB.addCommentsList(comments)
            }

            override fun onFailure(call: Call<List<SingleComment>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error comments load", Toast.LENGTH_SHORT).show()
            }

        })

        client.getUsersList().enqueue(object : Callback<List<SingleUser>> {
            override fun onResponse(
                call: Call<List<SingleUser>>,
                response: Response<List<SingleUser>>
            ) {
                val users = response.body() ?: listOf()
                ControllerDB.addUsersList(users)
            }

            override fun onFailure(call: Call<List<SingleUser>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error users load", Toast.LENGTH_SHORT).show()
            }

        })
    }
}