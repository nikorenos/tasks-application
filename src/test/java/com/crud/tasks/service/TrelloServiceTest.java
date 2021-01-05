package com.crud.tasks.service;

import com.crud.tasks.domain.*;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void fetchTrelloBoards() {
        //Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto();
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);

        // When
        List<TrelloBoardDto> receivedTrelloBoardDtoList = trelloService.fetchTrelloBoards();
        // Then
        assertEquals(1, receivedTrelloBoardDtoList.size());
    }

    @Test
    public void createTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto= new TrelloCardDto("Card 1", "Card 1 description", "top", "1");
        AttachmentByTypeDto attachment = new AttachmentByTypeDto();
        BadgesDto badgesDto = new BadgesDto(4, attachment);
        CreatedTrelloCardDto cardDto = new CreatedTrelloCardDto("1","Card 1", "https://test.com", badgesDto);
        Mail mail = new Mail("test@test.com", "testCC@test.com", "Test", "Test Message");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        if (mail.getToCc() != null) {
            mailMessage.setCc((mail.getToCc()));
        }
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(cardDto);

        // When
        simpleEmailService.send(mail);
        CreatedTrelloCardDto createdCard = trelloService.createTrelloCard(trelloCardDto);
        // Then
        Mockito.verify(javaMailSender, times(1)).send(mailMessage);
        assertEquals("1", createdCard.getId());
        assertEquals("Card 1", createdCard.getName());
        assertEquals("https://test.com", createdCard.getShortUrl());
        assertEquals(4, createdCard.getBadges().getVotes());
    }

    @Test
    public void shouldSendEmailWithCC() {
        //Given
        Mail mail = new Mail("test@test.com", "testCC@test.com", "Test", "Test Message");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        if (mail.getToCc() != null) {
            mailMessage.setCc((mail.getToCc()));
        }
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        //When
        simpleEmailService.send(mail);

        //Then
        Mockito.verify(javaMailSender, times(1)).send(mailMessage);
    }
}
