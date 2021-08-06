package org.qollabor.service.api.projection.cases

import org.qollabor.cmmn.akka.event.CaseDefinitionApplied
import org.qollabor.service.api.projection.record.CaseRoleRecord

object CaseInstanceRoleMerger {

  import scala.collection.JavaConverters._

  def merge(event: CaseDefinitionApplied): Seq[CaseRoleRecord] = {
    val caseDefinition = event.getDefinition()
    caseDefinition.getCaseRoles().asScala.map(role => CaseRoleRecord(event.getCaseInstanceId, event.tenant, role.getName, assigned = false)).toSeq
  }

}
