package MagicSquaredSquare.Engine.Framework

import org.bh.tools.base.abstraction.Integer
import org.bh.tools.base.async.OperationQueue
import MagicSquaredSquare.Engine.Framework.Gene as BaseGene

/**
 * @author Kyli Rouge
 * @since 2017-06-11
 */
open class GeneticAlgorithm
<
        Being : GeneticBeing<Gene>,
        out Gene: BaseGene,
        out Delegate : GeneticAlgorithmDelegate<Being, Gene>
> (
        val delegate: Delegate
) {
    fun start(populationSize: Integer = 100, endState: EndState) {
        val progenitors = delegate.makeProgenitors(populationSize)

        OperationQueue.main.enqueue()
    }



    companion object {
        sealed class EndState {
            class generationLimit(val maxGenerations: Integer): EndState()
            class fitnessLimit(val minFitness: GeneticFitness): EndState()
        }
    }
}