package com.example.treedbtest.service

import com.example.treedbtest.domain.Closure
import com.example.treedbtest.domain.ClosureId
import com.example.treedbtest.domain.Member
import com.example.treedbtest.model.ClosureDto
import com.example.treedbtest.repository.ClosureRepository
import com.example.treedbtest.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class ClosureService(
    private val closureRepository: ClosureRepository
) {

    fun getClosure(closureId: ClosureId): Closure {
        return closureRepository.getById(closureId)
    }

    fun createClosure(closure: Closure) {
        closureRepository.save(closure)
    }

    fun deleteClosure(closureId: ClosureId) {
        closureRepository.deleteById(closureId)
    }

    fun getClosures(): List<Closure> {
        return closureRepository.findAll()

    }

    fun getClosuresByRootIndex(rootIndex: String): MutableList<Closure> {
        return closureRepository.findAllByIdRootIndex(rootIndex)
    }

    fun getClosuresByAncestor(ancestor: String): MutableList<Closure> {
        return closureRepository.findAllByIdAncestorIndex(ancestor)
    }

    fun getClosuresByDescendant(descendant: String?): MutableList<Closure> {
        return closureRepository.findAllByIdDescendantIndex(descendant)
    }
}