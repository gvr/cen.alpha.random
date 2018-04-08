package cen.alpha.random.generator

import Hash._

final class Xoroshiro128plus private(s0: Long, s1: Long) {

  def getLong: Long = s0 + s1

  def getInt: Int = (getLong >>> 32).toInt

  def next: Xoroshiro128plus = {
    val x1 = s1 ^ s0
    val newS0 = java.lang.Long.rotateLeft(s0, 55) ^ x1 ^ x1 << 14
    val newS1 = java.lang.Long.rotateLeft(x1, 36)
    new Xoroshiro128plus(newS0, newS1)
  }

  def split: Xoroshiro128plus = {
    new Xoroshiro128plus(murmurHash3(s0), murmurHash3(s1))
  }

}

object Xoroshiro128plus {

  def init(s0: Long, s1: Long): Xoroshiro128plus = new Xoroshiro128plus(s0, s1)

}
