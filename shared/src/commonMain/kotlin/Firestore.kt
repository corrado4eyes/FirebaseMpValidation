import com.splendo.kaluga.logging.debug
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseApp
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.initialize
import kotlinx.serialization.ImplicitReflectionSerializer

@ImplicitReflectionSerializer
class FirestoreRepository(context: Any?) {

    private val firebase = Firebase
    private val firebaseApp: FirebaseApp?

    init {
        firebaseApp = firebase.initialize(context)
    }

    private val firestore by lazy { firebase.firestore }

    suspend fun getDocument(documentPath: String): MeetingData {
        debug("FirestoreRepository", "firebaseApp: ${firebaseApp?.name}")
        debug("FirestoreRepository", "firestoreApp: ${firestore.name}")
        return firestore.document(documentPath).get().data()
//        return MeetingData(id = "id")
    }
}

expect val FirebaseFirestore.name: String?