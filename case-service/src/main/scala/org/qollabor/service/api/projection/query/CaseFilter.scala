package org.qollabor.service.api.projection.query

case class CaseFilter(tenant: Option[String] = None, caseName: Option[String] = None, status: Option[String] = None, identifiers: Option[String] = None, parentCaseId: Option[String] = None, rootCaseId: Option[String] = None)
