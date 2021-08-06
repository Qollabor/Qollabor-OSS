package org.qollabor.service.db.querydb.schema.versions

import org.qollabor.infrastructure.jdbc.schema.DbSchemaVersion
import org.qollabor.service.db.querydb.QueryDBSchema
import org.qollabor.service.db.querydb.schema.Projections
import slick.migration.api.Migration

object QueryDB_1_1_5 extends DbSchemaVersion with QueryDBSchema {
  val version = "1.1.5"
  val migrations: Migration = Projections.resetCaseProjectionWriter
}
