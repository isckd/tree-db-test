package com.example.treedbtest.api

import com.example.treedbtest.domain.Closure
import com.example.treedbtest.domain.ClosureId
import com.example.treedbtest.model.ClosureDto
import com.example.treedbtest.service.ClosureService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ClosureApiDelegateImpl(
    private val closureService: ClosureService
): ClosureApiDelegate {

    override fun getClosureList(): ResponseEntity<List<ClosureDto>> {
        return ResponseEntity.ok(
            closureService.getClosures().toList().map {
                ClosureDto(
                    currentIndex = it.id.currentIndex,
                    rootIndex = it.id.rootIndex,
                    ancestorIndex = it.id.ancestor,
                    descendantIndex = it.id.descendant,
                    depth = it.id.depth
                )
            }
        )
    }

    override fun createClosure(closureDto: ClosureDto): ResponseEntity<Unit>  {

        val closureId = ClosureId(
            currentIndex = closureDto.currentIndex,
            rootIndex = closureDto.rootIndex,
            ancestor = closureDto.ancestorIndex,
            descendant = closureDto.descendantIndex,
            depth = closureDto.depth
        )

        closureService.createClosure(
            Closure(closureId)
        )

        return ResponseEntity(HttpStatus.OK)
    }
}