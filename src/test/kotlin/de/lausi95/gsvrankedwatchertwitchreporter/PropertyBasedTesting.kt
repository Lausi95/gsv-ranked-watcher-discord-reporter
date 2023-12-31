package de.lausi95.gsvrankedwatchertwitchreporter

import kotlin.random.Random

const val StringChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"

fun someInt(min: Int = 0, max: Int = 100): Int {
  return Random.nextInt(min, max)
}

fun someString(length: Int = 10): String {
  return (1..length)
    .map { Random.nextInt(0, StringChars.length) }
    .map(StringChars::get)
    .joinToString("")
}

fun someBoolean(): Boolean {
  return someInt() % 2 == 1
}
