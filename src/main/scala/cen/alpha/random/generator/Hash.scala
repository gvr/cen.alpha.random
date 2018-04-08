package cen.alpha.random.generator

object Hash {

  @inline def murmurHash3(x: Long): Long = {
    var z = x
    z = (z ^ (z >>> 33)) * 0xff51afd7ed558ccdL
    z = (z ^ (z >>> 33)) * 0xc4ceb9fe1a85ec53L
    z ^ (z >>> 33)
  }

}
