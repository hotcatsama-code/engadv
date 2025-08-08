package com.learn.englishadventures.game_logic

import kotlin.random.Random

object LevelGenerator {
    // Allocate 100 unique words for each game, disjoint across games.
    fun allocateForGames(): Triple<List<String>, List<String>, List<String>> {
        val all = (WordBank.easyWords + WordBank.mediumWords + WordBank.hardWords)
            .map { it.uppercase() }
            .distinct()
            .shuffled()
        val need = 300
        val pool = if (all.size >= need) all else generateSequence { all }.flatten().take(need).toList()
        val a = pool.subList(0, 100)
        val b = pool.subList(100, 200)
        val c = pool.subList(200, 300)
        return Triple(a, b, c)
    }

    fun generateScrambleLevelsFrom(words: List<String>): List<Pair<String, String>> = words.map { word ->
        var scrambled: String
        do {
            scrambled = word.toCharArray().apply { shuffle() }.concatToString()
        } while (scrambled == word)
        word to scrambled
    }

    fun generateChainLevels(count: Int): List<String> {
        val (a, _, _) = allocateForGames()
        return a.take(count)
    }

    fun generateLetterHopLevels(count: Int): List<LetterHopLevel> {
        val (_, _, c) = allocateForGames()
        return c.take(count).map { createLetterGridFor(it) }
    }

    fun generateLetterHopFor(word: String): LetterHopLevel = createLetterGridFor(word)

    data class LetterHopLevel(val target: String, val grid: List<List<Char>>)

    private fun createLetterGridFor(target: String, size: Int = 6): LetterHopLevel {
        val grid = MutableList(size) { MutableList(size) { randomChar() } }
        val horizontal = Random.nextBoolean()
        if (horizontal) {
            val row = Random.nextInt(size)
            val start = Random.nextInt(0, (size - target.length).coerceAtLeast(0) + 1)
            target.forEachIndexed { i, c -> if (start + i < size) grid[row][start + i] = c }
        } else {
            val col = Random.nextInt(size)
            val start = Random.nextInt(0, (size - target.length).coerceAtLeast(0) + 1)
            target.forEachIndexed { i, c -> if (start + i < size) grid[start + i][col] = c }
        }
        return LetterHopLevel(target, grid.map { it.toList() })
    }

    private fun randomChar(): Char = ('A'..'Z').random()
}
