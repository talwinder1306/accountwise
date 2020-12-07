package com.account.utils;
import com.account.bean.AccountTransaction;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

public class FileParserTest {
    private String fileName;

    @Before
    public void setup(){
        fileName = "accounts.csv";
    }

    @Test
    public void parseCsvFileShouldReturnValidOutput() {
        List<AccountTransaction> result = FileParser.parseCsvFile(fileName);
        assertNotNull(result);
        assertEquals(result.size(), 460);
    }
}
