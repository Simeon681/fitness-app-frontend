package com.example.fitnessapp1

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

object ScheduledTask {
    private val scheduler: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()

    fun performTask(
        taskToRun: () -> Unit,
        hour: Int,
        minute: Int,
        second: Int
    ) {
        val timeNow = LocalDateTime.now()
        val targetTime = timeNow.withHour(hour).withMinute(minute).withSecond(second)
        val initialDelay = calculateInitialDelay(timeNow, targetTime)

        scheduler.schedule(
            { taskToRun() },
            initialDelay,
            TimeUnit.SECONDS
        )
    }

    private fun calculateInitialDelay(now: LocalDateTime, targetTime: LocalDateTime): Long {
        val delayInSeconds = targetTime.atZone(ZoneId.systemDefault()).toEpochSecond() -
                now.atZone(ZoneId.systemDefault()).toEpochSecond()

        return if (delayInSeconds >= 0) delayInSeconds else 0
    }
}