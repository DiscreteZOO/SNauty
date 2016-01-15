package xyz.discretezoo.snauty

import scala.collection.mutable.ArrayBuffer

/**
 * Created by katja on 04/11/15.
 */
class Binding {
  @native
  def sparseNauty(indices: Array[Long], degrees: Array[Long], neighbours: Array[Long], mininvarlevel: Int, maxinvarlevel: Int, invararg: Int): String
  def callSparseNauty(adjacencyList: Map[Long, Set[Long]]): String = {
    val vertexIndices = new ArrayBuffer[Long]
    val vertexDegrees = new ArrayBuffer[Long]
    val neighboursList = new ArrayBuffer[Long]

    def appendVertex(neighbours: Set[Long]): Unit = {
      vertexIndices += neighboursList.length
      vertexDegrees += neighbours.size
      neighboursList ++= neighbours
    }

    adjacencyList.zipWithIndex.toSeq.sortWith(_._2 < _._2).foreach(z => appendVertex(z._1._2))
    sparseNauty(vertexIndices.toArray, vertexDegrees.toArray, neighboursList.toArray, 0, 1, 0)
  }
  System.loadLibrary("ScalaNauty")
}
