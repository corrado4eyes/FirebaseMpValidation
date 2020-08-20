import dev.gitlive.firebase.firestore.FirebaseFirestore

actual fun platformName(): String = "Android 🤖"
actual val FirebaseFirestore.name: String? get() = android.app.name