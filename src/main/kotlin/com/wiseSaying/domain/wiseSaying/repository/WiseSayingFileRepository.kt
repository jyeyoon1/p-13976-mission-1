package com.wiseSaying.domain.wiseSaying.repository

import com.wiseSaying.domain.wiseSaying.entity.WiseSaying
import com.wiseSaying.standard.util.JsonUtil
import java.nio.file.Path

class WiseSayingFileRepository : WiseSayingRepository {

    val dirPath: Path
        get() {
            return Path.of("db/wiseSaying")
        }

    override fun save(wiseSaying: WiseSaying) : Int {
        mkdirIfNotExistPath()
        if(wiseSaying.isNew()) wiseSaying.id=genNextId()
        dirPath.resolve("${wiseSaying.id}.json").toFile().writeText(wiseSaying.jsonStr)
        return wiseSaying.id
    }

    override fun delete(wiseSaying: WiseSaying) {
        dirPath.resolve("${wiseSaying.id}.json").toFile().delete()
    }

    override fun isEmpty(): Boolean {
        return dirPath.toFile()
            .listFiles()
            ?.filter { it.name != "data.json"}
            ?.none { it.name.endsWith(".json")}
            ?: true
    }

    override fun findAll():  List<WiseSaying> {
        return dirPath.toFile()
            .listFiles()
            ?.filter { it.name != "data.json" }
            ?.filter { it.name.endsWith(".json") }
            ?.map { it.readText() }
            ?.map(WiseSaying.Companion::fromJsonStr)
            ?.sortedByDescending { it.id }
            .orEmpty()
    }

    override fun findById(id: Int): WiseSaying? {
        return dirPath.resolve("${id}.json")
            .toFile()
            .takeIf { it.exists() }
            ?.readText()
            ?.let(WiseSaying.Companion::fromJsonStr)
    }

    override fun clear() {
        dirPath.toFile().deleteRecursively()
    }

    override fun build() {
        mkdirIfNotExistPath()
        val wiseSayings = findAll()

        JsonUtil.listToString(wiseSayings.map { it.jsonStr })
            .let {
                dirPath
                    .resolve("data.json")
                    .toFile()
                    .writeText(it)
            }
    }
    internal fun saveLastId(lastId: Int) {
        mkdirIfNotExistPath()
        dirPath.resolve("lastId.txt")
            .toFile()
            .writeText(lastId.toString())
    }

    internal fun getLastId(): Int {
        return try {
            dirPath.resolve("lastId.txt").toFile().readText().toInt()
        } catch (e: Exception) {
            0
        }
    }

    private fun genNextId(): Int {
        return (getLastId() + 1).also {
            saveLastId(it)
        }
    }

    internal fun mkdirIfNotExistPath(){
        dirPath.toFile().run {
            if(!exists()) {
                mkdirs()
            }
        }
    }
}