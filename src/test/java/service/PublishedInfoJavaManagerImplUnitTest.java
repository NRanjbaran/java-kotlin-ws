package service;

import exception.PublishedInfoManagerException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.PublishedInfoRepository;
import repository.WriterDao;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PublishedInfoJavaManagerImplUnitTest {

    @Test
    void testGetWriterInfos_codeIsNull_returnNull() {
        String code = null;
        PublishedInfoRepository publishedInfoRepository = Mockito.mock(PublishedInfoRepository.class);
        WriterDao writerDao = Mockito.mock(WriterDao.class);

        assertThrows(PublishedInfoManagerException.class,
                () -> new PublishedInfoJavaManagerImpl(publishedInfoRepository, writerDao).getWriterInfo(code));
    }
}