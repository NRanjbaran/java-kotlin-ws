/**
 * This package contains service classes responsible for managing and coordinating business logic.
 */
package service

import PublishedInfoManager
import WriterInfo
import org.slf4j.LoggerFactory
import repository.HibernateFunctionExecutorImpl
import repository.PublishedInfoRepository
import repository.WriterDao
import util.DtoMapper

/**
 * Implementation of the {@link writerInfoManager} interface that acts as an intermediary between Java and Kotlin packages.
 */
class PublishedInfoManagerImpl : PublishedInfoManager {

    private val logger = LoggerFactory.getLogger("PublishedInfoManagerImplKt")

    /**
     * Retrieves a list of writerinfos for a given CODE.
     *
     * @param writerCode The CODE for which writerinfo are to be retrieved.
     * @return A list of writerInfo instances representing the retrieved writerInfos.
     * @see WriterInfo
     * @see PublishedInfoJavaManager
     */
    override fun getWriterInfo(writerCode: String): List<WriterInfo> {
        val dbFunctionExecutor = HibernateFunctionExecutorImpl.newInstance();
        val publishedInfoRepository = PublishedInfoRepository(dbFunctionExecutor)
        val writerDao = WriterDao(dbFunctionExecutor)

        val writerInfo = PublishedInfoJavaManagerImpl(publishedInfoRepository, writerDao).getWriterInfo(writerCode)
        return DtoMapper().convertToWriterInfoList(writerInfo)
    }
}