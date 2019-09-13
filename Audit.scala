import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Level, Logger}


object Audit {

  def main(args: Array[String]) {

    val sc = SparkSession
      .builder()
      .appName("Audit Trail")
      .master("local")
      .getOrCreate()

    var auditlog = ""
    var auditloglen = 0
    var  numofocc = 0
    var prevpos = 0
    var codedata = ""
    var myvar2 = 0
    var newpos = 0
    var lennum = 0
    var loopvar = 0
    var lent = 0
    var inrloop = 0
    /* type coln is varray(500) of = ""
     type fn is varray(500) of = ""
     type oval is varray(500) of = ""
     type nval is varray(500) of = ""
     fname fn := fn()
     colname coln := coln()
     newvalue oval := oval()
     oldvalue nval := nval()*/
    var getlength = 0
    var columnname = ""
    //var displayname coln := coln()
    var buscompname =""
    var childbc =""
    var opdate =""
    var auditrowid =""
    var employeeid = ""
    var oprtion   =""
    var recordid = ""
    var displayfield =""
    var fieldname =""
    var fieldnamecount = 0
    var currentstate =""
    var prevstate =""
    var totalrecords = 0
    var printloop = 0

    //INSTR FUNCTION
    val instr2 : (String, String) => Int = (str, sub) => str.indexOfSlice(sub)


    Logger.getLogger("org").setLevel(Level.ERROR)
    Logger.getLogger("akka").setLevel(Level.ERROR)


    println("Output")
    // Load the text into a Spark RDD, which is a distributed representation of each line of text

        val sauditrdd = sc.read.format("csv").option("header","true").load("src/main/resources/queryhive14599.txt")

        //sauditrdd.show(false)
sauditrdd.registerTempTable("s_audit_item")


sauditrdd.foreach
                 {
                   //Accessing Row & Declaring Variables
                   row =>
                   auditrowid = row.getAs("ROW_ID")
                   buscompname = row.getAs("BUSCOMP_NAME")
                   childbc = row.getAs("CHILD_BC_NAME")
                   opdate = row.getAs("OPERATION_DT")
                   employeeid = row.getAs("USER_ID")
                   recordid = row.getAs("RECORD_ID")
                   oprtion = row.getAs("OPERATION_CD")
                   auditlog = row.getAs("AUDITTRAILSTR")
                   auditloglen = auditlog.length()
                   numofocc = auditloglen - auditlog.replace("*","").length
                   loopvar =1
                   prevpos = 0
                   lent =0
                   inrloop = 1
                   totalrecords = 0
                   getlength = 0
                   printloop = 0


                    // for (loopvar <- 1 to numofocc)
                      //  {
                          myvar2 = auditlog.indexOf("*", 0) + 1
                          newpos = prevpos+lent
                          lennum = myvar2-lent-prevpos-1
                          lent = (auditlog.substring(newpos,lennum)).toInt
                          codedata = auditlog.substring(myvar2,lent+2)
                          currentstate = codedata.substring(0,1)

                              if (loopvar == 1)
                                {
                                  inrloop = codedata.substring(1,2).toInt
                                  totalrecords = inrloop
                                  prevstate = currentstate
                                }
                              else if (prevstate== "C" ||  prevstate == "O" || prevstate == "N" ||  prevstate== "J" || prevstate == "K" || prevstate == "L")
                                {
                                    if (inrloop > getlength)
                                      {
                                        if (prevstate == "C" || prevstate == "K")
                                          {
                                            columnname = codedata
                                            fieldnamecount = 0
                                          }
                                      }
                                }
                              else
                                {
                                  print("not found")
                                }


                   //}



                 }


  }



}