package de.lausi95.gsvrankedwatcherdiscordreporter.application

import de.lausi95.gsvrankedwatcherdiscordreporter.domain.model.match.Match
import de.lausi95.gsvrankedwatcherdiscordreporter.domain.service.MatchReporter
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
