package org.example.chat.controller.dto.pushTmp;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class RequestUnsubscribe {
    String token;
    List<String> topicList;
}
