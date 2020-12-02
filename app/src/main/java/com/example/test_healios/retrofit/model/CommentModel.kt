package com.example.test_healios.retrofit.model

import io.realm.RealmObject

open class SingleComment(
    var postId: Int? = null,
    var id: Int? = null,
    var name: String? = null,
    var email: String? = null,
    var body: String? = null
) : RealmObject()