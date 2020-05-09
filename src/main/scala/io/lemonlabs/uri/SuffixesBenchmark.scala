package io.lemonlabs.uri

import java.util.concurrent.TimeUnit

import io.lemonlabs.uri.SuffixesBenchmark.SuffixesBenchmarkState
import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.BenchmarkParams

import scala.annotation.tailrec
import scala.util.Random

object SuffixesBenchmark {

  object Trie {
    def apply(prefix: List[Char]): Trie =
      prefix match {
        case Nil     => Trie(Map.empty, wordEnd = true)
        case x :: xs => Trie(Map(x -> Trie(xs)), wordEnd = false)
      }

    def empty = Trie(Map.empty, wordEnd = false)
  }
  case class Trie(children: Map[Char, Trie], wordEnd: Boolean = false) {
    def +(kv: (Char, Trie)): Trie =
      this.copy(children = children + kv)

    def next(ch: Char): Option[Trie] =
      children.get(ch)

    def insert(value: List[Char]): Trie =
      value match {
        case Nil => copy(wordEnd = true)
        case x :: xs =>
          next(x) match {
            case None =>
              this + (x -> Trie(xs))
            case Some(child) =>
              this + (x -> child.insert(xs))
          }
      }

    def size: Int = children.size + children.values.map(_.size).sum

    def matches(s: String): Vector[String] = {
      @tailrec
      def collectMatches(previous: String,
                         stillToGo: List[Char],
                         current: Trie,
                         matches: Vector[String]): Vector[String] = stillToGo match {
        case Nil =>
          matches
        case x :: xs =>
          current.next(x) match {
            case None =>
              matches
            case Some(next) =>
              val newPrevious = previous + x
              val newMatches = if (next.wordEnd && xs.headOption.contains('.')) newPrevious +: matches else matches
              collectMatches(newPrevious, xs, next, newMatches)
          }
      }
      collectMatches("", s.toCharArray.toList, this, Vector.empty)
    }

    def longestMatch(s: String): Option[String] =
      matches(s).headOption
  }




  object SegmentTrie {
    def apply(prefix: List[String]): SegmentTrie =
      prefix match {
        case Nil     => SegmentTrie(Map.empty, wordEnd = true)
        case x :: xs => SegmentTrie(Map(x -> SegmentTrie(xs)), wordEnd = false)
      }

    def empty = SegmentTrie(Map.empty, wordEnd = false)
  }
  case class SegmentTrie(children: Map[String, SegmentTrie], wordEnd: Boolean = false) {
    def +(kv: (String, SegmentTrie)): SegmentTrie =
      this.copy(children = children + kv)

    def next(segment: String): Option[SegmentTrie] =
      children.get(segment)

    def insert(value: List[String]): SegmentTrie =
      value match {
        case Nil => copy(wordEnd = true)
        case x :: xs =>
          next(x) match {
            case None =>
              this + (x -> SegmentTrie(xs))
            case Some(child) =>
              this + (x -> child.insert(xs))
          }
      }

    def size: Int = children.size + children.values.map(_.size).sum

    def matches(s: String): List[String] = {
      @tailrec
      def collectMatches(previous: String,
                         stillToGo: Array[String],
                         current: SegmentTrie,
                         matches: List[String]): List[String] =
        if(stillToGo.isEmpty)
          matches
        else {
          current.next(stillToGo.head) match {
            case None =>
              matches
            case Some(next) =>
              val newPrevious = if(previous.isEmpty) stillToGo.head else stillToGo.head + "." + previous
              val newMatches = if (next.wordEnd) newPrevious +: matches else matches
              collectMatches(newPrevious, stillToGo.tail, next, newMatches)
          }
        }

      collectMatches("", s.split('.').reverse, this, List.empty)
    }

    def longestMatch(s: String): Option[String] =
      matches(s).headOption
  }



  @State(Scope.Thread)
  class SuffixesBenchmarkState {

    var findLongestOnThese: List[String] = _
    var suffixes: List[String] = _
    var suffixesSet: Set[String] = _

    var trie: Trie = _
    var segmentTrie: SegmentTrie = _

    @Setup def setUp(params: BenchmarkParams): Unit = {
      val numberOfSuffixes = params.getParam("numberOfSuffixes").toInt
      suffixes = PublicSuffixes.all.take(numberOfSuffixes)

      suffixesSet = suffixes.toSet

      trie = suffixes.foldLeft(Trie.empty) { (t, s) =>
        t.insert(s.reverse.toList)
      }
      segmentTrie = suffixes.foldLeft(SegmentTrie.empty) { (t, s) =>
        t.insert(s.split('.').reverse.toList)
      }

      val www = params.getParam("www").toBoolean
      findLongestOnThese = PublicSuffixes.alexa10k.map { host =>
        if(!www) host else s"www.$host"
      }
    }
  }
}

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 50, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 50, time = 200, timeUnit = TimeUnit.MILLISECONDS)
class SuffixesBenchmark {

  import org.openjdk.jmh.annotations.Param

  @Param(Array("10", "100", "10000"))
//  @Param(Array("10000"))
  private var numberOfSuffixes: Int = _

  @Param(Array("true", "false"))
  private var www: Boolean = _

  @Benchmark
  @Fork(1)
  def publicSuffixes(s: SuffixesBenchmarkState): Unit = {
    s.findLongestOnThese.foreach { findLongestOnThis =>

//      // Brute force DB implementation
//      val bfDbRes = s.suffixes.foldLeft(Option.empty[String]) { (longest, suffix) =>
//        if (findLongestOnThis.endsWith(s".$suffix") && longest.forall(_.length < suffix.length)) {
//          Some(suffix)
//        } else {
//          longest
//        }
//      }

       // Brute force segment implementation
//      def findLongest(remaining: String): Option[String] = {
//        if(s.suffixesSet.contains(remaining)) {
//          Some(remaining)
//        } else {
//          val i = remaining.indexOf('.')
//          if(i == -1)
//            None
//          else
//            findLongest(remaining.substring(i + 1))
//        }
//      }
//      val bfSegRes = findLongest(findLongestOnThis)

//      // Trie implementation
//      val tRes = s.trie.longestMatch(findLongestOnThis.reverse).map(_.reverse)

      //      // SegmentTrie implementation
      val stRes = s.segmentTrie.longestMatch(findLongestOnThis)

//      if(stRes != tRes || bfSegRes != tRes) {
//        println("==============")
//        println(s.suffixes)
//        println(s"findLongestOnThis = $findLongestOnThis")
//        println(s"stRes = $stRes")
//        println(s"tRes = $tRes")
//        println(s"bfSegRes = $bfSegRes")
//      }

//      if(bfDbRes != tRes || bfSegRes != tRes) {
//        println(s.suffixes)
//        println("==============")
//        println(s"findLongestOnThis = $findLongestOnThis")
//        println(s"bfDbRes = $bfDbRes")
//        println(s"bfSegRes = $bfSegRes")
//        println(s"tRes = $tRes")
//      }
//      require(bfDbRes == tRes)
//      require(bfSegRes == tRes)
    }

  }
}
