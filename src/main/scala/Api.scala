package template

import cats.implicits._
import cats.effect._
import org.http4s._ 
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl
import org.http4s.implicits._
import org.http4s.circe.CirceEntityDecoder._

trait TemplateRoutes[F[_]] {
  val routes: HttpRoutes[F]
}

object TemplateRoutes {
  def build[F[_]: Async](client: TemplateClient[F]): TemplateRoutes[F] = {
    new TemplateRoutes[F] {
      val dsl = new Http4sDsl[F] {}
      import dsl._  

      val routes = HttpRoutes.of[F] {
        case GET -> Root / "ping" => Ok("pong")

        // Example POST below
        // case req @ POST -> Root / "chat" => for {
        //   chat    <- req.as[Chat]
        //   reply   <- client.completion(chat)
        //   choices <- Async[F].fromOption(reply.choices.headOption, new Exception("Something went wrong"))
        //   resp    <- Ok(choices.message.content)
        // } yield resp
      }
    }
  }
}