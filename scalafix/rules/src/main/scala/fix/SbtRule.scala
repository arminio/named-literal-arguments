package fix

import scalafix.v1._

import scala.meta._

class SbtRule extends SyntacticRule("SbtRule") {
  override def fix(implicit doc: SyntacticDocument): Patch = {


    doc.tree.collect {

      case Term.ApplyInfix(Term.Name(n), _, _, terms) if n == "libraryDependencies"  ⇒

          println(terms)
          terms.collect {
            case Term.Apply(f, args) ⇒
              println(s"f:${f.getClass} => $f")
              println(s"args:${args.getClass} => $args")
              args.collect{
                case dep@q""""org.typelevel" %% "cats-core" % ${v @ Lit.String(_)}""" ⇒
                  println(s"extracted version: $v")
//                  Patch.replaceTree(dep, """"org.typelevel" %% "cats-core" % "19.SUCCESS.0"""")
                  Patch.replaceTree(v, """"38.CHICKEN.0"""")
              }
          }

      }

  }.flatten.flatten.asPatch
}