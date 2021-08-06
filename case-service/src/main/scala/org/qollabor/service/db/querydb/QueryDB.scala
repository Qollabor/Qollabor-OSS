package org.qollabor.service.db.querydb

import org.qollabor.infrastructure.jdbc.schema.QollaborDatabaseDefinition
import org.qollabor.service.db.querydb.schema.versions.{QueryDB_1_0_0, QueryDB_1_1_10, QueryDB_1_1_5, QueryDB_1_1_6}

object QueryDB extends QollaborDatabaseDefinition with QueryDBSchema {
  def verifyConnectivity() {
    useSchema(Seq(QueryDB_1_0_0, QueryDB_1_1_5, QueryDB_1_1_6, QueryDB_1_1_10))
  }
}
