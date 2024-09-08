package com.example.dagger2

class MockStorage : Storage {
    private val map: MutableMap<String, String?> = mutableMapOf()

    override fun getString(key: String): String? {
        return map[key]
    }

    override fun setString(key: String, value: String?) {
        map[key] = value
    }
}
