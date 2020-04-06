import org.easymock.EasyMock;
import org.easymock.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CarEasyTest {
    private Car myFerrari = EasyMock.createMock(MockType.NICE,Car.class);
    
    @Test
    public void test_instance_car(){
        assertTrue(myFerrari instanceof Car);
    }
    
    @Test
    public void test_default_behavior_needsFuel(){
        assertFalse(myFerrari.needsFuel(), "New test double should return False as boolean");
    }
    
    @Test
    public void test_default_behavior_temperature(){
        assertEquals(0.0, myFerrari.getEngineTemperature(), "New test double should return 0.0");
    }
    
    @Test
    public void test_stubbing_mock(){
        EasyMock.expect(myFerrari.needsFuel()).andReturn(true);
        EasyMock.replay(myFerrari);
        assertTrue(myFerrari.needsFuel());
        
    }
    
    @Test
    public void test_exception(){
        EasyMock.expect(myFerrari.needsFuel()).andThrow(new RuntimeException());
        EasyMock.replay(myFerrari);
        assertThrows(RuntimeException.class, () -> {
            myFerrari.needsFuel();
        });
    }
    
    //Dodane testy
    @Test
    public void testGetModel(){
        EasyMock.expect(myFerrari.getModel()).andReturn("Enzo");
        EasyMock.replay(myFerrari);
        
        assertEquals(myFerrari.getModel(),"Enzo");
        
        EasyMock.verify(myFerrari);
    }
    
    @Test
    public void testGetModelException(){
        EasyMock.expect(myFerrari.getModel()).andThrow(new RuntimeException());
        EasyMock.replay(myFerrari);
        assertThrows(RuntimeException.class, () -> {
            myFerrari.getModel();
        });
    }
    
    @Test
    public void testGetYearModel(){
        EasyMock.expect(myFerrari.getYearModel()).andReturn(2005);
        EasyMock.replay(myFerrari);
    
        assertEquals(myFerrari.getYearModel(),2005);
    
        EasyMock.verify(myFerrari);
    }
    
    
    @Test
    public void testGetYearModelException(){
        EasyMock.expect(myFerrari.getYearModel()).andThrow(new RuntimeException());
        EasyMock.replay(myFerrari);
        assertThrows(RuntimeException.class, () -> {
            myFerrari.getYearModel();
        });
    }
    
    @Test
    public void testDefaultBehaviourGetYearModel(){
        assertEquals(0, myFerrari.getYearModel(), "New test int should return 0");
    }
    
    
}
