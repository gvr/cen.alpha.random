package cen.alpha.random.generator

import java.util.SplittableRandom

import org.scalatest.{Matchers, WordSpec}

class SplitMixSpec extends WordSpec with Matchers {

  "SplitMix" should {

    "match Splittable Random" in {
      val expect = new SplittableRandom(123L)
      val actual = SplitMix.init(123L)
      actual.getLong shouldBe expect.nextLong()
      actual.next.getInt shouldBe expect.nextInt()
      actual.next.next.getLong shouldBe expect.nextLong()
      actual.next.next.next.getLong shouldBe expect.nextLong()
      actual.next.next.next.next.getLong shouldBe expect.nextLong()
    }

    "match split for Splittable Random" in {
      val expect = new SplittableRandom(123456789L)
      val actual = SplitMix.init(123456789L)
      actual.getLong shouldBe expect.nextLong()
      val expect1 = expect.split() // this advances SplittableRandom two times
      actual.next.next.next.getLong shouldBe expect.nextLong()
      val actual1 = actual.next.split // split
      actual1.getLong shouldBe expect1.nextLong()
      actual1.next.getLong shouldBe expect1.nextLong()
      actual1.next.next.getLong shouldBe expect1.nextLong()
    }

  }

}
