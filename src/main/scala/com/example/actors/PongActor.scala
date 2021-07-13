package com.example.actors

import akka.actor.{Actor, ActorLogging, ActorRef}
import akka.pattern._
import akka.util.Timeout

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

class PongActor extends Actor with ActorLogging {

  implicit val timeout: Timeout = Timeout(1.second)
  val future: Future[ActorRef] = context.actorSelection("..").resolveOne()
  val referee: ActorRef = Await.result(future, timeout.duration)

  def receive: Receive = {
    case PingActorMessages.Ping =>
      println("Pong actor received ping")
      println("Pong actor says: foul!")
      val future = referee ? RefereeActorMessages.Foul
      val response = Await.result(future, 3.second)
      println("Pong actor disagrees with referees decision: " + response)

      sender() ! PingActorMessages.Pong
  }

  override def postStop() = {
    println("PingActor stopped.")
  }
}
