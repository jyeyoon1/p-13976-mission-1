package com.ll

import com.ll.global.container.Container
import kotlin.test.Test
import kotlin.test.assertContains
import org.assertj.core.api.Assertions.assertThat

class WiseSayingTest {

    private val repository = Container.wiseSayingRepository
    @Test
    fun `명언 종료`() {
        val result = TestRunner.run("""
            종료
        """)
        assertContains(result, "명령)")
        assertContains(result, "명언 앱을 종료합니다.")
    }

    @Test
    fun `명언 작성`() {
        val result = TestRunner.run("""
            등록
            나의 죽음을 적들에게 알리지 말라.
            이순신
        """)
        assertContains(result, "명언 :")
        assertContains(result, "작가 :")
        assertContains(result, "1번 명언이 등록되었습니다.")
    }

    @Test
    fun `명언 목록`() {
        val result = TestRunner.run(
            """
            등록
            나의 죽음을 적들에게 알리지 말라.
            이순신
            등록
            천재는 99%의 노력과 1%의 영감이다.
            에디슨
            목록
        """
        )
        assertContains(result, "2 / 에디슨 / 천재는 99%의 노력과 1%의 영감이다.")
        assertContains(result, "1 / 이순신 / 나의 죽음을 적들에게 알리지 말라.")
    }

        @Test
        fun `명언 삭제`(){
            val result = TestRunner.run("""
                등록
                나의 죽음을 적들에게 알리지 말라.
                이순신
                등록
                천재는 99%의 노력과 1%의 영감이다.
                에디슨
                삭제?id=1
            """)
            assertContains(result, "1번 명언이 삭제되었습니다.")
        }

        @Test
        fun `명언 삭제 - 없는 번호에 대한 예외처리`(){
            val result = TestRunner.run("""
                등록
                나의 죽음을 적들에게 알리지 말라.
                이순신
                등록
                천재는 99%의 노력과 1%의 영감이다.
                에디슨
                삭제?id=1
                삭제?id=1
            """)
            assertContains(result, "1번 명언이 삭제되었습니다.")
            assertContains(result, "1번 명언은 존재하지 않습니다.")
        }

    @Test
    fun `명언 수정 - 없는 번호 예외처리 추가`(){
        val result = TestRunner.run("""
                등록
                나의 죽음을 적들에게 알리지 말라.
                이순신
                등록
                천재는 99%의 노력과 1%의 영감이다.
                에디슨
                수정?id=3
                수정?id=2
                현재를 사랑하라.
                작자미상
                목록
            """)
        assertContains(result, "3번 명언은 존재하지 않습니다.")
        assertContains(result, "명언(기존) : 천재는 99%의 노력과 1%의 영감이다.")
        assertContains(result, "작가(기존) : 에디슨")
        assertContains(result, "2 / 작자미상 / 현재를 사랑하라.")
        assertContains(result, "1 / 이순신 / 나의 죽음을 적들에게 알리지 말라.")
    }

    @Test
    fun `파일을 통한 영속성`(){
        TestRunner.run("""
                등록
                나의 죽음을 적들에게 알리지 말라.
                이순신
                등록
                천재는 99%의 노력과 1%의 영감이다.
                에디슨
        """)
    }

    @Test
    fun `명언 빌드`(){
        val result = TestRunner.run("""
                등록
                나의 죽음을 적들에게 알리지 말라.
                이순신
                등록
                천재는 99%의 노력과 1%의 영감이다.
                에디슨
                빌드
        """)
        assertContains(result, "data.json 파일의 내용이 갱신되었습니다.")
    }

    @Test
    fun `명언 검색`() {
        val result = TestRunner.run(
            """
            등록
            나의 죽음을 적들에게 알리지 말라.
            이순신
            등록
            천재는 99%의 노력과 1%의 영감이다.
            에디슨
            목록?keywordType=content&keyword=천재
        """
        )
        assertThat(result)
            .contains("검색타입 : content")
            .contains("검색어 : 천재")
            .contains("2 / 에디슨 / 천재는 99%의 노력과 1%의 영감이다.")
            .doesNotContain("1 / 이순신 / 나의 죽음을 적들에게 알리지 말라.")
    }

    @Test
    fun `페이징 - 1 페이지`() {
        TestRunner.makeSampleData(10)
        val result = TestRunner.run("""
            목록
        """)
        assertThat(result)
            .doesNotContain("5 / 작자미상 / 명언 5")
            .doesNotContain("4 / 작자미상 / 명언 4")
            .doesNotContain("3 / 작자미상 / 명언 3")
            .doesNotContain("2 / 작자미상 / 명언 2")
            .doesNotContain("1 / 작자미상 / 명언 1")
            .contains("10 / 작자미상 / 명언 10")
            .contains("9 / 작자미상 / 명언 9")
            .contains("8 / 작자미상 / 명언 8")
            .contains("7 / 작자미상 / 명언 7")
            .contains("6 / 작자미상 / 명언 6")
            .contains("페이지 : [1] / 2")
    }

    @Test
    fun `페이징 - 2 페이지`() {
        TestRunner.makeSampleData(10)
        val result = TestRunner.run("""
            목록?page=2
        """)
        assertThat(result)
            .contains("5 / 작자미상 / 명언 5")
            .contains("4 / 작자미상 / 명언 4")
            .contains("3 / 작자미상 / 명언 3")
            .contains("2 / 작자미상 / 명언 2")
            .contains("1 / 작자미상 / 명언 1")
            .doesNotContain("10 / 작자미상 / 명언 10")
            .doesNotContain("9 / 작자미상 / 명언 9")
            .doesNotContain("8 / 작자미상 / 명언 8")
            .doesNotContain("7 / 작자미상 / 명언 7")
            .doesNotContain("6 / 작자미상 / 명언6")
            .contains("페이지 : 1 / [2]")
    }
}

