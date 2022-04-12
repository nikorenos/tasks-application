package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.dto.TrelloBoardDto;
import com.crud.tasks.dto.TrelloCardDto;
import com.crud.tasks.dto.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {
    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoardsTest() {
        // Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "list 1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "list 2", true);
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(trelloListDto1);
        trelloListDtoList.add(trelloListDto2);
        TrelloBoardDto[] trelloBoardsDto = new TrelloBoardDto[1];
        trelloBoardsDto[0] = new TrelloBoardDto("1", "board 1", trelloListDtoList);
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(trelloBoardsDto[0]);

        // When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDto);

        // Then
        assertEquals("1", trelloBoardList.get(0).getId());
        assertEquals("board 1", trelloBoardList.get(0).getName());
        assertEquals("list 2", trelloBoardList.get(0).getLists().get(1).getName());
    }

    @Test
    public void mapToBoardsDtoTest() {
        // Given
        TrelloList trelloList1 = new TrelloList("1", "list 1", false);
        TrelloList trelloList2 = new TrelloList("2", "list 2", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        TrelloBoard[] trelloBoards = new TrelloBoard[1];
        trelloBoards[0] = new TrelloBoard("1", "board 1", trelloLists);
        List<TrelloBoard> trelloBoard = new ArrayList<>();
        trelloBoard.add(trelloBoards[0]);

        // When
        List<TrelloBoardDto> trelloBoardList = trelloMapper.mapToBoardsDto(trelloBoard);

        // Then
        assertEquals("1", trelloBoardList.get(0).getId());
        assertEquals("board 1", trelloBoardList.get(0).getName());
        assertEquals("list 2", trelloBoardList.get(0).getLists().get(1).getName());
    }

    @Test
    public void mapToListTest() {
        // Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "list 1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "list 2", true);
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(trelloListDto1);
        trelloListDtoList.add(trelloListDto2);

        // When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtoList);

        // Then
        assertEquals("1", trelloLists.get(0).getId());
        assertEquals("list 1", trelloLists.get(0).getName());
        assertEquals(false, trelloLists.get(0).isClosed());
    }

    @Test
    public void mapToListDtoTest() {
        // Given
        TrelloList trelloList1 = new TrelloList("1", "list 1", false);
        TrelloList trelloList2 = new TrelloList("2", "list 2", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);

        // When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloLists);

        // Then
        assertEquals("2", trelloListDtoList.get(1).getId());
        assertEquals("list 2", trelloListDtoList.get(1).getName());
        assertEquals(true, trelloListDtoList.get(1).isClosed());
    }

    @Test
    public void mapToCardTest() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card 1", "Card 1 description", "top", "1");

        // When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        // Then
        assertEquals("1", trelloCard.getListId());
        assertEquals("Card 1", trelloCard.getName());
        assertEquals("top", trelloCard.getPos());
    }

    @Test
    public void mapToCardDtoTest() {
        // Given
        TrelloCard trelloCard = new TrelloCard("Card 1", "Card 1 description", "top", "1");

        // When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        // Then
        assertEquals("1", trelloCardDto.getListId());
        assertEquals("Card 1", trelloCardDto.getName());
        assertEquals("top", trelloCardDto.getPos());
    }
}
