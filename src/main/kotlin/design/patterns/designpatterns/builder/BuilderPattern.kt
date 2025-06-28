package design.patterns.designpatterns.builder

import design.patterns.designpatterns.builder.Subscription.Frequency
import design.patterns.designpatterns.builder.Subscription.Frequency.*
import design.patterns.designpatterns.builder.Subscription.Topic
import design.patterns.designpatterns.builder.Subscription.Topic.*
import kotlinx.collections.immutable.toImmutableList

// Builder interface declares product
// construction steps that are common to all
// types of builders.

interface INotificationSettingsBuilder{
    var enabled: Boolean
    fun addSubscription(dest: Destination, topic: Topic, freq: Frequency)
    fun build(): NotificationSettings
}

// Concrete builders provide different implementations
// of the construction steps. Concrete builders may
// produce products that don't follow the common
// interface.

class NotificationSettingsBuilder: INotificationSettingsBuilder{
    override var enabled: Boolean = false
    private val subscriptions = mutableListOf<Subscription>()

    override fun addSubscription(dest: Destination, topic: Topic, freq: Frequency) {
        subscriptions.add(Subscription(dest, topic, freq))
    }

    override fun build(): NotificationSettings = NotificationSettings(enabled, subscriptions.toImmutableList())

}

// Products are resulting objects. Products constructed
// by different builders don't have to belong to the
// same class hierarchy or interface.

data class NotificationSettings(val enabled: Boolean, val subscriptions: List<Subscription>)

data class Subscription(val destination: Destination, val topic: Topic, val frequence: Frequency) {
    enum class Topic { NEWS, ANALYTICS, SECURITY_ALERTS }
    enum class Frequency { IMMEDIATELY, DAILY, WEEKLY }
}

sealed interface Destination

@JvmInline
value class EmailAddress(val value: String) : Destination
@JvmInline
value class PhoneNumber(val value: String) : Destination

// Director class defines the order in which
// to execute the different steps of the
// building process.

fun createNotificationSettings(email: EmailAddress?, phone: PhoneNumber?): NotificationSettings {

    val builder: INotificationSettingsBuilder = NotificationSettingsBuilder()
    if (email != null) { builder.addSubscription(email, ANALYTICS, DAILY) }
    if (email != null) { builder.addSubscription(email, NEWS, WEEKLY) }
    if (phone != null) { builder.addSubscription(phone, SECURITY_ALERTS, IMMEDIATELY) }
    if (phone != null) { Subscription.Topic.entries.forEach{ builder.addSubscription(phone, it, IMMEDIATELY) } }

    builder.enabled = true

    val settings = builder.build()

    println("Notification Settings: $settings")

    return settings

}


