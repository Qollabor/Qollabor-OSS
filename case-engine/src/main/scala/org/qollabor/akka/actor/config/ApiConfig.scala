package org.qollabor.akka.actor.config

class ApiConfig(val parent: QollaborConfig) extends MandatoryConfig {
  val path = "api"
  override val exception = ConfigurationException("Qollabor API is not configured. Check local.conf for 'qollabor.api' settings")

  lazy val bindHost = {
    config.getString("bindhost")
  }

  lazy val bindPort = {
    config.getInt("bindport")
  }

  lazy val security: SecurityConfig = new SecurityConfig(this)
}