package de.lausi95.gsvrankedwatchertwitchreporter.domain.service

import de.lausi95.gsvrankedwatchertwitchreporter.domain.model.match.Match

interface MatchReporter {

  fun reportMatch(match: Match)
}
