package org.qollabor.service.api.cases

import java.time.Instant
import java.util.UUID

import akka.actor.ActorSystem
import akka.testkit.TestKit
import org.qollabor.cmmn.instance.State
import org.qollabor.identity.TestIdentityFactory
import org.qollabor.service.api.projection.query.{CaseFilter, CaseQueriesImpl}
import org.qollabor.service.api.projection.record.{CaseRecord, CaseTeamMemberRecord, PlanItemHistoryRecord, PlanItemRecord}
import org.qollabor.service.api.projection.slick.SlickRecordsPersistence
import org.qollabor.service.api.writer.TestConfig
import org.qollabor.service.db.querydb.{QueryDB, QueryDBSchema}
import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.must.Matchers

import scala.concurrent.Await
import scala.concurrent.duration._

class CaseQueriesImplTest extends TestKit(ActorSystem("testsystem", TestConfig.config)) with AnyFlatSpecLike with Matchers with BeforeAndAfterAll with QueryDBSchema {

  implicit val executionContext = scala.concurrent.ExecutionContext.Implicits.global

  val caseQueries = new CaseQueriesImpl
  val updater = new SlickRecordsPersistence

  val tenant = "tenant"

  val idOfActiveCase = "active"
  val idOfTerminatedCase = "terminated"
  val idOfCompletedCase = "completed"
  val activeCase = CaseRecord(id = idOfActiveCase, tenant = tenant, rootCaseId = idOfActiveCase, caseName = "aaa bbb ccc", state = State.Active.toString, failures = 0, lastModified = Instant.now, createdOn = Instant.now) //, casefile = "")
  val terminatedCase = CaseRecord(id = idOfTerminatedCase, tenant = tenant, rootCaseId = idOfTerminatedCase, caseName = "ddd EeE fff", state = State.Terminated.name, failures = 0, lastModified = Instant.now, createdOn = Instant.now) //, casefile = "")
  val completedCase = CaseRecord(id = idOfCompletedCase, tenant = tenant, rootCaseId = idOfCompletedCase, caseName = "ddd EeE fff", state = State.Completed.name, failures = 0, lastModified = Instant.now, createdOn = Instant.now) //, casefile = "")

  val planItem1_1 = PlanItemRecord(id = UUID.randomUUID().toString, definitionId = "abc", caseInstanceId = idOfActiveCase, tenant = tenant, stageId = "", name = "planitem1-1", index = 0, currentState = "Active",
    historyState = "", transition = "", planItemType = "CasePlan", required = false, repeating = false, lastModified = Instant.now,
    modifiedBy = "user1", createdOn = Instant.now, createdBy = "user1", taskInput = "", taskOutput = "", mappedInput = "", rawOutput = "")

  //  val planItemId1 = UUID.randomUUID().toString
  val planItem2_1 = PlanItemRecord(id = UUID.randomUUID().toString, definitionId = "abc", caseInstanceId = idOfTerminatedCase, tenant = tenant, stageId = "", name = "planitem2-1", index = 0, currentState = "Completed",
    historyState = "", transition = "", planItemType = "CasePlan", required = false, repeating = false, lastModified = Instant.now,
    modifiedBy = "user1", createdOn = Instant.now, createdBy = "user1", taskInput = "", taskOutput = "", mappedInput = "", rawOutput = "")

  val planItemHistory2_1 = PlanItemHistoryRecord(id = UUID.randomUUID().toString, planItemId = planItem2_1.id, caseInstanceId = idOfTerminatedCase, tenant = tenant, stageId = "", name = "planitem2",
    currentState = "", historyState = "", transition = "", index = 0, planItemType = "", required = false, repeating = false, lastModified = Instant.now,
    modifiedBy = "user1", eventType="?", sequenceNr = 1, taskInput = "", taskOutput = "", mappedInput = "", rawOutput = "")

  val caseListActive = CaseList(caseName = "aaa bbb ccc", numActive = 1L, numClosed = 0L)
  val caseListDDDEEEFFF = CaseList(caseName = "ddd EeE fff", numTerminated = 1L, numCompleted = 1L)
  val caseListTerminated = CaseList(caseName = "ddd EeE fff", numTerminated = 1L)

  val user = TestIdentityFactory.createPlatformUser("user1", tenant, List("A", "B"))

  val caseTeamMemberRecords = Seq(
    CaseTeamMemberRecord(caseInstanceId = idOfActiveCase, tenant = tenant, memberId = user.userId, caseRole = "", isTenantUser = true, false, active = true),
    CaseTeamMemberRecord(caseInstanceId = idOfTerminatedCase, tenant = tenant, memberId = user.userId, caseRole = "", isTenantUser = true, false, active = true),
    CaseTeamMemberRecord(caseInstanceId = idOfCompletedCase, tenant = tenant, memberId = user.userId, caseRole = "", isTenantUser = true, false, active = true),
  )

  override def beforeAll {
    QueryDB.verifyConnectivity()
    val records = Seq(activeCase, planItem1_1, terminatedCase, completedCase, planItem2_1, planItemHistory2_1) ++ caseTeamMemberRecords ++ TestIdentityFactory.asDatabaseRecords(user)
    updater.bulkUpdate(records)
    //    Await.ready(Future.sequence(caseQueries.bulkUpdate(records), 1.seconds)
  }

  // *******************************************************************************************************************
  // Responses of type Case
  // *******************************************************************************************************************

  "Create a table" should "succeed the second time as well" in {
    QueryDB.verifyConnectivity()
  }

  "A query" should "retrieve an existing case" in {
    val res = Await.result(caseQueries.getCaseInstance(activeCase.id, user), 3.seconds)
    res must be (Some(activeCase))
  }

  val tenantFilter = CaseFilter(Some(tenant))

  it should "retrieve all cases" in {
    val res = Await.result(caseQueries.getCases(user, tenantFilter), 3.seconds)
    res must contain (activeCase)
    res must contain (terminatedCase)
    res must contain (completedCase)

    // TODO: the old test had only below line (instead of above three lines). Sometimes this would make the test fail for unclear reasons (perhaps timing issues causes by other tests running parallelly???)
    //    res must be (Seq(completedCase, terminatedCase, activeCase))
  }

  it should "retrieve cases filtered by definition" in {
    val res = Await.result(caseQueries.getCases(user, tenantFilter.copy(caseName = Some("eee"))), 3.seconds)
    res must be (Seq(completedCase, terminatedCase))
  }

  it should "retrieve cases filtered by status" in {
    val res = Await.result(caseQueries.getCases(user, tenantFilter.copy(status = Some("Active"))), 3.seconds)
    res must be (Seq(activeCase))
  }

  it should "retrieve my terminated cases" in {
    val res = Await.result(caseQueries.getMyCases(user, tenantFilter.copy(status = Some("Terminated"))), 3.seconds)
    res must be (Seq(terminatedCase))
  }

  it should "retrieve my completed cases" in {
    val res = Await.result(caseQueries.getMyCases(user, tenantFilter.copy(status = Some("Completed"))), 3.seconds)
    res must be (Seq(completedCase))
  }

  // *******************************************************************************************************************
  // Responses of type CaseList
  // *******************************************************************************************************************

  it should "filter all cases by definition" in {
    val res = Await.result(caseQueries.getCasesStats(user = user, Some(tenant), from = 0, numOfResults = 10, caseName = Some("ddd EeE fff")), 1.second)
    res.size must be (1)
    res.head must be(caseListDDDEEEFFF)
  }

  it should "filter all cases by status - active" in {
    val res = Await.result(caseQueries.getCasesStats(user = user, Some(tenant), from = 0, numOfResults = 10, status = Some("Active")), 1.second)
    res.size must be (1)
    res.head must be(caseListActive)
  }


  it should "filter all cases by status - terminated" in {
    val res = Await.result(caseQueries.getCasesStats(user = user, Some(tenant), from = 0, numOfResults = 10, status = Some("Terminated")), 1.second)
    res.size must be (1)
    res.head must be(caseListTerminated)
  }

  it should "retrieve all planItems" in {
    val res = Await.result(caseQueries.getPlanItems(planItem1_1.caseInstanceId, user), 1.second)
    res.items.size must be (1)
    res.items.head must be(planItem1_1)
  }

  it should "retrieve a planItem" in {
    val res = Await.result(caseQueries.getPlanItem(planItem2_1.id, user), 1.second)
    res.record must be(planItem2_1)
  }

  it should "retrieve planItemHistory records" in {
    val res = Await.result(caseQueries.getPlanItemHistory(planItemHistory2_1.planItemId, user), 1.second)
    res.records.size must be (1)
    res.records.head must be(planItemHistory2_1)
  }

}
