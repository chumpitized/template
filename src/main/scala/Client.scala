package template

import cats.effect._
import org.http4s._ 
import org.http4s.dsl.Http4sDsl
import org.http4s.implicits._
import org.http4s.circe.CirceEntityDecoder._
import org.http4s.circe.CirceEntityEncoder._
import org.http4s.client.Client
import org.http4s.headers.Authorization
import template.config.IntegrationKey

trait TemplateClient[F[_]] {
  def templatePost: F[Unit]
}

object TemplateClient {

  def build[F[_]: Async](client: Client[F], integrationKey: IntegrationKey): TemplateClient[F] =
    new TemplateClient[F] {
      def templatePost: F[Unit] = {
        
        // val payload = ???
        // val req = Request[F](
        //   method = Method.POST,
        //   uri = uri"https://YOUR_URL_HERE",
        //   headers = Headers.of(
        //     Authorization(Credentials.Token(AuthScheme.Bearer, integrationKey.key))
        // )).withEntity(
        //   payload
        // )
        
        // client.expect[???](req)

        Async[F].unit

      }

    }

}
