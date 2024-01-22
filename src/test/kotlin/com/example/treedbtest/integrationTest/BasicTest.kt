package com.example.treedbtest.integrationTest

import com.example.treedbtest.model.IndexDto
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import javax.transaction.Transactional

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BasicTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper
): DescribeSpec({

    describe("basic of basic test") {
        it("index post test"){
            mockMvc.post("/index/create") {
                contentType = MediaType.APPLICATION_JSON
                content = """
                    {
                      "indexName": "index01",
                      "indexType": "ANALYSIS_INDEX",
                      "description": "description01"
                    }
                """.trimIndent()
            }
                .andExpect {
                    status { isOk() }
                }
        }

        it("index get list test") {
            mockMvc.get("/index/get/list") {}
                .andExpect {
                    status { isOk() }
                    jsonPath("$[0].indexName") {value("index01") }
                    jsonPath("$[0].indexType") {value("ANALYSIS_INDEX")}
                    jsonPath("$[0].description") {value("description01")}
                }

        }
    }

    describe("index forAll test") {
        forAll(
            row("""
                {
                    "indexName": "index01",
                    "indexType": "ANALYSIS_INDEX",
                    "description": "description01"
                }
            """.trimIndent()),
            row("""
                {
                    "indexName": "index02",
                    "indexType": "ANALYSIS_INDEX",
                    "description": "description02"
                }
            """.trimIndent()),
            row("""
                {
                    "indexName": "index03",
                    "indexType": "ANALYSIS_INDEX",
                    "description": "description03"
                }
            """.trimIndent())
        ) {
            jsonData ->
            mockMvc.post("/index/create") {
                contentType = MediaType.APPLICATION_JSON
                content = jsonData
            }
                .andExpect {
                    status { isOk() }
                }
        }

        val resp: List<IndexDto> = objectMapper.readValue( mockMvc.get("/index/get/list") {}
            .andExpect { status { isOk() } }
            .andReturn().response.contentAsString )

        forAll(
            row("index01", "ANALYSIS_INDEX", "description01"),
            row("index02", "ANALYSIS_INDEX", "description02"),
            row("index03", "ANALYSIS_INDEX", "description03")
        ) { indexName, expectedIndexType, expectedDescription ->
            resp.find { it.indexName == indexName }?.apply {
                indexType.value shouldBe expectedIndexType
                description shouldBe expectedDescription
            }
        }

    }

})