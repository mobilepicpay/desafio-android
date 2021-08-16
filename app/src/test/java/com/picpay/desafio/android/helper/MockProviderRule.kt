package com.picpay.desafio.android.helper

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.test.mock.MockProvider
import org.koin.test.mock.Provider

class MockProviderRule private constructor(private val mockProvider: Provider<*>): TestRule {

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                MockProvider.register(mockProvider)
                base?.evaluate()
            }
        }
    }

    companion object {
        fun create(mockProvider: Provider<*>) : MockProviderRule {
            return MockProviderRule(mockProvider)
        }
    }
}