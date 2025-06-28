package design.patterns.designpatterns.builder

// Director class defines the order in which
// to execute the different steps of the
// building process.


// Builder interface declares product
// construction steps that are common to all
// types of builders.

// Concrete builders provide different implementations
// of the construction steps. Concrete builders may
// produce products that don't follow the common
// interface.

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


