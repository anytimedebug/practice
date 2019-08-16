package com.practice.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Transactional
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = [JpaTestConfig::class])
class BazRepositoryTest {
    @Autowired
    private lateinit var fooRepository: FooRepository
    @Autowired
    private lateinit var entityManager: EntityManager

    @Test
    fun `test save`() {
        // arrange
        val foo = Foo(0)

        // act
        val actual = fooRepository.save(foo)

        // assert
        assertThat(actual).isEqualTo(foo)
    }

    @Test
    fun `test saveAll`() {
        // arrange
        val foo1 = Foo(0)
        val foo2 = Foo(0)
        val foo3 = Foo(0)

        // act
        val actual = fooRepository.saveAll(listOf(foo1, foo2, foo3))

        // assert
        assertThat(actual).isNotEmpty
        assertThat(actual.size).isEqualTo(3)
    }

    @Test
    fun `test findAll`() {
        // arrange
        entityManager.persist(Foo(0))
        entityManager.persist(Foo(0))
        entityManager.persist(Foo(0))

        // act
        val actual = fooRepository.findAll()

        // assert
        assertThat(actual).isNotEmpty
        assertThat(actual.size).isEqualTo(3)
    }
}