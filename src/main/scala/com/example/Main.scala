package com.example

import akka.actor.{ActorSystem, Props}
import com.example.actors.RefereeActor

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

object Main {

  val system = ActorSystem("MyActorSystem")
  val referee = system.actorOf(Props[RefereeActor], "referee")

  def main(args: Array[String]): Unit = {
    println("Started")
    Await.ready(system.whenTerminated, 365.days)
  }
}
