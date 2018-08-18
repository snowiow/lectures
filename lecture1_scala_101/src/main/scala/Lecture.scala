
import SlideshowUtil._
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object Lecture extends JSApp {

  import Enumeration._

  val overview = chapter(
    chapterSlide(
      <.h1("Scala 101")
    ),

    slide(
      "What we will learn in this lecture",
      Enumeration(
        Item.stable(<.p("Scala background")),
        Item.fadeIn(<.p("SBT: manage your projects")),
        Item.fadeIn(<.p("introduction into Scala (our first code yeah)"))
      )
    ),

    noHeaderSlide(
      <.h2("You have a question?"),
      <.h3("Ask it right away!")
    )
  )

  val introduction = chapter(
    chapterSlide(
      <.h1("What is Scala? And why?")
    ),

    slide(
      "In a nutshell",
      Enumeration(
        Item.stable(<.p("strict and statically typed programming language")),
        Item.fadeIn(<.p(<.b("Sca"), "lable ", <.b("La"), "nguage")),
        Item.fadeIn(<.p("support for JVM, native and JavaScript")),
        Item.fadeIn(<.p("Martin Orderky et all in 2005"))
      )
    ),

    slide(
      "Why Scala: JVM",
      Enumeration(
        Item.stable(<.p("access to Java ecosystem")),
        Item.fadeIn(<.p("or any ecosystem compiling to ByteCode)")),
        Item.fadeIn(<.p("JVM performance (JIT, GC)")),
        Item.fadeIn(<.p("... and tooling (metrics, debugging, etc.)")),
      )
    ),

    slide(
      "Why Scala: brings its own Ecosysten",
      Enumeration(
        Item.stable(<.p("large projects like Spark or Akka")),
        Item.fadeIn(<.p("strong library support for HTTP, FP, OOP, JDBC, ..."))
      )
    ),

    slide(
      "Why Scala: Fusion of FP and OOP",
      Enumeration(
        Item.stable(<.p("with additional Type-Level programming on top")),
        Item.fadeIn(<.p("choose paradigm which solve your problem best ...")),
        Item.fadeIn(<.p("... stay in the same language"))
      )
    ),

    slide(
      "Why Scala: Industry & Academia combined",
      Enumeration(
        Item.stable(<.p("big players use it: Twitter, Stripe to name a few")),
        Item.fadeIn(<.p("backed by Lightbend")),
        Item.fadeIn(<.p("backed by EPFL")),
        Item.fadeIn(<.p("... and of course by an Open Source community"))
      )
    ),

    noHeaderSlide(
      <.h2("Are you convinced?"),
      <.h4(
        ^.cls := "fragment fade-in",
        "Great, then let's move on and learn how to use it."
      )
    )
  )

  val expressions = chapter(
    chapterSlide(
      <.h1("Expressions"),
      <.h3("Basic building blocks")
    ),

    slide(
      "Expressions",
      <.p("Scala programs are made up from expressions and declarations.")
    ),

    slide(
      "Expressions",
      <.p("Expression can be values, combination of values or functions applied to values. And they get reduced to results.")
    ),

    slide(
      "Values as Expressions",
      code("""
        // already in reduced form
        1
        'a'
        "Hello"
      """)
    ),

    slide(
      "Operations as Expressions",
      code("""
        1 + 2
      """),
      codeFragment("""
        ==> 3
      """),
      codeFragment("""
        val w = "world"

        "Hello " + a + "!"
      """),
      codeFragment("""
        ==> "Hello world!"
      """)
    ),

    slide(
      "Assignment of results",
      code("""
        val firstName = "John"
        val lastName  = "Doe"

        val name = a + b
      """)
    ),

    slide(
      "Block Expressions",
      code("""
        {
          val a = 1 + 2
      
          a * 5
        }
      """),
      codeFragment("""
        > {
          val a = 3
      
          a * 5
        }
      """),
      codeFragment("""
        > {
          3 * 5
        }
      """),
      codeFragment("""
         ==> 15
      """)      
    ),

    slide(
      "Scoping",
      code("""
        val a = 1 + 2
        {
           val b = 2 + 3
           val c = {
             val a = 3 + 4
             a + b
           }
           a + c
        }
      """),
      codeFragment("""
        > val a = 3
        {
           val b = 5
           val c = {
             val a = 7
             7 + 5
           }
           3 + (7 + 5)
        }
      """),
      codeFragment("""
        ==> 15
      """)
    ),

    exerciseSlide(
      "Does this work?",
      code("""
        val a = 5
        {
          val b = 3
          a + b
        }
      """),
      codeFragment("""
        ==> 8
      """)
    ),

    exerciseSlide(
      "Does this work?",
      code("""
        val a = {
          val b = 6
          10
        }
        a + b
      """),
      codeFragment("""
        // no, `b` doesn't exist in the outer scope
      """)
    ),

    exerciseSlide(
      "Does this work?",
      code("""
        val a = 5
        {
          val a = {
            a * 10
          }
          a
        }
      """),
      codeFragment("""
        // no, you try to define `a` by using itself
      """)
    ),

    slide(
      "Precedence",
      code("""
        0 - (all letters)
        1 - |
        2 - ^
        3 - &
        4 - = !
        5 - < >
        6 - :
        7 - + -
        8 - * / %
        // (all other special characters)
      """)
    ),

    exerciseSlide(
      "What's the result?",
      code("""
        2 * 3 + 10
      """),
      codeFragment("""
        // prec('*') > prec('+')
        > (2 * 3) + 10
      """),
      codeFragment("""
        > 6 + 10
      """),
      codeFragment("""
        ==> 16
      """)
    ),

    exerciseSlide(
      "What's the result?",
      code("""
        2.0 * 3.0 / 3.0
      """),
      codeFragment("""
        // '*' left most operation
        > (2.0 * 3.0) / 3.0
      """),
      codeFragment("""
        > 6.0 / 3.0
      """),
      codeFragment("""
        ==> 2.0
      """)
    ),

    exerciseSlide(
      "What's the result?",
      code("""
        2 * 5 == 10 && 24 / 6 < 4 | ! false
      """),
      codeFragment("""
        > (((2 * 5) == 10) && ((24 / 6) < 4)) | (! false)
      """),
      codeFragment("""
        // use brackets
        ==> true
      """)
    ),

    noHeaderSlide(
      <.h2("Expressions return typed results"),
      <.br,
      codeFragment("""
        1       // Int
        "hello" // String

        1.0 + 2.0 // reduces to a Double
      """)
    ),

    noHeaderSlide(
      <.h2("But what are types?")
    )
  )

  val types = chapter(
    chapterSlide(
      <.h1("Types"),
      <.h3("Make your code more strict")
    ),

    slide(
      "Definition",
      <.h3("Types put constraints on term.")
    ),

    slide(
      "State of a Value",
      code("""
        a: Int = 0

        // `a` is of type `Int`
        // is an Integer number
        // -2147483648 <= a <= 2147483647

        b: String = a // forbidden 
      """)
    ),

    slide(
      "Primitive Types",
      code("""
        Boolean - true false
        Byte    - Integer from -128 to 127
        Short   - Integer from -32,768 to 32,767
        Int     - Integer from -2,147,483,648 to 2,147,483,647
        Long    - Integer from -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807
        Float   - +- 1.40129846432481707e-45 to 3.40282346638528860e+38
        Double  - +- 4.94065645841246544e-324 to 1.79769313486231570e+308
        Char    - signs encoded with Integers from 0 to 65,535
        String  - sequence of Chars
      """),
      <.br,
      codeFragment("""
        // And many more complex types. Some of them we will learn
        // in the course of this workshop.
      """)
    ),

    slide(
      "Operation restriction",
      code("""
          1 + 2
        """),
      codeFragment("""
        // numbers, Char and String a
        1 + 2.0       ==> 3.0: Double
        1 + 'a'       ==> 98: Int
        1 + " banana" ==> "1 banana": String
      """),
      codeFragment("""
        1 + true // forbidden
      """)
    ),

    exerciseSlide(
      "What is the Type?",
      code("""
        1 + 2
      """),
      codeFragment("""
        ==> 3: Int
      """),
      code("""
        1.0 + 2
      """),
      codeFragment("""
        ==> 3.0: Double
      """),
      code("""
        "Hello " + true
      """),
      codeFragment("""
        ==> "Hello true": String
      """)
    ),

    exerciseSlide(
      "What is the Type?",
      code("""
        1 / 2
      """),
      codeFragment("""
        ==> 0: Int
      """),
      code("""
        1 / 2.0
      """),
      codeFragment("""
        ==> 0.5: Double
      """),
      code("""
        1 / 0.0
      """),
      codeFragment("""
        // this throws an error (Exception)
      """)
    ),
    
    slide(
      "Types are important",
      Enumeration(
        Item.stable(<.p("give guarantees about state of values / application of operators")),
        Item.fadeIn(<.p("proofen during compile time")),
        Item.fadeIn(<.p("no extra tests necessary"))
      )
    ),

    noHeaderSlide(
      <.p("We now know what Expressions are and how to use them."),
      <.p("But how do we make them reusable?")
    )
  )

  val Show = ScalaComponent
    .builder[Unit]("Slideshow")
    .renderStatic(
      <.div(
        ^.cls := "reveal",
        <.div(
          ^.cls := "slides",
          overview,
          introduction,
          expressions,
          types
        )
      )
    )
    .build

  @JSExport
  override def main(): Unit = {
    Show().renderIntoDOM(dom.document.body)
  }
}
