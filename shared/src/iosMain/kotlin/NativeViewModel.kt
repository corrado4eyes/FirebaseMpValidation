import kotlinx.coroutines.*
import kotlinx.serialization.ImplicitReflectionSerializer
@InternalCoroutinesApi
@ImplicitReflectionSerializer
class NativeViewModel(
    private val onMeetingDataReceived: (MeetingData) -> Unit
) : CoroutineScope by MultiplatformMainScope() {

    private val firestoreRepository = FirestoreRepository(Unit)


    fun getDocument(documentPath: String) = launch {
        onMeetingDataReceived(firestoreRepository.getDocument(documentPath))
    }

}
