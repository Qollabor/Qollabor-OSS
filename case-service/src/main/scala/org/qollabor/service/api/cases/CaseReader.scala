package org.qollabor.service.api.cases

import org.qollabor.service.api.projection.LastModifiedRegistration

object CaseReader {
  val lastModifiedRegistration: LastModifiedRegistration = new LastModifiedRegistration("Cases")
}
