package com.example.treedbtest.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "closure")
class Closure(

    @EmbeddedId
    var id: ClosureId,
) {

    @CreatedDate
    lateinit var createTime: LocalDateTime
    @LastModifiedDate
    lateinit var updateTime: LocalDateTime
}

@Embeddable
data class ClosureId (
    @Column(name = "current_index")
    var currentIndex: String,

    @Column(name = "root_index")
    var rootIndex: String,

    @Column(name = "ancestor")
    var ancestor: String,

    @Column(name = "descendant", nullable = true)
    var descendant: String?,

    @Column(name = "depth")
    var depth: Int
): Serializable {

}
