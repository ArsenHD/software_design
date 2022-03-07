package cli.commands

import cli.TestWithFakerAndEnv
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class EchoTestWithFakerAndEnv : TestWithFakerAndEnv() {

    @Test
    fun runWithStrongQuoting() {
        val content = faker.random.randomString()
        val command = EchoCommand(content, false)
        val res = command.run("", env).forNextCommand()
        assertEquals(content, res)
    }

    @Test
    fun runWithStrongQuotingAndEnv() {
        val content = "${ faker.random.randomString() }$$envKey${ faker.random.randomString() }"
        val command = EchoCommand(content, false)
        val res = command.run("", env).forNextCommand()
        assertEquals(content, res)
    }

    @Test
    fun runWithWeakQuotingAndEnv() {
        val content = "${ faker.random.randomString() }$$envKey${ faker.random.randomString() }"
        val command = EchoCommand(content, true)
        val res = command.run("", env).forNextCommand()
        assertEquals(env.replaceVars(content), res)
    }
}
