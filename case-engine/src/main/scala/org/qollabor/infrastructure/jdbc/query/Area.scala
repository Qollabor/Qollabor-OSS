package org.qollabor.infrastructure.jdbc.query

case class Area(offset: Int, numOfResults: Int)

object Area {
  val Default = Area(0, 100)
}
