package app.models

import kotlinx.serialization.Serializable

@Serializable
enum class Manufacturer {
    AIRBUS,
    BOEING,
    EMBRAER,
    CESSNA,
    CIRRUS,
    BOMBARDIER,
    ATR,
    COMAC
}