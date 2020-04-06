import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(EasyMockExtension.class)
public class FriendshipsMongoEasyMockTest {
    
    @TestSubject
    FriendshipsMongo friendships = new FriendshipsMongo();
    
    //A nice mock expects recorded calls in any order and returning null for other calls
    @Mock(type = MockType.NICE)
    FriendsCollection friends;
    
    @Test
    public void mockingWorksAsExpected(){
        Person joe = new Person("Joe");
        //Zapisanie zachowania - co sie ma stac
        expect(friends.findByName("Joe")).andReturn(joe);
        //Odpalenie obiektu do sprawdzenia zachowania
        replay(friends);
        assertThat(friends.findByName("Joe")).isEqualTo(joe);
    }
    
    @Test
    public void alexDoesNotHaveFriends(){
        assertThat(friendships.getFriendsList("Alex")).isEmpty();
    }
    
    @Test
    public void joeHas5Friends(){
        List<String> expected = Arrays.asList(new String[]{"Karol","Dawid","Maciej","Tomek","Adam"});
        Person joe = createMock(Person.class);
        expect(friends.findByName("Joe")).andReturn(joe);
        expect(joe.getFriends()).andReturn(expected);
        replay(friends);
        replay(joe);
        assertThat(friendships.getFriendsList("Joe")).hasSize(5).containsOnly("Karol","Dawid","Maciej","Tomek","Adam");
    }
    
    //Dodane testy
    @Test
    public void joeIsFriendWithAlex(){
        Person joe=createMock(Person.class);
        expect(friends.findByName("Joe")).andReturn(joe);
        expect(joe.getFriends()).andReturn(Arrays.asList("Alex"));
        replay(joe);
        replay(friends);
        
        assertThat(friendships.areFriends("Joe","Alex")).isTrue();
    }
    
    @Test
    public void joeIsNotFriendWithAlex(){
        Person joe=createMock(Person.class);
        expect(friends.findByName("Joe")).andReturn(joe);
        expect(joe.getFriends()).andReturn(new ArrayList<>());
        replay(joe);
        replay(friends);
        
        assertThat(friendships.areFriends("Joe","Alex")).isFalse();
    }
    
    @Test
    public void getAlexName(){
        Person alex=createMock(Person.class);
        expect(alex.getName()).andReturn("Alex");
        replay(alex);
        
        assertThat(alex.getName()).isEqualTo("Alex");
    }
    
    @Test
    public void setNullPersonName(){
        Person alex=createMock(Person.class);
        alex.setName(null);
        expectLastCall().andThrow(new NullPointerException());
    }
    
    @Test
    public void addNullFriend(){
        Person alex=createMock(Person.class);
        alex.addFriend(null);
        expectLastCall().andThrow(new NullPointerException());
    }
    
    
}