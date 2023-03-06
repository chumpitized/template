package template.config

import scala.concurrent.duration.FiniteDuration

final case class ServiceConfig(
  integration: IntegrationKey
)

final case class IntegrationKey(
  key: String
)