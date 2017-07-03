package MagicSquaredSquare.Engine

import MagicSquaredSquare.Engine.Framework.*
import MagicSquaredSquare.Engine.Framework.GeneticAlgorithm.Companion.EndState.generationLimit
import MagicSquaredSquare.Engine.PossibleMagicSquaredSquare.Companion.Square
import org.bh.tools.base.abstraction.Integer
import org.bh.tools.base.collections.Set
import org.bh.tools.base.collections.extensions.reduceTo
import org.bh.tools.base.math.fractionValue
import org.bh.tools.base.math.geometry.IntegerRect
import org.bh.tools.base.math.max
import org.bh.tools.base.util.assertionFailure

/**
 * @author Kyli Rouge
 * @since 2017-06-11
 */
class MagicSquaredSquareEngine(val largestSubSquareSideLength: Integer) :
        GeneticAlgorithmDelegate<PossibleMagicSquaredSquare, Square> {

    private val backingEngine by lazy { GeneticAlgorithm(delegate = this) }

    override fun makeProgenitors(count: Integer): Set<PossibleMagicSquaredSquare> {
        return Set((0..count).map { PossibleMagicSquaredSquare(largestSubSquareSideLength) })
    }

    override fun makeChildren(parents: Set<PossibleMagicSquaredSquare>): Set<PossibleMagicSquaredSquare> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mutate(being: PossibleMagicSquaredSquare): PossibleMagicSquaredSquare {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun score(being: PossibleMagicSquaredSquare): GeneticFitness {
        return being.subSquares.reduceTo(0.5) { runningValue, subSquare ->
            var fitness = runningValue

            val distance = subSquare.distanceOfFarthestEdge(from = being.rectValue)
            fitness -= (distance.fractionValue / 10.0)

            val intersections: Set<IntegerRect> = being.subSquares.reduceTo(mutableSetOf()) { intersections, otherSubSquare ->
                if (otherSubSquare !== subSquare) {
                    val intersection = otherSubSquare.intersection(subSquare)
                    if (intersection != null) {
                        intersections.add(intersection)
                    }
                }

                /*return*/ intersections
            }
            val totalIntersectionArea = intersections.reduceTo(0.0) { totalIntersectionArea, intersection ->
                /*return*/ totalIntersectionArea + intersection.area
            }
            fitness -= totalIntersectionArea

            /* return */ fitness
        }
    }

    fun start() {
        backingEngine.start(populationSize = 100, endState = generationLimit(100))
    }
}


fun IntegerRect.distanceOfFarthestEdge(from: IntegerRect): Integer {
    if (from.contains(this)) {
        return 0
    }

    val xDistance: Integer
    val yDistance: Integer

    if (this.minX < from.minX) {
        xDistance = from.minX - this.minX
    } else if (this.maxX > from.maxX) {
        xDistance = this.maxX - from.maxX
    } else {
        assertionFailure("We already determined that from does not contain this")
        xDistance = 0
    }

    if (this.minY < from.minY) {
        yDistance = from.minY - this.minY

    } else if (this.maxY > from.maxY) {
        yDistance = this.maxY - from.maxY
    } else {
        assertionFailure("We already determined that from does not contain this")
        yDistance = 0
    }

    return max(xDistance, yDistance)
}
