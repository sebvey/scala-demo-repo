package io.sve.ziolearning.oneoone

import java.io._
import zio.{ZIO, ZIOAppDefault}
import zio.Console._
import scala.io.Source

object XRCReadWrite extends ZIOAppDefault {

  val outputPath = "/home/sve/workspaces/perso/scala/scala-demo-repo/ziolearning/output.txt"
  val rscPath = "some_resource.txt"

  def readFile(path: String): String = {
    val src = Source.fromResource(path)

    try src.getLines.mkString("\n")
    finally src.close()
  }

  def writeFile(filePath: String, content: String): Unit = {

    val pw = new PrintWriter(new File(filePath))

    try pw.write(content)
    finally pw.close()

  }

  def readFileZIO(file: String) = ZIO.attempt(readFile(file))

  def writeFileZIO(filePath: String, content: String) = ZIO.attempt(writeFile(filePath, content))

  def run = for {
    content <- readFileZIO(rscPath)
    _       <- printLine(content)
    _ <- writeFileZIO(outputPath,content)
  } yield ()

}
