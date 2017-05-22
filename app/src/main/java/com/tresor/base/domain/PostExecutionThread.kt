package com.tresor.base.domain

import io.reactivex.Scheduler

/**
 * @author sebastianuskh on 5/22/17.
 */

interface PostExecutionThread {
    val scheduler: io.reactivex.Scheduler
}