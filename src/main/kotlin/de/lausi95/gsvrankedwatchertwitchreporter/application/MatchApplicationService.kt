package de.lausi95.gsvrankedwatchertwitchreporter.application

import de.lausi95.gsvrankedwatchertwitchreporter.domain.model.match.Match
import de.lausi95.gsvrankedwatchertwitchreporter.domain.service.MatchReporter
import org.springframework.stereotype.Component

@Component
class MatchApplicationService(private val matchReporter: MatchReporter) {

  fun reportMatch(match: Match) {
    matchReporter.reportMatch(match)
  }
}
