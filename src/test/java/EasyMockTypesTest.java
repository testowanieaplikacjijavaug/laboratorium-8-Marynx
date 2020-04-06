import org.easymock.MockType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;


public class EasyMockTypesTest {
    
    @Test
    //restrykcyjnie oczekuje tylko zamockowanych funkcji w danej kolejności, wazne aby byly wszystkie
    public void testMockTypeStrict() {
        ArrayList<Integer> mockList = mock(MockType.STRICT, ArrayList.class);
        expect(mockList.add(10)).andReturn(true);
        expect(mockList.add(20)).andReturn(true);
        expect(mockList.size()).andReturn(2);
        expect(mockList.get(0)).andReturn(10);
        replay(mockList);
        
        mockList.add(10);
        mockList.add(20);
        assertEquals(mockList.size(), 2);
        assertTrue(mockList.get(0) == 10);
        
        verify(mockList);
    }
    
    @Test
    //kolejnosc zamockowanych funkcji nie ma znaczenia, wazne aby byly wszystkie
    public void testMockTypeNice() {
        ArrayList<Integer> mockList = mock(MockType.NICE, ArrayList.class);
        expect(mockList.add(10)).andReturn(true);
        expect(mockList.size()).andReturn(2);
        expect(mockList.get(0)).andReturn(10);
        replay(mockList);
        
        mockList.add(10);
        //nie rzuci wyjatku, dzieki MockType.NICE
        boolean b = mockList.add(30);
        
        assertFalse(b);
        assertEquals(mockList.size(), 2);
        assertTrue(mockList.get(0) == 10);
        verify(mockList);
    }
    
    @Test
    //kolejnosc zamockowanych funkcji nie ma znaczenia, wazne aby byly wszystkie
    public void testDefaultMock() {
        ArrayList<Integer> mockList = mock(ArrayList.class);
        expect(mockList.add(10)).andReturn(true);
        expect(mockList.size()).andReturn(2);
        expect(mockList.get(0)).andReturn(10);
        replay(mockList);
        
        assertTrue(mockList.add(10));
        assertEquals(mockList.size(), 2);
        assertTrue(mockList.get(0) == 10);
        verify(mockList);
    }

}
