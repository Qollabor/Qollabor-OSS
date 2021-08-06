package org.qollabor.service.api.writer

import com.typesafe.config.ConfigFactory

object TestConfig {
  val config = ConfigFactory.parseString(
    """
      |      akka {
      |        loglevel = "DEBUG"
      |        stdout-loglevel = "DEBUG"
      |        loggers = ["akka.testkit.TestEventListener"]
      |        actor {
      |          default-dispatcher {
      |            executor = "fork-join-executor"
      |            fork-join-executor {
      |              parallelism-min = 8
      |              parallelism-factor = 2.0
      |              parallelism-max = 8
      |            }
      |          }
      |          serialize-creators = off
      |          serialize-messages = off
      |
      |          serializers {
      |            qollabor_serializer = "org.qollabor.akka.actor.serialization.QollaborSerializer"
      |          }
      |          serialization-bindings {
      |            "org.qollabor.akka.actor.serialization.QollaborSerializable" = qollabor_serializer
      |          }
      |        }
      |
      |      persistence {
      |       publish-confirmations = on
      |       publish-plugin-commands = on
      |       journal {
      |          plugin = "inmemory-journal"
      |       }
      |      }
      |      test {
      |        single-expect-default = 10s
      |        timefactor = 1
      |      }
      |    }
      |
      |    inmemory-journal {
      |      event-adapters {
      |         tagging = "org.qollabor.akka.actor.tagging.CaseTaggingEventAdapter"
      |
      |      }
      |      event-adapter-bindings {
      |        "org.qollabor.akka.actor.event.ModelEvent" = tagging
      |      }
      |    }
    """.stripMargin
  )
}
