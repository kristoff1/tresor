package com.tresor.login.data.repository.datasource

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.tresor.login.domain.model.UserLoginDomainModel

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.annotations.NonNull
import io.reactivex.functions.Function


/**
 * @author sebastianuskh on 5/22/17.
 */

class FirebaseJava : UserDataSource {

    internal var auth = FirebaseAuth.getInstance()

    override fun login(email: String, password: String): Observable<UserLoginDomainModel> {
        return Observable.create(ObservableOnSubscribe<FirebaseUser> { e ->
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isComplete) {
                            val user = auth.currentUser
                            e.onNext(user)
                            e.onComplete()
                        }
                    }
        }).map { null }
    }
}
