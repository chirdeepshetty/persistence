package com.tw.searchHistoryTest;

import com.tw.searchHistory.util.PropertyReader;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ResourceBundle;

public class PropertyReaderTest {

    @Test
    public void fileNameTest(){
        assertEquals(new PropertyReader(null).propertyFileName, "persistence");
    }

    @Test
    @Ignore
    public void shouldAbleToReadFromActualFile(){
        PropertyReader propertyReader = new PropertyReader();
        assertEquals(propertyReader.getCookieName(), "authentication");
        assertEquals(propertyReader.getServerName(), "javaErlangServer");
        assertEquals(propertyReader.getMailBoxName(), "javaErlangMailBox");        
    }

    @Test
    @Ignore
    public void shouldAbleToReadAllTheProperties(){

        Mockery mockRepository = new JUnit4Mockery();
        final ResourceBundle resourceBundle = mockRepository.mock(ResourceBundle.class);
        PropertyReader propertyReader = new PropertyReader(resourceBundle);

        mockRepository.checking(new Expectations(){{
            oneOf(resourceBundle).getString("mailBoxName");
            will(returnValue("blah response"));
        }});
        assertEquals(propertyReader.getMailBoxName(), "blah response");
    }
}
