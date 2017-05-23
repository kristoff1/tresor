package com.tresor.base.data

import com.tresor.base.domain.PostExecutionThread
import com.tresor.base.domain.ThreadExecutor
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.*

/**
 * @author sebastianuskh on 5/22/17.
 */
class AndroidPostExecutionThread (
        override val scheduler: Scheduler = AndroidSchedulers.mainThread()
) : PostExecutionThread


class JobExecutor : ThreadExecutor {
    val threadPoolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(
            3,
            5,
            10,
            TimeUnit.SECONDS,
            LinkedBlockingQueue<Runnable>(),
            JobThreadFactory())

    override fun execute(command: Runnable?) {
        threadPoolExecutor.execute(command)
    }
}

class JobThreadFactory(var counter: Int = 0) : ThreadFactory {
    override fun newThread(r: Runnable?): Thread = Thread(r, "android_" + counter++)
}