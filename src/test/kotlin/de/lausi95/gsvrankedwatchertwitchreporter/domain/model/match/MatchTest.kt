package de.lausi95.gsvrankedwatchertwitchreporter.domain.model.match

import de.lausi95.gsvrankedwatchertwitchreporter.someString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MatchTest {

  @Test
  fun formatParticipantNames_oneName_returnsJustTheName() {
    val participantName = someString()
    val participantNames = listOf(participantName)

    val formattedName = participantNames.formatParticipantNames()

    assertThat(formattedName).isEqualTo(participantName)
  }

  @Test
  fun formatParticipantNames_twoNames_returnsNamesWithAnAndInBetween() {
    val participantName1 = someString()
    val participantName2 = someString()
    val participantNames: List<String> = listOf(participantName1, participantName2)

    val formattedName: String = participantNames.formatParticipantNames()

    assertThat(formattedName).isEqualTo("$participantName1 and $participantName2")
  }

  @Test
  fun formatParticipantNames_threeNames_returnsTheFirstNamesCommaSeparatedAndTheLastNameWithAnAnd() {
    val participantName1: String = someString()
    val participantName2: String = someString()
    val participantName3: String = someString()
    val participantNames: List<String> = listOf(participantName1, participantName2, participantName3)

    val formattedName: String = participantNames.formatParticipantNames()

    assertThat(formattedName).isEqualTo("$participantName1, $participantName2 and $participantName3")
  }

  @Test
  fun formatParticipantNames_noNames_returnsEmptyString() {
    val participantNames: List<String> = emptyList()

    val formattedName: String = participantNames.formatParticipantNames()

    assertThat(formattedName).isEqualTo("")
  }
}
