package de.lausi95.gsvrankedwatchertwitchreporter.adapter.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import de.lausi95.gsvrankedwatchertwitchreporter.application.MatcApplicationService
import de.lausi95.gsvrankedwatchertwitchreporter.domain.model.match.Match
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
private class KafkaListeners(
  private val objectMapper: ObjectMapper,
  private val matchApplicationService: MatcApplicationService,
) {

  @KafkaListener(topics = ["match_played"])
  fun matchReported(message: String) {
    val match = objectMapper.readValue(message, Match::class.java)
    matchApplicationService.reportMatch(match)
  }
}
