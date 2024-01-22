package com.example.treedbtest.repository

import com.example.treedbtest.domain.Closure
import com.example.treedbtest.domain.ClosureId
import org.springframework.data.jpa.repository.JpaRepository

interface ClosureRepository : JpaRepository<Closure, ClosureId> {


    fun findAllByIdRootIndex(rootIndex: String): MutableList<Closure>
    fun findAllByIdAncestorIndex(ancestorIndex: String): MutableList<Closure>
    fun findAllByIdDescendantIndex(descendantIndex: String?): MutableList<Closure>

}