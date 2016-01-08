package counter

import org.scalatest.{Matchers, FlatSpec}

/**
  * Created by manuMohan on 07/01/2016.
  */
class CounterSpec extends FlatSpec with Matchers {

  "Counter" should "add a to Counter" in {
    val c1 = new Counter
    c1.add("a")
    c1.get("a") should be === 1
  }

  "Counter" should "add b to Counter" in {
    val c1 = new Counter
    c1.add("b")
    c1.get("b") should be === 1
    c1.get("a") should be === 0
  }

  "Counter" should "add a and b to Counter" in {
    val c1 = new Counter
    c1.add("a")
    c1.add("a")
    c1.add("b")
    c1.add("b")
    c1.get("a") should be === 2
    c1.get("b") should be === 2
  }

  "Counter" should "add a 3 times and b 4 times to Counter" in {
    val c1 = new Counter
    c1.add("a", 3)
    c1.add("b", 4)
    c1.get("a") should be === 3
    c1.get("b") should be === 4
  }

  "Counter" should "return counts of items added" in {
    val c1 = new Counter
    c1.add("a")
    c1.add("b")
    c1.add("a", 3)
    c1.add("b", 4)
    val itemSpec = c1.get()

    itemSpec getOrElse ("a",0) should be === 4
    itemSpec getOrElse ("b",0) should be === 5
  }

  "Counter" should "return difference of 2 counters" in {
    val c1 = new Counter
    val c2 = new Counter
    c1.add("a")
    c1.add("b")
    c1.add("c")

    c2.add("a")
    c2.add("b")
    c2.add("d")

    val diff = c1.diff(c2)

    diff getOrElse ("a",Map.empty) getOrElse ("mine",-100) should be === 1
    diff getOrElse ("a",Map.empty) getOrElse ("other",-100) should be === 1
    diff getOrElse ("a",Map.empty) getOrElse ("diff",-100) should be === 0

    diff getOrElse ("b",Map.empty) getOrElse ("mine",-100) should be === 1
    diff getOrElse ("b",Map.empty) getOrElse ("other",-100) should be === 1
    diff getOrElse ("b",Map.empty) getOrElse ("diff",-100) should be === 0

    diff getOrElse ("c",Map.empty) getOrElse ("mine",-100) should be === 1
    diff getOrElse ("c",Map.empty) getOrElse ("other",-100) should be === 0
    diff getOrElse ("c",Map.empty) getOrElse ("diff",-100) should be === 1

    diff getOrElse ("d",Map.empty) getOrElse ("mine",-100) should be === 0
    diff getOrElse ("d",Map.empty) getOrElse ("other",-100) should be === 1
    diff getOrElse ("d",Map.empty) getOrElse ("diff",-100) should be === -1
  }
}
