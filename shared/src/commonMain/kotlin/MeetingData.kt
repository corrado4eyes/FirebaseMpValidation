import kotlinx.serialization.Serializable


@Serializable
data class MeetingData(
    val id: String,
    val password: String = ""
)