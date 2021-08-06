package org.qollabor.tenant.akka.command.exception

import org.qollabor.akka.actor.command.exception.InvalidCommandException

case class TenantException(msg: String) extends InvalidCommandException(msg)
