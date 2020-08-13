import kotlinx.coroutines.*

@InternalCoroutinesApi
fun MultiplatformMainScope(): CoroutineScope {
    return CoroutineScope(SupervisorJob() + MainQueueDispatcher)
}