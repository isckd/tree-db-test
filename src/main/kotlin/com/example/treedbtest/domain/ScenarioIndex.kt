package com.example.treedbtest.domain

import com.example.treedbtest.model.IndexDto
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "scenario_index")
class ScenarioIndex(

    @Id
    @Column(name = "index_name")
    var indexName: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "index_type")
    var indexType: IndexDto.IndexType,

    @Column(name = "description")
    var description: String? = null,



    ) {

    @CreatedDate
    lateinit var createTime: LocalDateTime
    @LastModifiedDate
    lateinit var updateTime: LocalDateTime
}


enum class IndexType(val dtoString: String) {
    ANALYSIS_INDEX("analysis_index"),
    CONDITION_INDEX("condition_index"),
    FLOW_INDEX("flow_index"),
    QUESTION_INDEX("question_index"),
    ANSWER_INDEX("answer_index"),
}
