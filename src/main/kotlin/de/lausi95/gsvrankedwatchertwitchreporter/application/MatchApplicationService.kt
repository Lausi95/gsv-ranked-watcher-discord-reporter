package de.lausi95.gsvrankedwatchertwitchreporter.application

import de.lausi95.gsvrankedwatchertwitchreporter.domain.model.match.Match
import de.lausi95.gsvrankedwatchertwitchreporter.domain.service.MatchReporter
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class MatchApplicationService(private val matchReporter: MatchReporter) {

  private val log = LoggerFactory.getLogger(MatchApplicationService::class.java)

  fun reportMatch(match: Match) {
    log.info("Reporting Match: $match")
    matchReporter.reportMatch(match)
  }
}
