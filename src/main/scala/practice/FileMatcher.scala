package practice

import java.io.File

object FileMatcher {
  private val listFiles = new File("/Users/rhyno/study/scala/ScalaPractice/src/main/resources/practice").listFiles()

  def filesMatching(matcher: String => Boolean): Array[File] = {
    /**
     * strategy 1
     */
    //listFiles.filter{
      //file => matcher(file.getName)
    //}
    /**
     * strategy 2
     */
    for (file <- listFiles if matcher(file.getName)) yield file
  }

  def endsWith(query: String): Array[File] = {
    filesMatching(_.endsWith(query))
    //listFiles.filter(file => file.getName.endsWith(query))
  }

  def startWith(query: String): Array[File] = {
    filesMatching(_.startsWith(query))
    //listFiles.filter(file => file.getName.startsWith(query))
  }

  def containing(query: String): Array[File] = {
    filesMatching(_.contains(query))
    //listFiles.filter(file => file.getName.contains(query))
  }

  def main(args:Array[String]):Unit = {
    assert(endsWith("txt").length == 2)
    assert(startWith("abc").length == 0)
    assert(containing("1").length == 1)
  }
}
