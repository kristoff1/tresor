package com.tresor.login.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.tresor.login.data.exception.FirebaseHandlerException
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author sebastianuskh on 5/22/17.
 */
class FirebaseHandler @Inject constructor(){

    var auth = FirebaseAuth.getInstance()

    fun login(email: String, password: String): Observable<FirebaseUser> =
            Observable.create(
                    { e ->
                        auth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    if (task.isComplete){
                                        e.onNext(auth.currentUser)
                                        e.onComplete()
                                    } else {
                                        e.onError(FirebaseHandlerException("Failed to Log in"))
                                    }
                                }
                                .addOnFailureListener {
                                    e.onError(FirebaseHandlerException("Failed to log in"))
                                }
                    }
            )


}