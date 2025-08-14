package com.example.domain.ai

data class GeminiSummaryRequest(
    val dateKey: String,
    val lines: List<String>
)

data class GeminiSummaryResponse(
    val dateKey: String,
    val summary: String,
    val inferenceMillis: Long?
)

interface GeminiClient {
    suspend fun summarize(request: GeminiSummaryRequest): GeminiSummaryResponse
}