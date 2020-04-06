import org.easymock.*;
import static org.easymock.EasyMock.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.*;


public class AssertVerifyTest {
    private Car myFerrari = EasyMock.createMock(MockType.NICE,Car.class);
    
    @Test
    public void testWithoutVerify() {
        expect(myFerrari.getModel()).andReturn("Enzo");
        expect(myFerrari.getYearModel()).andReturn(2005);
        replay(myFerrari);
        
        assertThat(myFerrari.getModel()).isEqualTo("Enzo");
        //bez verify test przechodzi poniewaz nie ma sprawdzania czy wszystkie mockowane funkcje zostaly wywolane
    }
    
    @Test
    public void testVerify() {
        expect(myFerrari.getModel()).andReturn("Enzo");
        expect(myFerrari.getYearModel()).andReturn(2005);
        replay(myFerrari);
    
        assertThat(myFerrari.getModel()).isEqualTo("Enzo");
    
        assertThatThrownBy(() -> {
            verify(myFerrari);
        }).isInstanceOf(AssertionError.class);
        //po dodaniu 'verify' widzimy ze test nie przechodzi i informuje nas ze nie wszystkie zmockowane funkcje zostaly wywolane
    }
    
    
}
