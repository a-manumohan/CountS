package counter

/**
  * Created by manuMohan on 07/01/2016.
  */
class Counter {
  /**
    * @param item to be added
    * @param count of the items
    */
  def this(item: Object, count: Int) = {
    this
    this.add(item, count)
  }

  /**
    * create counter with reference
    * @param reference
    */
  def this(reference: Map[Object, Int]) = {
    this
    referenceMap = reference
  }

  var itemMap: Map[Object, Int] = Map()
  var referenceMap: Map[Object, Int] = Map()

  /**
    *
    * @param item to be added to counter
    */
  def add(item: Object): Unit = {
    add(item, 0)
  }

  /**
    * @param item to be added
    * @param count number of item to be added
    */
  def add(item: Object, count: Int): Unit = {
    var itemCount: Int = itemMap getOrElse(item, 0)
    if (itemCount >= referenceMap.getOrElse(item, Int.MaxValue)) return
    itemCount = itemCount + (count match {
      case 0 => 1
      case x if x < 0 => 1
      case x if x > 0 => count
    })
    itemMap = itemMap - item + (item -> itemCount)
  }

  /**
    * @param item
    * @return the count of item, 0 if not present
    */
  def get(item: Object): Int = {
    itemMap getOrElse(item, 0)
  }

  /**
    *
    * @return item counts map
    */
  def get(): Map[Object, Int] = {
    itemMap
  }

  def diff(c1: Counter): Map[Object, Map[String, Int]] = {
    val newItemMap = (itemMap.keys ++ c1.itemMap.keys).foldLeft(Map.empty[Object, Map[String, Int]])((r: Map[Object, Map[String, Int]], o: Object) => {
      r +
        (o -> Map("mine" -> get(o), "other" -> c1.get(o), "diff" -> (get(o) - c1.get(o))))
    })
    newItemMap
  }
}
