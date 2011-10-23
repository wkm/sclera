package sclera.algo

import collection.immutable.TreeMap
import collection.mutable.{HashMap, DoubleLinkedList}

class MalformedIntervalException(lo: Int, hi: Int)
  extends Exception("Interval lo is larger than hi: "+(lo, hi))

case class Interval(
    val lo: Int,
    val hi: Int
) {

  if(lo > hi)
    throw new MalformedIntervalException(lo, hi)

  def ==(other: Interval) =
    other.lo == lo && other.hi == hi

  def <(other: Interval) =
    hi < other.lo

  def <=(other: Interval) =
    hi <= other.hi

  def >(other: Interval) =
    lo > other.hi

  def >=(other: Interval) =
    lo >= other.lo

  def contains(other: Interval) =
    other.lo < hi && other.hi > lo

  def overlaps(other: Interval) =
    other <= this && other >= this
}

/**
 * A mapping of intervals (integer tuple pairs) to a given annotation,
 * searchable by annotation, splittable by interval
 *
 * this is a crappy linear-time solution, but the overall parsing solution
 * is crappy anyway, c'est la vie
 */
case class SplittableIntervalMapping[A](implicit val ordering: Ordering[A]) {
  private case class Node[A] (
    interval: Interval,
    key: A
  ) {
    def expose = (interval, key)
  }

  private var intervalMap = new DoubleLinkedList[Node[A]]
  private val keyMap = new HashMap[A, List[Interval]]

  def getIntervals(key: A) =
    keyMap.get(key).getOrElse(List[Interval]())

  /**
   * finds all indices overlapping with the given interval
   */
  def indicesOverlappingWith(interval: Interval) = {
    var loIndex = -1
    var hiIndex = -1

    var index = 0
    for(elem <- intervalMap) {
      if(loIndex == -1 && elem.interval.hi > interval.lo)
        loIndex = index

      if(loIndex != -1 && hiIndex == -1 && elem.interval.lo < interval.hi)
        hiIndex = index

      index += 1
    }

    (loIndex, hiIndex)
  }

  def overlappingWith(interval: Interval) =
    indicesOverlappingWith(interval) match {
      case (lo,hi) =>
        intervalMap.slice(lo, hi).map( _.expose )
    }

  def intervals = intervalMap.map( _.expose )

  def addInterval(int: (Int, Int), key: A) {
    addInterval(Interval(int._1, int._2), key)
  }

  def addInterval(interval:Interval, key: A) {
    if(intervalMap.isEmpty) {
      intervalMap = DoubleLinkedList(new Node(interval, key))
      keyMap.update(key, List(interval))
    } else {
      // find 
    }
  }
}