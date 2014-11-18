package core

import scala.collection.IndexedSeqLike
import collection.mutable.{Builder, ArrayBuffer}
import scala.collection.generic.{SeqFactory, GenericTraversableTemplate, GenericCompanion, CanBuildFrom}
import scala.reflect.ClassTag
import scala.collection.TraversableView.Coll
import scala.annotation.tailrec

/**
 * Created by ernestrc on 18/11/2014.
 */

final class Series[A : ClassTag] private (val length: Int, items:Array[A])
  extends IndexedSeq[A]{

  override protected[this] def newBuilder = Series.newBuilder[A]

  def apply(idx:Int):A =
    if(idx < 0 || length <= idx) throw new IndexOutOfBoundsException
    else items(idx)

}

//TODO implement abstract generic Series with concrete methods for String,Int,...
object Series {

  def empty[A: ClassTag]: Series[A] = newBuilder[A].result()

  private def fromSeq[A:ClassTag](buf:Seq[A]):Series[A] = {
    val items : Array[A] = new Array(buf.length)
    for(i <- 0 until buf.length)
      items(i) = buf(i)
    new Series(items.length,items)
  }

  def newBuilder[A : ClassTag]:collection.mutable.Builder[A,Series[A]] =
    new ArrayBuffer mapResult fromSeq[A]

  implicit def canBuildFrom[A : ClassTag]: CanBuildFrom[Series[_], A, Series[A]] =
    new CanBuildFrom[Series[_], A, Series[A]]{
      def apply() = newBuilder[A]
      def apply(from: Series[_]) = newBuilder[A]
    }

  def apply[A:ClassTag](elems: A*):Series[A] = this.fromSeq(elems)

}