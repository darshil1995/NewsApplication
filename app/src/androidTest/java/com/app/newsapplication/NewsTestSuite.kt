package com.app.newsapplication

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class,
    FragmentTests::class
)
class NewsTestSuite