
type range = (Double, Double)
type assigned = scala.collection.mutable.Map[range, Array[range]]

val productions: assigned = scala.collection.mutable.Map()

def production(f:range, w:Int):Unit = {
  val add:Array[range] = Array.fill(w){(0.0,0.0)}
  if(productions.contains(f)){
    productions(f) ++: add
  }else {
    productions += (f -> add)
  }
}

def shuffle_order(insert:range, place:range): Option[(range, Array[range])] = {
  productions foreach(size =>
    if(size._1._1>=insert._1 && size._1._2 < insert._2 && size._1 != place) {
        for ( a <- size._2
              if a.equals((0.0,0.0)) ){
          productions(size._1)(productions(size._1).lastIndexOf(a)) = insert
          return Some(size._1 -> productions(size._1))
        }
      for( a <- size._2){
        shuffle_order(a, size._1) match{
          case None =>
          case _ => productions(size._1)(productions(size._1).lastIndexOf(a)) = insert
            return Some(size._1 -> productions(size._1))

        }
      }
    }
    )
  None
}

def order(f:range, w:Int):String = {
  var results: String = ""
  var x: Option[(range, Array[range])] = None
  var a = 0
  for(a <- 1 to w){
      x = shuffle_order(f, (0,0))
      results = x match{
        case None => return "There's no available production suitable to your order."
        case Some(y) => results + "\nOrder added to: \n" + y._1.toString()+"\n"
      }
  }
  results
}

def ship_order(f:range):Unit = {
  productions foreach (size => {
    productions(size._1) = size._2.filter(word => word.equals(f))
    if(productions(size._1).isEmpty){
      productions -= size._1
    }
  }
    )
}

  def print_productions():Unit = {
    for(a <- productions){
      println("Production:\n")
      println(a._1.toString())
      println("Orders:\n")
      for(b <- a._2){
        println(b.toString()+"\n")
      }
    }

}



production((0.1, 0.2), 1)
production((0.2, 0.3), 1)

print_productions()

println("First:")
println(order((0, 0.4), 1))

print_productions()

println("Second:")
println(order((0, 0.3), 1))

print_productions()

println("Third")
println(order((0.1, 0.2), 1))

println("Delete")
ship_order((0,0.3))

print_productions()
