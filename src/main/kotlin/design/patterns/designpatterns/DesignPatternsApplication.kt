package design.patterns.designpatterns

import design.patterns.designpatterns.builder.EmailAddress
import design.patterns.designpatterns.builder.NotificationSettings
import design.patterns.designpatterns.builder.PhoneNumber
import design.patterns.designpatterns.builder.Subscription
import design.patterns.designpatterns.builder.Subscription.*
import design.patterns.designpatterns.builder.Subscription.Frequency.*
import design.patterns.designpatterns.builder.Subscription.Topic.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DesignPatternsApplication

fun main() {
    createNotificationSettings()
}

fun createNotificationSettings(): NotificationSettings {
    return NotificationSettings(
        enabled = true,
        subscriptions = listOf(
            Subscription(EmailAddress("johndoe@example.com"), NEWS, WEEKLY),
            Subscription(PhoneNumber("+1234567890"), ANALYTICS, DAILY)
        )
    )
}
