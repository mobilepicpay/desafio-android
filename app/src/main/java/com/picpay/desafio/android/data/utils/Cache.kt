package com.picpay.desafio.android.data.utils

class Cache: CacheInterface {
    private val cache = HashMap<Any, Any>()

    override val size: Int
        get() = cache.size

    override fun set(key: Any, value: Any) {
        this.cache[key] = value
    }

    override fun get(key: Any): Any? {
        return this.cache[key]
    }

    override fun remove(key: Any): Any? {
        return this.cache.remove(key)
    }

    override fun clear() {
        this.cache.clear()
    }
}
