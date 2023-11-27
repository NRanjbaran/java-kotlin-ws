package service;

import model.dto.WriterEventDto;
import model.dto.WriterEventType;
import model.entity.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.WriterDao;

import java.util.List;
import java.util.Optional;

class WriterStreamProcessorImplUnitTest {

    private Writer dummyWriter;

    @BeforeEach
    void init() {
        String code = "code test";
        String description = "description test";
        this.dummyWriter = new Writer();
        this.dummyWriter.setCode(code);
    }

    @Test
    void testProcess_validWriterEventDtoWithAddWriterEventType_verifyMethods() {
        WriterEventDto writerEventDto = new WriterEventDto(this.dummyWriter.getCode(), "", "", "", WriterEventType.ADD, List.of());
        WriterDao writerDao = Mockito.mock(WriterDao.class);
        Mockito.doNothing().when(writerDao).save(Mockito.any(Writer.class));

        new WriterStreamProcessorImpl(writerDao).process(writerEventDto);

        Mockito.verify(writerDao).save(Mockito.any(Writer.class));
    }

    @Test
    void testProcess_validWriterEventDtoWithDeleteWriterEventType_verifyMethods() {
        WriterEventDto writerEventDto = new WriterEventDto(this.dummyWriter.getCode(), "", "", "", WriterEventType.ADD, List.of());
        WriterDao writerDao = Mockito.mock(WriterDao.class);
        Mockito.when(writerDao.findWriter(Mockito.anyString())).thenReturn(Optional.of(dummyWriter));

        new WriterStreamProcessorImpl(writerDao).process(writerEventDto);

        Mockito.verify(writerDao).findWriter(this.dummyWriter.getCode());
        Mockito.verify(writerDao).delete(Mockito.any(Writer.class));
    }

}