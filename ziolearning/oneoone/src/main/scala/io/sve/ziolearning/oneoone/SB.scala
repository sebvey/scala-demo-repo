package io.sve.ziolearning.oneoone

import java.io._
import zio._
import zio.Console._
import scala.io.Source
import scala.concurrent.{ExecutionContext, Future}
import zio.Schedule
import zio.{Scope, ZIOAppArgs}

import sttp.client4._
import sttp.client4.httpclient.zio.HttpClientZioBackend
import java.net.http.HttpClient


object SB extends ZIOAppDefault {

  val httpClient = HttpClient.newBuilder().build()
  val backend = HttpClientZioBackend.usingClient(httpClient)

  val query = "http language:scala"

  val sort: Option[String] = None

  // the `query` parameter is automatically url-encoded
  // `sort` is removed, as the value is not defined
  val request = basicRequest.get(
    uri"https://api.github.com/search/repositories?q=$query&sort=$sort")


  val run = for {

    response <- request.send(backend)

    _ <- printLine(response.headers)

  } yield ()



  // // response.header(...): Option[String]
  // println(response.header("Content-Length"))

  // // response.body: by default read into an Either[String, String]
  // // to indicate failure or success
  // println(response.body)

}
