package cen.alpha.random.generator

import Hash._

// code from Splittable random
final class SplitMix private(seed: Long, gamma: Long) {

  import SplitMix._

  def getLong: Long = mix64(seed)

  def getInt: Int = mix32(seed)

  def next: SplitMix = new SplitMix(seed + gamma, gamma)

  def split: SplitMix = {
    val newGamma = mixGamma(seed + gamma)
    new SplitMix(mix64(seed) + newGamma, newGamma)
  }

}

object SplitMix {

  private val goldenGamma: Long = 0x9e3779b97f4a7c15L

  @inline private def mix64(x: Long): Long = {
    var z = x
    z = (z ^ (z >>> 30)) * 0xbf58476d1ce4e5b9L
    z = (z ^ (z >>> 27)) * 0x94d049bb133111ebL
    z ^ (z >>> 31)
  }

  @inline private def mix32(x: Long): Int = {
    var z = x
    z = (z ^ (z >>> 33)) * 0x62a9d9ed799705f5L
    z = (z ^ (z >>> 28)) * 0xcb24d0a5c88c35b3L
    (z >>> 32).toInt
  }

  @inline private def mixGamma(x: Long): Long = {
    val z = murmurHash3(x) | 1L // force to be odd
    val n = java.lang.Long.bitCount(z ^ (z >>> 1)) // ensure enough transitions
    if (n < 24) z ^ 0xaaaaaaaaaaaaaaaaL
    else z
  }

  def init(seed: Long, gamma: Long = goldenGamma): SplitMix = {
    new SplitMix(seed + gamma, gamma)
  }

}
