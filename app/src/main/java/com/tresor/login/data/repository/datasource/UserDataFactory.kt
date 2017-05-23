package com.tresor.login.data.repository.datasource

import com.tresor.login.data.entity.mapper.FirebaseUserMapper
import com.tresor.login.data.firebase.FirebaseHandler
import javax.inject.Inject

/**
 * @author sebastianuskh on 5/22/17.
 */
class UserDataFactory @Inject constructor(val firebaseHandler: FirebaseHandler, val mapper: FirebaseUserMapper) {
    fun createFirebaseDataSource() = FirebaseUserDataSource(firebaseHandler, mapper)
}