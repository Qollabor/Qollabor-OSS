package org.qollabor.infrastructure.jdbc.schema

import org.qollabor.infrastructure.jdbc.QollaborJDBCConfig
import slick.migration.api.Migration
import slick.migration.api.flyway.{MigrationInfo, VersionedMigration}

trait DbSchemaVersion extends QollaborJDBCConfig {
  val version: String
  val migrations: Migration
  def getScript(implicit infoProvider: MigrationInfo.Provider[Migration]): Seq[VersionedMigration[String]] = {
    Seq(VersionedMigration(version, migrations))
  }
}
