import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Http4kServer
import org.http4k.server.Netty
import org.http4k.server.asServer
import service.PublishedInfoManagerImpl

class Server(
    port: Int = 9000,
) {

    var service: PublishedInfoManager = PublishedInfoManagerImpl()


    private val routes = routes(
        "WriterInfos" bind Method.GET to { getWriterInfos(it) }
    )

    private val server: Http4kServer = routes.asServer(Netty(port))

    fun start() {
        server.start()
    }

    private fun getWriterInfos(req: Request): Response {
        val code = req.query("code")
            ?: return Response(Status.BAD_REQUEST).body("{'reason': 'missing_writer_code'}")

        val writerInfo = service.getWriterInfo(code)
        if (writerInfo.isEmpty()) {
            return Response(Status.NOT_FOUND).body("Not found WriterInfos with code: $code")
        }

        val body = jackson.writeValueAsBytes(writerInfo)

        return Response(Status.OK).body(body.inputStream())
    }
}
