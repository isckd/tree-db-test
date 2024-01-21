package com.example.treedbtest.repository

import com.example.treedbtest.domain.Closure
import com.example.treedbtest.domain.ClosureId
import org.springframework.data.jpa.repository.JpaRepository

interface ClosureRepository : JpaRepository<Closure, ClosureId> {


    fun findAllByIdRootIndex(rootIndex: String): MutableList<Closure>
    fun findAllByIdAncestor(ancestor: String): MutableList<Closure>
    fun findAllByIdDescendant(descendant: String?): MutableList<Closure>

}