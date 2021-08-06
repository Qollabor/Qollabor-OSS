/*
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.service.api.cases.route

import akka.http.scaladsl.server.Directives._
import org.qollabor.identity.IdentityProvider
import org.qollabor.service.api.projection.query.CaseQueries

class DeprecatedPlanItemHistoryRoute(val caseQueries: CaseQueries)(override implicit val userCache: IdentityProvider) extends CasesRoute {
  override def routes = {
    deprecatedPlanItemHistory
  }

  def deprecatedPlanItemHistory = get {
    validUser { platformUser =>
      path(Segment / "planitems" / Segment / "history") {
        (caseInstanceId, planItemId) => {
          extractUri { uri =>
            logger.warn(s"Using deprecated API to get plan item history:")
            logger.warn(s"Old: /$caseInstanceId/planitems/$planItemId/history")
            logger.warn(s"New: /$caseInstanceId/history/planitems/$planItemId")
            runQuery(caseQueries.getPlanItemHistory(planItemId, platformUser))
          }
        }
      }
    }
  }
}