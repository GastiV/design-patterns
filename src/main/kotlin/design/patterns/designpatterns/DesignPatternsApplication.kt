package design.patterns.designpatterns

import design.patterns.designpatterns.builder.EmailAddress
import design.patterns.designpatterns.builder.PhoneNumber
import design.patterns.designpatterns.builder.createNotificationSettings
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class DesignPatternsApplication

fun main() {
    createNotificationSettings(EmailAddress("johndoe@example.com"), null)
    createNotificationSettings(EmailAddress("johndoe@example.com"), PhoneNumber("+1234567890"))
}
