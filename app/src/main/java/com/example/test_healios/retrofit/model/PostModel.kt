package com.example.test_healios.retrofit.model

import io.realm.RealmObject

open class SinglePost(
    var userId: Int? = null,
    var id: Int? = null,
    var title: String? = null,
    var body: String? = null
) : RealmObject()