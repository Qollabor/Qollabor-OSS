package org.qollabor.akka.actor.config

import java.util

import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging
import org.qollabor.akka.actor.identity.TenantUser

class PlatformConfig(val parent: QollaborConfig) extends MandatoryConfig {
  val path = "platform"
  override val exception = ConfigurationException("Check configuration property 'qollabor.platform'. This must be available")

  val platformOwners: util.List[String] = config.getStringList("owners")
  if (platformOwners.isEmpty) {
    throw ConfigurationException("Platform owners cannot be an empty list. Check configuration property qollabor.platform.owners")
  }

  lazy val defaultTenant = {
    val configuredDefaultTenant = readString("default-tenant", "")
    configuredDefaultTenant
  }

  /**
    * Config property for reading a specific file with bootstrap tenant setup
    */
  lazy val bootstrapFile = readString("bootstrap-file", "")

  def isPlatformOwner(user: TenantUser): Boolean = isPlatformOwner(user.id)

  def isPlatformOwner(userId: String): Boolean = {
    // TTP: platformOwners should be taken as Set and "toLowerCase" initially, and then we can do "contains" instead
    logger.debug("Checking whether user " + userId + " is a platform owner; list of owners: " + platformOwners)
    platformOwners.stream().filter(o => o.equalsIgnoreCase(userId)).count() > 0
  }
}