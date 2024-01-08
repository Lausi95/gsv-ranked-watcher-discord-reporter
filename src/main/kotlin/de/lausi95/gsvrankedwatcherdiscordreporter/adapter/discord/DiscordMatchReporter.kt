package de.lausi95.gsvrankedwatcherdiscordreporter.adapter.discord

import club.minnced.discord.webhook.WebhookClient
import club.minnced.discord.webhook.send.WebhookEmbed
import club.minnced.discord.webhook.send.WebhookEmbedBuilder
import club.minnced.discord.webhook.send.WebhookMessageBuilder
import de.lausi95.gsvrankedwatcherdiscordreporter.domain.model.match.Match
import de.lausi95.gsvrankedwatcherdiscordreporter.domain.service.MatchReporter
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

data class Emote(val value: String) {
  private val emoteRegex = "<:.+?:\\d+?>".toRegex()

  init {
    require(emoteRegex.matches(value)) { "Emote does not match format." }
  }
}

data class Color(val value: Int)

object Emotes {
  private val pog = Emote("<:pog:853266160232431646>")
  private val sadge = Emote("<:sadge:934050600696573952>")

  fun select(win: Boolean) = if (win) pog else sadge
}

object Colors {
  private val red = Color(0xFF0000)
  private val green = Color(0x00FF00)

  fun select(win: Boolean) = if (win) green else red
}

private fun Match.formatTitle(): String {
  val participantNames = formatParticipantNames()
  val pronouns = formatPronoun()
  val win = formatWin()
  val emoteValue = Emotes.select(this.win).value

  return "$participantNames played a match, and $pronouns $win $emoteValue"
}

fun Match.createLeagueOfGraphsLink(): String {
  return "https://www.leagueofgraphs.com/de/match/euw/$matchId"
}

@Component
private class DiscordMatchReporter(
  @Value("\${discord.webhook-url}") private val webhookUrl: String,
  @Value("\${discord.avatar-url}") private val avatarUrl: String,
) : MatchReporter {

  private val webhookClient = WebhookClient.withUrl(webhookUrl)

  override fun reportMatch(match: Match) {
    val titleText = match.formatTitle()
    val body = match.formatParticipants()

    /* TODO find out, why the description is not displayed.
    val leagueOfGraphsLink = match.createLeagueOfGraphsLink()
    val embedTitle = WebhookEmbed.EmbedTitle(titleText, leagueOfGraphsLink)
    val embed = WebhookEmbedBuilder()
      .setColor(Colors.select(match.win).value)
      .setTitle(embedTitle)
      .setDescription(match.formatParticipants())
      .build()
    */

    val message = WebhookMessageBuilder()
      .setUsername("Ranked Watcher")
      .setAvatarUrl(avatarUrl)
      .setContent("$titleText\n\n$body")
      // .addEmbeds(embed)
      .build()

    webhookClient.send(message)
  }
}
