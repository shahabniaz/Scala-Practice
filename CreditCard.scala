
object CreditCard {

  def main(args: Array[String]): Unit = {

    def mask(creditCardNumber: String): String = {

      if (creditCardNumber.length == 6) {
        return (creditCardNumber)
      }
      else if(creditCardNumber.length == 0)
        {
          var emptystring = "Empty String"
          return(emptystring)
        }
      else {

        val firstdigit = 1
        val lastdigit = creditCardNumber.length - 4
        var toreplace = creditCardNumber.substring(firstdigit,lastdigit).replaceAll("[0-9]", "#")

        val finalnbr = creditCardNumber.substring(0, firstdigit).
          concat(toreplace).
          concat(creditCardNumber.substring(lastdigit, creditCardNumber.length))
        return (finalnbr)
      }
    }
    val a = ""
    mask(a)
  }
}
