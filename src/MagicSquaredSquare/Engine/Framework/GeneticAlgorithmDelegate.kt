package MagicSquaredSquare.Engine.Framework

import org.bh.tools.base.abstraction.Fraction
import org.bh.tools.base.abstraction.Integer

import MagicSquaredSquare.Engine.Framework.Gene as BaseGene

/**
 * @author Kyli Rouge
 * @since 2017-06-06
 */
interface GeneticAlgorithmDelegate<Being : GeneticBeing<Gene>, out Gene: BaseGene> {
    fun makeProgenitors(count: Integer): Set<Being>
    fun makeChildren(parents: Set<Being>): Set<Being>
    fun mutate(being: Being): Being
    fun score(being: Being): GeneticFitness
}



typealias GeneticFitness = Fraction
