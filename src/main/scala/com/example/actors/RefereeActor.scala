package com.example.actors

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import com.newrelic.api.agent.{NewRelic, Trace, TransactionNamePriority}

object RefereeActorMessages {
  case class Foul(text: String)
  case object StartGame
}

class RefereeActor extends Actor with ActorLogging {
  var pingActor: ActorRef = context.actorOf(Props[PingActor], "pingActor")
  var pongActor: ActorRef = context.actorOf(Props[PongActor], "pongActor")

  def receive: Receive = {
    case "Start Game" =>
      startGameinTxn()

    case RefereeActorMessages.StartGame =>
      pingActor ! PingActorMessages.Start

    case PingActorMessages.Done =>
      println("Referee received Done. stopping game")
      context.system.terminate();

    case RefereeActorMessages.Foul =>
      sender() ! RefereeActorMessages.Foul("that was not a foul.")
  }

  override def postStop() = {
    println("Referee stopped.")
  }

  @Trace(dispatcher = true)
  def startGameinTxn() = {
    NewRelic.getAgent.getTransaction.setTransactionName(TransactionNamePriority.CUSTOM_HIGH, true, "myCategory", "pingPongGame")
    self ! RefereeActorMessages.StartGame
  }

}
