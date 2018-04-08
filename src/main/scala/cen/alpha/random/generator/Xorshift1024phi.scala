package cen.alpha.random.generator

import Hash._

final class Xorshift1024phi private(state: Array[Long], index: Int) {

  import Xorshift1024phi._

  def getLong: Long = state(index) * multiplier

  def getInt: Int = (getLong >>> 32).toInt

  def next: Xorshift1024phi = {
    val newState = copy(state)
    val nextIndex = (index + 1) & 15
    val s0 = state(index)
    val s1 = state(nextIndex) ^ (state(nextIndex) << 31)
    newState(nextIndex) = s1 ^ s0 ^ (s1 >>> 11) ^ (s0 >>> 30)
    new Xorshift1024phi(newState, nextIndex)
  }

  def split: Xorshift1024phi = {
    val newState = state.map(murmurHash3)
    new Xorshift1024phi(newState, index)
  }

}

object Xorshift1024phi {

  private val multiplier = -7046029254386353133L

  @inline private def copy(array: Array[Long]): Array[Long] = {
    val result = Array.ofDim[Long](array.length)
    System.arraycopy(array, 0, result, 0, array.length)
    result
  }

  def init(state: Array[Long]): Xorshift1024phi = new Xorshift1024phi(copy(state), 0)

}
