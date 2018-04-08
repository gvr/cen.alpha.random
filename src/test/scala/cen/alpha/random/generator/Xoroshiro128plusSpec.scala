package cen.alpha.random.generator

import it.unimi.dsi.util.XoRoShiRo128PlusRandomGenerator
import org.scalatest.{Matchers, WordSpec}

import scala.annotation.tailrec

class Xoroshiro128plusSpec extends WordSpec with Matchers {

  "Xoroshiro" should {

    "match the XoRoShiRo implementation" in {
      val s0 = 1234567L
      val s1 = 6348538749L
      val reference = new XoRoShiRo128PlusRandomGenerator()
      reference.setState(Array(s0, s1))

      val generator = Xoroshiro128plus.init(s0, s1)
      val generator1 = matchSequence(generator, reference, 1000)
      generator1.getInt shouldBe reference.nextInt()
      matchSequence(generator1.next, reference, 10)
    }

    "match the XoRoShiRo split" in {
      val s0 = 12334567L
      val s1 = 63485348749L
      val expect = new XoRoShiRo128PlusRandomGenerator()
      expect.setState(Array(s0, s1))

      val actual = Xoroshiro128plus.init(s0, s1)
      actual.getLong shouldBe expect.nextLong()

      val expect1 = expect.split()
      val actual1 = actual.next.split

      actual.next.getLong shouldBe expect.nextLong()

      actual1.getLong shouldBe expect1.nextLong()
      actual1.next.getLong shouldBe expect1.nextLong()
    }

  }

  @tailrec private def matchSequence(generator: Xoroshiro128plus,
                                     reference: XoRoShiRo128PlusRandomGenerator,
                                     n: Int): Xoroshiro128plus = {
    if (n > 0) {
      generator.getLong shouldBe reference.nextLong()
      matchSequence(generator.next, reference, n - 1)
    }
    else generator
  }

}
