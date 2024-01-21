package com.example.treedbtest.api

import com.example.treedbtest.domain.ScenarioIndex
import com.example.treedbtest.model.IndexDto
import com.example.treedbtest.service.ScenarioIndexService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class IndexApiDelegateImpl(
    private val scenarioIndexService: ScenarioIndexService
): IndexApiDelegate {

    override fun getIndexList(): ResponseEntity<List<IndexDto>> {
        return ResponseEntity.ok(
            scenarioIndexService.getScenarioIndexes().toList().map{
                IndexDto(
                    indexName = it.indexName,
                    indexType = it.indexType,
                    description = it.description
                )
            }
        )
    }
    override fun createIndex(indexDto: IndexDto): ResponseEntity<Unit> {
        val scenarioIndex = ScenarioIndex(
            indexName = indexDto.indexName,
            indexType = indexDto.indexType,
            description = indexDto.description
        )

        scenarioIndexService.createScenarioIndex(scenarioIndex)

        return ResponseEntity(HttpStatus.OK)
    }



}