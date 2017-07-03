package MagicSquaredSquare.Engine

import MagicSquaredSquare.Engine.Framework.Gene
import MagicSquaredSquare.Engine.Framework.GeneticBeing
import MagicSquaredSquare.Engine.PossibleMagicSquaredSquare.Companion.Square
import org.bh.tools.base.abstraction.Integer
import org.bh.tools.base.collections.Index
import org.bh.tools.base.collections.extensions.subList
import org.bh.tools.base.math.geometry.*
import org.bh.tools.base.math.geometry.IntegerPoint.Companion.zero
import org.bh.tools.base.math.int32Value
import org.bh.tools.base.math.integerValue
import org.bh.tools.base.struct.IntegerRange



/**
 * A possible Magic Squared Square. This is a squared square whose sub-squares all have consecutive side lengths.
 *
 * See also: <a href="https://en.wikipedia.org/wiki/Squaring_the_square">P</a>
 *
 * @author Kyli Rouge
 * @since 2017-06-08
 */
class PossibleMagicSquaredSquare(
        val sideLength: Integer
) : GeneticBeing<Square> {

    val subSquares by lazy {
        List(size = sideLength.int32Value, init = {
            Square(origin = zero, size = it.integerValue)
        })
    }

    override val totalSplittableSize: Integer
        get() = sideLength

    override fun getSplit(range: IntegerRange): List<Square> =
            subSquares.subList(range)

    override fun get(geneAt: Index): Square = subSquares[geneAt]


    val rectValue: IntegerRect by lazy { IntegerRect(origin = zero, size = IntegerSize(squareSide = sideLength)) }


    companion object {
        class Square(origin: IntegerPoint, size: Integer)
            : IntegerRect(origin = origin, size = IntegerSize(squareSide = size)),
                Gene
    }
}