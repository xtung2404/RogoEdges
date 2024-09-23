package items

enum class RogoFunction(
 val id: String
) {
    OVERVIEW("Overview"),
    GATEWAY_MANUFACTURER("Gateway manufacturer"),
    TESTING_WIFI_AND_BLE("Testing Wifi & BLE");
    companion object {
        fun getFunctions(): List<RogoFunction> {
            return listOf<RogoFunction>(
                OVERVIEW,
                GATEWAY_MANUFACTURER,
                TESTING_WIFI_AND_BLE
            )
        }
    }
}

