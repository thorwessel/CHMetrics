package models

enum class Labels(val string: String) {
    WALLET_LOAD("wallet_load"),
    EXPORTS("exports"),
    INTEGRATIONS("integrations"),
    TRANSACTIONS("transactions"),
    MISSING_RECEIPTS("missing_receipt"),
    HIGH_PRIORITY("High priority"),
    MEDIUM_PRIORITY("Medium priority"),
    LOW_PRIORITY("Low priority")
}