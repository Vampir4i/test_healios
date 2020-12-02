package com.example.test_healios.retrofit.model

import io.realm.RealmObject

open class SingleUser(
    var id: Int? = null,
    var name: String? = null,
    var username: String? = null,
    var email: String? = null,
    var address: UserAddress? = null,
    var phone: String? = null,
    var website: String? = null,
//    var company: UserCompany? = null
) : RealmObject()

open class UserAddress(
    var street: String? = null,
    var suite: String? = null,
    var city: String? = null,
    var zipcode: String? = null,
//    var geo: AddressGeo? = null
): RealmObject()

open class AddressGeo(
    var lat: String? = null,
    var lng: String? = null
): RealmObject()

open class UserCompany(
    var name: String? = null,
    var catchPhrase: String? = null,
    var bs: String? = null
): RealmObject()