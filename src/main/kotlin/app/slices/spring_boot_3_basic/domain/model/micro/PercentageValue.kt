package app.slices.spring_boot_3_basic.domain.model.micro

import arrow.core.left

class PercentageValue(
    num: Int,
) {

    companion object {
        const val MIN = 0
        const val MAX = 100

        fun validate(num: Int): Pair<Boolean, String?> = when {
            num < MIN -> Pair(false, "Percentage should be min $MIN")
            num > MAX -> Pair(false, "Percentage should be max $MAX")
            else -> Pair(true, null)
        }
    }

    init {
        require(validate(num).first) { "Percentage should be between $MIN and $MAX" }
    }

}