package com.example.treedbtest.service

import com.example.treedbtest.domain.ScenarioIndex
import com.example.treedbtest.repository.ScenarioIndexRepository
import org.springframework.stereotype.Service

@Service
class ScenarioIndexService(
    private val scenarioIndexRepository: ScenarioIndexRepository
) {

    fun getScenarioIndexes(): List<ScenarioIndex> {
        return scenarioIndexRepository.findAll()
    }

    fun createScenarioIndex(scenarioIndex: ScenarioIndex) {
        scenarioIndexRepository.save(scenarioIndex)
    }

    fun getScenarioIndex(indexName: String) {
        scenarioIndexRepository.findById(indexName)
    }
}