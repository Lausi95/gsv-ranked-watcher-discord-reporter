package de.lausi95.gsvrankedwatchertwitchreporter.adapter.http

import de.lausi95.gsvrankedwatchertwitchreporter.application.MatchApplicationService
import de.lausi95.gsvrankedwatchertwitchreporter.domain.model.match.Match
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/matches")
private class MatchController(private val matchApplicationService: MatchApplicationService){

  @PostMapping
  fun postMatch(@RequestBody match: Match) {
    matchApplicationService.reportMatch(match)
  }
}
