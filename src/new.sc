
type range = (Double, Double)
type assigned = scala.collection.mutable.Map[range, Array[range]]
var productions: assigned = scala.collection.mutable.Map()

def production(f:range, w:Int):Unit = {
  val add:Array[range] = Array.fill(w){(0.0,0.0)}
  if(productions.contains(f)){
    productions(f) ++: add
  }else {
    productions += (f -> add)
  }
}

def shuffle_order(insert:range, place:range): Option[(range, Array[range])] = {
  val add:Array[range] = Array.fill(1){(0.0, 0.0)}
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
          case _ => {productions(size._1)(productions(size._1).lastIndexOf(a)) = insert
            return Some(size._1 -> productions(size._1))
          }
        }
      }
    }
    )
  return None
}

def order(f:range, w:Int):String = {
  var results: String = ""
  var x: Option[(range, Array[range])] = None
  var a = 0
  for(a <- 1 to w){
      x = shuffle_order(f, (0,0))
      results = x match{
        case None => return "Nie znaleziono odpowiedniej dostępnej produkcji"
        case Some(y) => results + "\nPlace\n" + y._1.toString()+"\n"
      }
  }
  return results
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
      println("Do:\n")
      println(a._1.toString())
      println("W:\n")
      for(b <- a._2){
        println(b.toString()+"\n")
      }
    }

}



production((0.1, 0.2), 1)
production((0.2, 0.3), 1)


print(productions.toString())

println("First:")
println(order((0, 0.4), 1))
println(productions.toString())

for(a <- productions){
  println("Do:\n")
  println(a._1.toString())
  println("W:\n")
  for(b <- a._2){
    println(b.toString()+"\n")
  }
}

println("Second:")
println(order((0, 0.3), 1))

for(a <- productions){
  println("Do:\n")
  println(a._1.toString())
  println("W:\n")
  for(b <- a._2){
    println(b.toString()+"\n")
  }
}

println("Third")
println(order((0.1, 0.2), 1))

println("Delete")
ship_order((0,0.3))

print_productions()
