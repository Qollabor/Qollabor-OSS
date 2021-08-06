package org.qollabor.akka.actor.config

class OIDCConfig(val parent: SecurityConfig) extends MandatoryConfig {
  val path = "oidc"
  override val exception = ConfigurationException("Check configuration property 'qollabor.api.security.oidc'. This must be available.")

  val connectUrl = config.getString("connect-url")
  val tokenUrl = config.getString("token-url")
  val keysUrl = config.getString("key-url")
  val authorizationUrl = config.getString("authorization-url")
  val issuer = config.getString("issuer")
}