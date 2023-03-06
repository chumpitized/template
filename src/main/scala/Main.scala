package template

import java.time.LocalDate

import cats.effect._
import org.http4s._
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.client.Client
import com.comcast.ip4s._
import org.http4s.server.Server
import cats.effect.unsafe.implicits.global
import template.config.ConfigService
import template.config.IntegrationKey

object Main extends IOApp {

  def run(args: List[String]): IO[ExitCode] = (for {
    config <- Resource.eval(ConfigService.getConfig[IO])
    client <- buildClient
    serviceClient = TemplateClient.build[IO](client, config.integration)
    httpApp = TemplateRoutes.build[IO](serviceClient).routes.orNotFound
    server <- buildServer(httpApp)
  } yield server)
      .use(_ => IO.never)
      .as(ExitCode.Success)

  def buildServer(app: HttpApp[IO]): Resource[IO, Server] = 
    EmberServerBuilder
      .default[IO]
      .withHost(ipv4"0.0.0.0")
      .withPort(port"8080")
      .withHttpApp(app)
      .build

  def buildClient: Resource[IO, Client[IO]] = 
    EmberClientBuilder
    .default[IO]
    .build

}