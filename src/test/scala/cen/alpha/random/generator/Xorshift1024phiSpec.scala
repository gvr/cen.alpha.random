package cen.alpha.random.generator

import it.unimi.dsi.util.XorShift1024StarPhiRandomGenerator
import org.scalatest.{Matchers, WordSpec}

import scala.annotation.tailrec

class Xorshift1024phiSpec extends WordSpec with Matchers {

  "Xorshift1024phi" should {

    "match the XorShift1024StarPhiRandomGenerator implementation" in {
      val state = Array[Long](
        -8924956236279331811L, -6645523562763818923L, 6572057659653707831L, 4938671967096216094L,
        3458459993260519232L, -2581239258607468510L, 3916182429352585840L, -6142490363719071048L,
        -4266174017505289453L, 6839126324828817723L, 7572038374137779520L, 8723688107328792229L,
        819591658532496040L, 324108011427370141L, -5075132425047734838L, 2902007988922235075L)
      val reference = new XorShift1024StarPhiRandomGenerator()
      reference.setState(copy(state), 0)

      val generator = Xorshift1024phi.init(state)
      val generator1 = matchSequence(generator.next, reference, 100)
      generator1.getInt shouldBe reference.nextInt()
      matchSequence(generator1.next, reference, 100)
    }

    "match the XorShift1024StarPhiRandomGenerator split" in {
      val state = Array[Long](
        -8924956236279331811L, -6645523562763818923L, 6572057659653707831L, 4938671967096216094L,
        3458459993260519232L, -2581239258607468510L, 3916182429352585840L, -6142490363719071048L,
        -4266174017505289453L, 6839126324828817723L, 7572038374137779520L, 8723688107328792229L,
        819591658532496040L, 324108011427370141L, -5075132425047734838L, 2902007988922235075L)
      val reference = new XorShift1024StarPhiRandomGenerator()
      reference.setState(copy(state), 0)

      var generator = Xorshift1024phi.init(state)
      generator = matchSequence(generator.next, reference, 100)
      reference.nextLong()
      generator.next.getLong shouldBe reference.nextLong()
      val reference1 = reference.split()
      val generator1 = generator.next.split
      generator1.next.getLong shouldBe reference1.nextLong()
    }


  }

  @tailrec private def matchSequence(generator: Xorshift1024phi,
                                     reference: XorShift1024StarPhiRandomGenerator,
                                     n: Int): Xorshift1024phi = {
    if (n > 0) {
      generator.getLong shouldBe reference.nextLong()
      matchSequence(generator.next, reference, n - 1)
    }
    else generator
  }

  @inline private def copy(array: Array[Long]): Array[Long] = {
    val result = Array.ofDim[Long](array.length)
    System.arraycopy(array, 0, result, 0, array.length)
    result
  }

}
