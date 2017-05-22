package com.tresor.login.data.entity.mapper

import com.google.firebase.auth.FirebaseUser
import com.tresor.login.domain.model.UserLoginDomainModel
import io.reactivex.functions.Function
import javax.inject.Inject

/**
 * @author sebastianuskh on 5/22/17.
 */
class FirebaseUserMapper @Inject constructor() : Function<FirebaseUser, UserLoginDomainModel>{
    override fun apply(t: FirebaseUser): UserLoginDomainModel = UserLoginDomainModel(t.uid)

}