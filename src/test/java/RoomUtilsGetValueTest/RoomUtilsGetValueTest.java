package RoomUtilsGetValueTest;

import model.clues.Clue;
import model.decorations.Decoration;
import model.escape_room.RoomUtils;
import model.rooms.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoomUtilsGetValueTest {

    @Mock
    private Room room;

    @Mock
    private Clue clue1, clue2;

    @Mock
    private Decoration decoration1, decoration2, decoration3;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getValueTest(){
        when(clue1.getValue()).thenReturn(10.0F);
        when(clue2.getValue()).thenReturn(11.0F);

        when(decoration1.getValue()).thenReturn(50.0F);
        when(decoration2.getValue()).thenReturn(65.0F);
        when(decoration3.getValue()).thenReturn(40.0F);

        List<Clue> clues = Arrays.asList(clue1, clue2);
        List<Decoration> decorations = Arrays.asList(decoration1, decoration2, decoration3);

        when(room.getClues()).thenReturn(clues);
        when(room.getDecorations()).thenReturn(decorations);

        float total = RoomUtils.calculateValue(room);

        assertEquals(176.0f, total);
    }




}
