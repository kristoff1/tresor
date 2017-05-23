package com.tresor.login.domain.interactor

import com.tresor.base.domain.PostExecutionThread
import com.tresor.base.domain.ThreadExecutor
import com.tresor.base.domain.UseCase
import com.tresor.login.domain.model.UserLoginDomainModel
import com.tresor.login.domain.repository.UserRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author sebastianuskh on 5/22/17.
 */

class Login @Inject constructor (val userRepository: UserRepository, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) :
        UseCase<Login.Param, UserLoginDomainModel>(threadExecutor, postExecutionThread) {

    override fun getObservable(param: Param): Observable<UserLoginDomainModel> = userRepository.login(param.email, param.password)

    data class Param(val email: String, val password: String)

}
