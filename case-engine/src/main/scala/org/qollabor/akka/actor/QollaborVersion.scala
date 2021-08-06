package org.qollabor.akka.actor

import org.qollabor.akka.actor.serialization.json.{JSONReader, ValueMap}
import org.qollabor.cmmn.akka.BuildInfo

class QollaborVersion(val json: ValueMap = JSONReader.parse(BuildInfo.toJson).asInstanceOf[ValueMap]) {
  /**
    * Returns true if the two versions differ, false if they are the same.
    * @param otherVersionInstance
    * @return
    */
  def differs(otherVersionInstance: QollaborVersion): Boolean = {
    !json.equals(otherVersionInstance.json)
  }

  override def toString: String = json.toString
}
