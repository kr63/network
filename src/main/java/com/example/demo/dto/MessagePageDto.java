package com.example.demo.dto;

import com.example.demo.domain.Message;
import com.example.demo.domain.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonView(Views.FullMessage.class)
public class MessagePageDto {

    private List<Message> messages;
    private int currentPage;
    private int totalPages;
}
