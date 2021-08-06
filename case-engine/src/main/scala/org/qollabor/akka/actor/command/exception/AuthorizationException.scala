package org.qollabor.akka.actor.command.exception

case class AuthorizationException(message: String) extends RuntimeException(message)
