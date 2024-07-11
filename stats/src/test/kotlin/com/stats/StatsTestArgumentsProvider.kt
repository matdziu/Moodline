package com.stats

import com.domain.entities.DiaryEntry
import com.domain.entities.Emotion
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import java.time.LocalDateTime
import java.util.stream.Stream

class StatsTestArgumentsProvider : ArgumentsProvider {

    override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
        return Stream.of(
            provideStatsTestArguments1(),
            provideStatsTestArguments2(),
            provideStatsTestArguments3(),
            provideStatsTestArguments4(),
            provideStatsTestArguments5(),
        )
    }

    private fun provideStatsTestArguments1(): Arguments {
        return Arguments.of(
            StatsTestArgument(
                entriesPerMonthAndYearList = listOf(
                    EntriesPerMonthAndYear(
                        entries = listOf(
                            DiaryEntry(
                                id = "1",
                                emotion = Emotion.Rad,
                                entryText = "testEntryText1",
                                createdAt = LocalDateTime.of(2024, 1, 10, 12, 0),
                            ),
                            DiaryEntry(
                                id = "2",
                                emotion = Emotion.Good,
                                entryText = "testEntryText2",
                                createdAt = LocalDateTime.of(2024, 1, 9, 2, 0),
                            )
                        ),
                        monthAndYear = MonthAndYear(1, 2024)
                    )
                ),
                expectedStatsItemsList = listOf(
                    StatsItem(
                        label = "Jan, 2024",
                        radCount = 1,
                        goodCount = 1,
                        mehCount = 0,
                        badCount = 0,
                        awfulCount = 0,
                    )
                )
            )
        )
    }

    private fun provideStatsTestArguments2(): Arguments {
        return Arguments.of(
            StatsTestArgument(
                entriesPerMonthAndYearList = listOf(
                    EntriesPerMonthAndYear(
                        entries = listOf(
                            DiaryEntry(
                                id = "1",
                                emotion = Emotion.Rad,
                                entryText = "testEntryText1",
                                createdAt = LocalDateTime.of(2024, 1, 10, 12, 0),
                            ),
                            DiaryEntry(
                                id = "2",
                                emotion = Emotion.Good,
                                entryText = "testEntryText2",
                                createdAt = LocalDateTime.of(2024, 1, 9, 2, 0),
                            )
                        ),
                        monthAndYear = MonthAndYear(1, 2024)
                    ),
                    EntriesPerMonthAndYear(
                        entries = listOf(
                            DiaryEntry(
                                id = "3",
                                emotion = Emotion.Awful,
                                entryText = "testEntryText3",
                                createdAt = LocalDateTime.of(2020, 10, 10, 12, 0),
                            ),
                            DiaryEntry(
                                id = "4",
                                emotion = Emotion.Awful,
                                entryText = "testEntryText4",
                                createdAt = LocalDateTime.of(2020, 10, 9, 2, 0),
                            )
                        ),
                        monthAndYear = MonthAndYear(10, 2020)
                    )
                ),
                expectedStatsItemsList = listOf(
                    StatsItem(
                        label = "Jan, 2024",
                        radCount = 1,
                        goodCount = 1,
                        mehCount = 0,
                        badCount = 0,
                        awfulCount = 0,
                    ),
                    StatsItem(
                        label = "Oct, 2020",
                        radCount = 0,
                        goodCount = 0,
                        mehCount = 0,
                        badCount = 0,
                        awfulCount = 2,
                    )
                )
            )
        )
    }

    private fun provideStatsTestArguments3(): Arguments {
        return Arguments.of(
            StatsTestArgument(
                entriesPerMonthAndYearList = listOf(
                    EntriesPerMonthAndYear(
                        entries = listOf(
                            DiaryEntry(
                                id = "1",
                                emotion = Emotion.Rad,
                                entryText = "testEntryText1",
                                createdAt = LocalDateTime.of(2024, 1, 10, 12, 0),
                            ),
                            DiaryEntry(
                                id = "2",
                                emotion = Emotion.Rad,
                                entryText = "testEntryText2",
                                createdAt = LocalDateTime.of(2024, 1, 9, 2, 0),
                            )
                        ),
                        monthAndYear = MonthAndYear(1, 2024)
                    ),
                    EntriesPerMonthAndYear(
                        entries = listOf(
                            DiaryEntry(
                                id = "3",
                                emotion = Emotion.Rad,
                                entryText = "testEntryText3",
                                createdAt = LocalDateTime.of(2020, 10, 10, 12, 0),
                            ),
                            DiaryEntry(
                                id = "4",
                                emotion = Emotion.Rad,
                                entryText = "testEntryText4",
                                createdAt = LocalDateTime.of(2020, 10, 9, 2, 0),
                            )
                        ),
                        monthAndYear = MonthAndYear(10, 2020)
                    )
                ),
                expectedStatsItemsList = listOf(
                    StatsItem(
                        label = "Jan, 2024",
                        radCount = 2,
                        goodCount = 0,
                        mehCount = 0,
                        badCount = 0,
                        awfulCount = 0,
                    ),
                    StatsItem(
                        label = "Oct, 2020",
                        radCount = 2,
                        goodCount = 0,
                        mehCount = 0,
                        badCount = 0,
                        awfulCount = 0,
                    )
                )
            )
        )
    }


    private fun provideStatsTestArguments4(): Arguments {
        return Arguments.of(
            StatsTestArgument(
                entriesPerMonthAndYearList = listOf(
                    EntriesPerMonthAndYear(
                        entries = listOf(
                            DiaryEntry(
                                id = "1",
                                emotion = Emotion.Rad,
                                entryText = "testEntryText1",
                                createdAt = LocalDateTime.of(2024, 1, 10, 12, 0),
                            ),
                            DiaryEntry(
                                id = "2",
                                emotion = Emotion.Rad,
                                entryText = "testEntryText2",
                                createdAt = LocalDateTime.of(2024, 1, 9, 2, 0),
                            )
                        ),
                        monthAndYear = MonthAndYear(1, 2024)
                    ),
                    EntriesPerMonthAndYear(
                        entries = listOf(
                            DiaryEntry(
                                id = "3",
                                emotion = Emotion.Rad,
                                entryText = "testEntryText3",
                                createdAt = LocalDateTime.of(2020, 10, 10, 12, 0),
                            ),
                            DiaryEntry(
                                id = "4",
                                emotion = Emotion.Rad,
                                entryText = "testEntryText4",
                                createdAt = LocalDateTime.of(2020, 10, 9, 2, 0),
                            )
                        ),
                        monthAndYear = MonthAndYear(10, 2020)
                    ),
                    EntriesPerMonthAndYear(
                        entries = listOf(
                            DiaryEntry(
                                id = "5",
                                emotion = Emotion.Meh,
                                entryText = "testEntryText5",
                                createdAt = LocalDateTime.of(2025, 5, 10, 9, 15),
                            ),
                        ),
                        monthAndYear = MonthAndYear(5, 2025)
                    )

                ),
                expectedStatsItemsList = listOf(
                    StatsItem(
                        label = "Jan, 2024",
                        radCount = 2,
                        goodCount = 0,
                        mehCount = 0,
                        badCount = 0,
                        awfulCount = 0,
                    ),
                    StatsItem(
                        label = "Oct, 2020",
                        radCount = 2,
                        goodCount = 0,
                        mehCount = 0,
                        badCount = 0,
                        awfulCount = 0,
                    ),
                    StatsItem(
                        label = "May, 2025",
                        radCount = 0,
                        goodCount = 0,
                        mehCount = 1,
                        badCount = 0,
                        awfulCount = 0,
                    ),
                )
            )
        )
    }

    private fun provideStatsTestArguments5(): Arguments {
        return Arguments.of(
            StatsTestArgument(
                entriesPerMonthAndYearList = listOf(
                    EntriesPerMonthAndYear(
                        entries = listOf(
                            DiaryEntry(
                                id = "1",
                                emotion = Emotion.Bad,
                                entryText = "testEntryText1",
                                createdAt = LocalDateTime.of(2024, 1, 10, 12, 0),
                            ),
                        ),
                        monthAndYear = MonthAndYear(1, 2024)
                    )
                ),
                expectedStatsItemsList = listOf(
                    StatsItem(
                        label = "Jan, 2024",
                        radCount = 0,
                        goodCount = 0,
                        mehCount = 0,
                        badCount = 1,
                        awfulCount = 0,
                    )
                )
            )
        )
    }
}

data class StatsTestArgument(
    val entriesPerMonthAndYearList: List<EntriesPerMonthAndYear>,
    val expectedStatsItemsList: List<StatsItem>,
)