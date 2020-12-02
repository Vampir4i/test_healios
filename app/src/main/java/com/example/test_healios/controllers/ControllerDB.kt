package com.example.test_healios.controllers

import com.example.test_healios.retrofit.model.SingleComment
import com.example.test_healios.retrofit.model.SinglePost
import com.example.test_healios.retrofit.model.SingleUser
import io.realm.Realm

object ControllerDB {
    val realm: Realm = Realm.getDefaultInstance()

    fun addUsersList(usersList: List<SingleUser>) {
        realm.executeTransaction {
            for (user in usersList) it.copyToRealm(user)
        }
    }

    fun addPostsList(postsList: List<SinglePost>) {
        realm.executeTransaction {
            for (post in postsList) it.copyToRealm(post)
        }
    }

    fun addCommentsList(commentsList: List<SingleComment>) {
        realm.executeTransaction {
            for (comment in commentsList) it.copyToRealm(comment)
        }
    }

    fun getPostsList(): List<SinglePost> {
        return realm.where(SinglePost::class.java).findAll()
    }

    fun getSinglePost(id: Int): SinglePost? {
        return realm.where(SinglePost::class.java).equalTo("id", id).findFirst()
    }

    fun getSingleUser(userId: Int): SingleUser? {
        return realm.where(SingleUser::class.java).equalTo("id", userId).findFirst()
    }

    fun getPostsComments(postId: Int): List<SingleComment> {
        return realm.where(SingleComment::class.java).equalTo("postId", postId).findAll()
    }

    fun deleteData() {
        realm.executeTransaction {
            it.delete(SingleUser::class.java)
            it.delete(SinglePost::class.java)
            it.delete(SingleComment::class.java)
        }
    }
}