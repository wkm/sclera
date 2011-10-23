package sclera.algo

import collection.immutable.TreeMap

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
 */
class SplittableIntervalMapping[A] {

  

  
}