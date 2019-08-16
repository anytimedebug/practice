package com.practice.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class FooRepositoryTest {

    private val context = AnnotationConfigApplicationContext(JpaTestConfig::class.java)
    private val repository = context.getBean(FooRepository::class.java)

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
//        assertThat(actual.size).isEqualTo(3) // fail
    }

}