package cen.alpha.random.generator

final class PCG32 private(state: Long, increment: Long) {

  import PCG32.multiplier

  @inline private def advanceState(increment: Long): Long =
    multiplier * state + increment

  def get: Int = {
    val xss: Int = (((state >>> 18) ^ state) >>> 27).toInt
    val rot: Int = (state >>> 59).toInt
    Integer.rotateRight(xss, rot)
  }

  def next: PCG32 = {
    val state = advanceState(increment)
    new PCG32(state, increment)
  }

}

object PCG32 {

  // see http://www.pcg-random.org
  // code https://github.com/imneme/pcg-c-basic/blob/master/pcg_basic.c

  private val multiplier: Long = 6364136223846793005L

  private val increment: Long = 1442695040888963407L

  @inline private def advanceState(state: Long, increment: Long): Long =
    multiplier * state + increment

  def init(seed: Long, sequence: Long = increment): PCG32 = {
    val increment = (sequence << 1L) | 1L
    val state = advanceState(increment + seed, increment)
    new PCG32(state, increment)
  }

}
