package cen.alpha.random.generator

import org.scalatest.{Matchers, WordSpec}
import spire.random.rng.{PcgSeed64, PcgXshRr64_32}

class PCG32Spec extends WordSpec with Matchers {

  "PCG32" should {

    "match Spire implementation" in {
      val expect1 = PcgXshRr64_32.fromSeed(PcgSeed64(123L, 0L))
      val actual1 = PCG32.init(123L, 0L)
      actual1.get shouldBe expect1.nextInt()
      actual1.next.get shouldBe expect1.nextInt()
      actual1.next.next.get shouldBe expect1.nextInt()
      actual1.next.next.next.get shouldBe expect1.nextInt()

      val expect2 = PcgXshRr64_32.fromSeed(PcgSeed64(0x853c49e6748fea9L, 1442695040888963407L))
      val actual2 = PCG32.init(0x853c49e6748fea9L, 1442695040888963407L)
      actual2.get shouldBe expect2.nextInt()
      actual2.next.get shouldBe expect2.nextInt()
      actual2.next.next.get shouldBe expect2.nextInt()
      actual2.next.next.next.get shouldBe expect2.nextInt()
    }

  }

}
