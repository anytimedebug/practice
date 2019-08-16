package com.practice.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class BarRepositoryTest {

    @Autowired
    private lateinit var repository: FooRepository

    @Test
    fun `test save`() {
        // arrange
        val foo = Foo(0)

        // act
        val actual = repository.save(foo)

        // assert
        assertThat(actual).isNotNull
        assertThat(actual).isEqualTo(foo)
    }

    @Test
    fun `test saveAll`() {
        // arrange
        val foo1 = Foo(0)
        val foo2 = Foo(0)
        val foo3 = Foo(0)

        // act
        val actual = repository.saveAll(listOf(foo1, foo2, foo3))

        // assert
        assertThat(actual).isNotEmpty
        assertThat(actual.size).isEqualTo(3)
    }

    @Test
    fun `test findAll`() {
        // arrange
        val foo1 = Foo(0)
        val foo2 = Foo(0)
        val foo3 = Foo(0)
        repository.saveAll(listOf(foo1, foo2, foo3))

        // act
        val actual = repository.findAll()

        // assert
        assertThat(actual).isNotEmpty
        assertThat(actual.size).isEqualTo(3)
    }

}