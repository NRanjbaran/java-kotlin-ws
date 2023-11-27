package service;

import exception.PublishedInfoManagerException;
import model.dto.WriterInfoDto;
import model.entity.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import repository.HibernateFunctionExecutor;
import repository.HibernateFunctionExecutorImpl;
import repository.PublishedInfoRepository;
import repository.WriterDao;
import util.DataBaseUtil;
import util.InstantUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PublishedInfoJavaManagerImplIntegrationTest {

    private DataBaseUtil dataBaseUtil;
    private InstantUtil instantUtil;
    private PublishedInfoRepository publishedInfoRepository;
    private WriterDao writerDao;

    @BeforeEach
    void init() {
        this.dataBaseUtil = new DataBaseUtil();
        this.instantUtil = new InstantUtil();
        this.dataBaseUtil.deleteAllTables();
        HibernateFunctionExecutor hibernateFunctionExecutor = HibernateFunctionExecutorImpl.newInstance();
        this.publishedInfoRepository = new PublishedInfoRepository(hibernateFunctionExecutor);
        this.writerDao = new WriterDao(hibernateFunctionExecutor);
    }

    @Order(1)
    @Test
    void testGetWriterInfos_fiveQuotesPerMinuteWithoutMissedQuotes_validWriterInfoList() throws PublishedInfoManagerException {
        Writer writer = this.dataBaseUtil.createAndSaveDefaultWriter();

        List<WriterInfoDto> writerInfoDtos = new PublishedInfoJavaManagerImpl(this.publishedInfoRepository, this.writerDao).getWriterInfo(writer.getCode());

        assertEquals(30, writerInfoDtos.size());
    }

    @Order(2)
    @Test
    void testGetWriterInfos_missedQuotesInMiddleOfMinutes_validWriterInfoList() throws PublishedInfoManagerException {
        Writer writer = this.dataBaseUtil.createAndSaveDefaultWriter();

        List<WriterInfoDto> writerInfos = new PublishedInfoJavaManagerImpl(this.publishedInfoRepository, this.writerDao).getWriterInfo(writer.getCode());

        assertEquals(30, writerInfos.size());
    }

    // TODO: complete the tests
    @Order(3)
    @Test
    void testGetWriterInfos_missedQuotesInFirstOfMinutesWithPreviousQuoteInThePast_validWriterInfoList() throws PublishedInfoManagerException {

    }
}