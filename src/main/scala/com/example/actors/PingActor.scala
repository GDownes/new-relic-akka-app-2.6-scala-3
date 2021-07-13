package com.example.actors

import akka.actor.{Actor, ActorLogging}

object PingActorMessages {
  case object Ping
  case object Pong
  case object Start
  case object Done
}

class PingActor extends Actor with ActorLogging {
  import PingActorMessages._

  def receive: Receive = {
    case Start =>
      log.info("Ping actor sending ping")
      Thread.sleep(100)
      context.actorSelection("../pongActor") ! Ping

    case Pong =>
      log.info("Ping actor received pong")
      log.info("Ping actor sending parent DONE")
      Thread.sleep(1000)
      context.parent ! Done

  }

  override def postStop() = {
    log.info("PingActor stopped.")
  }

}
