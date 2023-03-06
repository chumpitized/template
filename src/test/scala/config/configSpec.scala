package template.config

import org.specs2.mutable.Specification
import cats.effect.IO
import template.config.{ConfigService, ServiceConfig}
import cats.effect.unsafe.implicits.global

class ConfigSpec extends Specification {
  "Load config" >> {
    ConfigService.getConfig[IO].attempt.unsafeRunSync() must beRight[ServiceConfig]
  }
}