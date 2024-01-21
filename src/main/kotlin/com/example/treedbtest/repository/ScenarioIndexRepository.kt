package com.example.treedbtest.repository

import com.example.treedbtest.domain.Closure
import com.example.treedbtest.domain.ClosureId
import com.example.treedbtest.domain.ScenarioIndex
import org.springframework.data.jpa.repository.JpaRepository

interface ScenarioIndexRepository : JpaRepository<ScenarioIndex, String> {


}