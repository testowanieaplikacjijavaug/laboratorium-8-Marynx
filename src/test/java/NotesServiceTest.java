import org.easymock.EasyMock;
import org.easymock.MockType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import static org.easymock.EasyMock.*;

public class NotesServiceTest {
    
    private NotesStorage notesStorage;
    private NotesService notesService;
    
    @BeforeEach
    public void setup(){
        notesStorage = createMock(NotesStorage.class);
        notesService = createMock(NotesService.class);
    }
    
    @Test
    public void testAddNote(){
        Note note=Note.of("Test",3.0f);
        ArrayList<Note> expected = new ArrayList<>();
    
        notesService.add(note);
        EasyMock.expectLastCall().andAnswer(() -> {
            expected.add(note);
            return null;
        }).times(1);
        replay(notesService);
    
        notesService.add(note);
    
        assertThat(expected).hasSize(1);
        assertThat(expected.get(0).getName()).isEqualTo("Test");
        assertThat(expected.get(0).getNote()).isEqualTo(3.0f);
    
        verify(notesService);
    }
    
    @Test
    public void testClearStorage(){
        Note note=Note.of("Test",3.0f);
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(note);
        notesStorage.clear();
        
        expectLastCall().andAnswer(() -> {
            notes.clear();
            return null;
        }).times(1);
        replay(notesStorage);
    
        assertThat(notes).hasSize(1);
        notesStorage.clear();
        assertThat(notes).hasSize(0);
        verify(notesStorage);
    }
    
    
    @Test
    public void testAverageOfNull(){
        expect(notesService.averageOf(null)).andThrow(new IllegalArgumentException("Imię ucznia nie może być null"));
        replay(notesService);
        assertThatThrownBy(() -> {
            notesService.averageOf(null);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Imię ucznia nie może być null");
    
        verify(notesService);
    }
    
    @Test
    public void testAverageOfEmpty(){
        expect(notesService.averageOf("")).andThrow(new IllegalArgumentException("Imię ucznia nie może być null"));
        replay(notesService);
        assertThatThrownBy(() -> {
            notesService.averageOf("");
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Imię ucznia nie może być null");
        
        verify(notesService);
    }
    
    @Test
    public void testGetAllNotesOf(){
        List<Note> expected = Arrays.asList(Note.of("Test", 3.0f), Note.of("Test", 4.0f));
        expect(notesStorage.getAllNotesOf("Test")).andReturn(expected);
        replay(notesStorage);
        assertThat(notesStorage.getAllNotesOf("Test")).hasSize(2).containsAll(expected);
        verify(notesStorage);
    }
    
    
    @AfterEach
    public void teardown(){
        notesStorage=null;
        notesService=null;
    }
    
}
