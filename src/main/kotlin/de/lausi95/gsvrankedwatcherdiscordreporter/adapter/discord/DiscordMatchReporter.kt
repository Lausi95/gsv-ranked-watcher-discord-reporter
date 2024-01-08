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
  val pog = Emote("<:pog:853266160232431646>")
  val sadge = Emote("<:sadge:934050600696573952>")
}

object Colors {
  val red = Color(0xFF0000)
  val green = Color(0x00FF00)
}

@Component
private class DiscordMatchReporter(
  @Value("\${discord.webhook-url}") private val webhookUrl: String,
  @Value("\${discord.avatar-url}") private val avatarUrl: String,
) : MatchReporter {

  private val webhookClient = WebhookClient.withUrl(webhookUrl)

  override fun reportMatch(match: Match) {
    val color = if (match.win) Colors.green else Colors.red
    val emote = if (match.win) Emotes.pog else Emotes.sadge
    val title = "${match.formatParticipantNames()} played a match, and ${match.formatPronoun()} ${match.formatWin()} ${emote.value}"

    val embed = WebhookEmbedBuilder()
      .setColor(color.value)
      .setTitle(WebhookEmbed.EmbedTitle(title, null))
      .setDescription(match.formatParticipants())
      .build()

    val messageBuilder = WebhookMessageBuilder()
    messageBuilder.setUsername("Ranked Watcher")
    messageBuilder.setAvatarUrl(avatarUrl)
    messageBuilder.setContent(title)
    messageBuilder.addEmbeds(embed)

    webhookClient.send(messageBuilder.build())
  }
}
