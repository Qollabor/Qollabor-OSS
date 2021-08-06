package org.qollabor.service.db.querydb.schema.versions

import org.qollabor.infrastructure.jdbc.schema.DbSchemaVersion
import org.qollabor.service.api.projection.table.{CaseTables, TaskTables}
import org.qollabor.service.db.querydb.QueryDBSchema
import org.qollabor.service.db.querydb.schema.Projections
import slick.migration.api.TableMigration

object QueryDB_1_1_10 extends DbSchemaVersion with QueryDBSchema
  with CaseTables {

  val version = "1.1.10"
  val migrations = (
    addPlanItemDefinitionIdColumn
  )

  import dbConfig.profile.api._

  def addPlanItemDefinitionIdColumn = TableMigration(TableQuery[PlanItemTable]).addColumns(_.definitionId)

}
