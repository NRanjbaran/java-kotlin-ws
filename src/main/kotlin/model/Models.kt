import model.dto.BookDto
import java.time.Instant

data class WriterEvent(val type: Type, val data: Writer) {
    enum class Type {
        ADD,
        DELETE
    }
}

data class BookEvent(val data: Book)

data class Writer(val code: CODE, val description: String)
typealias CODE = String

data class Book(val code: CODE, val price: Price)
typealias Price = Double


interface PublishedInfoManager {
    fun getWriterInfo(writerCode: String): List<WriterInfo>
}

data class WriterInfo(
    val code: String,
    val firstName: String,
    val lastName: String,
    val books: MutableSet<BookDto>
)

data class BookDto(
    val name: String,
    val price: Double,
    val publishedDateTime: Instant,
    val description: String
)