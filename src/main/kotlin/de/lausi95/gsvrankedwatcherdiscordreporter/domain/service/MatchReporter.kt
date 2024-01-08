package de.lausi95.gsvrankedwatcherdiscordreporter.domain.service

import de.lausi95.gsvrankedwatcherdiscordreporter.domain.model.match.Match

interface MatchReporter {

  fun reportMatch(match: Match)
}
