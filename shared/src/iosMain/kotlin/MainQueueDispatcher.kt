import kotlinx.cinterop.staticCFunction
import kotlinx.coroutines.*
import platform.Foundation.NSOperationQueue
import platform.Foundation.NSThread
import platform.darwin.*
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
internal class NsQueueDispatcher(private val dispatchQueue: dispatch_queue_t) : CoroutineDispatcher(), Delay {

    override fun isDispatchNeeded(context: CoroutineContext) =  !NSThread.currentThread().isMainThread

    // Dispatch block on given queue
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatchQueue) {
            block.run()
        }
    }

    // Support Delay
    override fun scheduleResumeAfterDelay(timeMillis: Long, continuation: CancellableContinuation<Unit>) {
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, timeMillis * 1_000_000), dispatchQueue) {
            with(continuation) {
                resumeUndispatched(Unit)
            }
        }
    }
}

@InternalCoroutinesApi
val MainQueueDispatcher: CoroutineDispatcher = NsQueueDispatcher(dispatch_get_main_queue())