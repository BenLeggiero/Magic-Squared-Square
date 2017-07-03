package MagicSquaredSquare.Engine.Framework

import org.bh.tools.base.abstraction.Integer
import org.bh.tools.base.collections.Index
import org.bh.tools.base.struct.IntegerRange

import MagicSquaredSquare.Engine.Framework.Gene as BaseGene

/**
 * @author Kyli Rouge
 * @since 2017-06-06
 */
interface GeneticBeing<out Gene: BaseGene> {
    val totalSplittableSize: Integer
    fun getSplit(range: IntegerRange): List<Gene>
    fun get(geneAt: Index): Gene
}


val GeneticBeing<*>.isSplittable: Boolean get() = this.totalSplittableSize > 1
