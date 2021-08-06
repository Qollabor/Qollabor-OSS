package org.qollabor.service.api.projection.cases

import org.qollabor.cmmn.akka.event.file.{BusinessIdentifierCleared, BusinessIdentifierSet}
import org.qollabor.service.api.projection.record.CaseBusinessIdentifierRecord

object CaseIdentifierMerger {

  def merge(event: BusinessIdentifierSet): CaseBusinessIdentifierRecord = {
    CaseBusinessIdentifierRecord(event.getActorId, event.tenant, event.name, Some(event.value.getValue.toString), true, event.path.toString)
  }

  def merge(event: BusinessIdentifierCleared): CaseBusinessIdentifierRecord = {
    CaseBusinessIdentifierRecord(event.getActorId, event.tenant, event.name, None, false, event.path.toString)
  }
}
