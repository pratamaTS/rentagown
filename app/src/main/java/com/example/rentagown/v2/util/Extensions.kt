package com.example.rentagown.v2.util

import androidx.annotation.RestrictTo

/**
 * Creates a 64 bit hash string from the given charsequences
 * @hide
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP_PREFIX)
fun CharSequence.to64BitHash(): Long {
    var result = -0x340d631b7bdddcdbL
    val len = this.length
    for (i in 0 until len) {
        result = result xor this[i].toLong()
        result *= 0x100000001b3L
    }
    return result
}