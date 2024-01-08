package de.lausi95.gsvrankedwatcherdiscordreporter.adapter.http

import de.lausi95.gsvrankedwatcherdiscordreporter.application.MatchApplicationService
import de.lausi95.gsvrankedwatcherdiscordreporter.domain.model.match.Match
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
