package test

import scalafix.v1.SyntacticDocument

import scala.io.Source
import scala.meta.inputs.Input
import scala.meta._

case class Dependency(org: String, lib: String, version: String)

object Main extends App {

  val fileContents = Source.fromFile("/tmp/AppDependenciesForOlaf.scala").mkString

  val doc = SyntacticDocument.fromInput(Input.stringToInput(fileContents))

//  val dependencies:Seq[Dependency] =
  doc.tree
    .collect {
//      case x@_ ⇒
//        println(s"(${x.getClass} ) \t\t\t => $x")
      case Term.ApplyInfix(term, termName, metas, terms) ⇒
        println(s"term = $term")
        println(s"termName = $termName")
        println(s"metas = $metas")
        println(s"terms =$terms")
        println ("--")
    }

}
