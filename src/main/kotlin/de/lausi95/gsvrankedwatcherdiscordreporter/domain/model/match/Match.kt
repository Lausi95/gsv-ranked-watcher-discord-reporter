package de.lausi95.gsvrankedwatcherdiscordreporter.domain.model.match

data class Participant(
  val summonerId: String,
  val summonerName: String,
  val champion: String,
  val position: String,
  val kills: Int,
  val deaths: Int,
  val assists: Int,
  val quadraKill: Boolean,
  val pentaKill: Boolean,
) {

  fun format(): String {
    return """
      **$summonerName**
      Champion: $champion
      Position: $position
      KDA: $kills/$deaths/$assists
    """.trimIndent()
  }
}

data class Match(
  val matchId: String,
  val win: Boolean,
  val participants: List<Participant>) {

  fun formatParticipantNames(): String {
    return participants.map { it.summonerName }.formatParticipantNames()
  }

  fun formatParticipants(): String {
    return participants.map { it.format() }.joinToString { "\n\n" }
  }

  fun formatWin(): String {
    return if (win) "won" else "lost"
  }

  fun formatPronoun() : String {
    return if (participants.size > 1) "they" else "he/she"
  }
}

fun List<String>.formatParticipantNames(): String {
  if (isEmpty())
    return ""

  return listOf(dropLast(1).joinToString(", "), last())
    .filter{ it.isNotBlank() }
    .joinToString(" and ")
}
