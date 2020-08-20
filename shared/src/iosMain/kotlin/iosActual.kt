import dev.gitlive.firebase.firestore.FirebaseFirestore

actual fun platformName(): String = "iOS 🍎"
actual val FirebaseFirestore.name: String? get() = ios.app.name
