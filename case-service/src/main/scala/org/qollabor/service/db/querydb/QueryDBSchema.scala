package org.qollabor.service.db.querydb

import org.qollabor.akka.actor.CaseSystem
import org.qollabor.infrastructure.jdbc.QollaborJDBCConfig
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile


/**
  * To quickly DROP all tables (including the flyway tables) from Postgres, run the following script
  * *
   DROP table case_file CASCADE;
   DROP table case_instance CASCADE;
   DROP table case_instance_definition CASCADE;
   DROP table case_instance_role CASCADE;
   DROP table case_instance_team_member CASCADE;
   DROP table flyway_schema_history CASCADE;
   DROP table plan_item CASCADE;
   DROP table plan_item_history CASCADE;
   DROP table task CASCADE;
   DROP table "tenant" CASCADE;
   DROP table "tenant_owners" CASCADE;
   DROP table user_role CASCADE;
   DROP table offset_storage CASCADE;
  */

trait QueryDBSchema extends QollaborJDBCConfig {
  lazy val dbConfig: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig("", CaseSystem.config.queryDB.config)
}
