package org.qollabor.service.api.tenant

import org.qollabor.service.api.projection.LastModifiedRegistration

object TenantReader {
  val lastModifiedRegistration: LastModifiedRegistration = new LastModifiedRegistration("Tenants")
}
