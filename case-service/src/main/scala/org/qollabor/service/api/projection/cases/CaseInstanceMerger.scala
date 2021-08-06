package org.qollabor.service.api.projection.cases

import org.qollabor.cmmn.akka.event.{CaseDefinitionApplied, CaseModified}
import org.qollabor.cmmn.instance.State
import org.qollabor.service.api.projection.record.CaseRecord

object CaseInstanceMerger {

  def merge(evt: CaseDefinitionApplied): CaseRecord = {
    CaseRecord(
      id = evt.getCaseInstanceId,
      tenant = evt.tenant,
      rootCaseId = evt.getRootCaseId,
      parentCaseId = evt.getParentCaseId,
      caseName = evt.getCaseName,
      state = State.Active.toString, // Will always be overridden from CaseModified event
      failures = 0,
      lastModified = evt.createdOn,
      modifiedBy = evt.createdBy,
      createdBy = evt.createdBy,
      createdOn = evt.createdOn
    )
  }

  def merge(evt: CaseModified, currentCaseInstance: CaseRecord): CaseRecord = {
    currentCaseInstance.copy(
      lastModified = evt.lastModified,
      modifiedBy = evt.getUser.id,
      failures = evt.getNumFailures,
      state = evt.getState.toString)
  }
}
