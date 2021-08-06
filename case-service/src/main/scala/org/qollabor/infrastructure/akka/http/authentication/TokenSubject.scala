package org.qollabor.infrastructure.akka.http.authentication

case class TokenSubject(value: String)

case class ServiceUserContext(subject: TokenSubject, groups: List[String])
