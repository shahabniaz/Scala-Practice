object Ordinal {

  def fromNumber(n: Int): String = {
    val ans = "th" // generic value
    if (n % 100 / 10 == 1)
    {
      return (n+ ans)
    } //tens should be "th"
    else if(n == 0)
    {
      return ("0")
    }
    else (n % 10) match {
      case 1 => return(n + "st")
      case 2 => return(n + "nd")
      case 3 => return(n + "rd")
      case _ => return(n + ans)
    }
  }
}