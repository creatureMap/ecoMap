package ecobridge.EcologyMap.controller;

import ecobridge.EcologyMap.domain.Creature;
import ecobridge.EcologyMap.dto.ChatResponse;
import ecobridge.EcologyMap.repository.CreatureRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.CacheRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatGptController {

    private CreatureRepository creatureRepository;

    @Value("${chatGpt.key}")
    private String key;

    @Autowired
    public ChatGptController(CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }

    @PostMapping("/send/{creatureId}")
    public ResponseEntity send(String message, @PathVariable Long creatureId) {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = UriComponentsBuilder
                .fromUriString("https://api.openai.com/v1/chat/completions")
                .build()
                .encode()
                .toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + key);

        ArrayList<Message> list = new ArrayList<>();

        // creatureId에 따른 정보 검색 후 시스템 메세지 추가
        Creature creature = creatureRepository.findById(creatureId)
                .orElseThrow(() -> new IllegalArgumentException("Creation not found " + creatureId));

        list.add(new Message("system", "You are an assisntant that knows about this creature: " + creature.getCreatureInformation()));
        list.add(new Message("system", "You're explaining to a child, so please use gentle language and introduce yourself as if you were the creature."));
        list.add(new Message("system", "Please respond in Korean."));

        // 사용자의 메세지 추가
        list.add(new Message("user",message));

        Body body = new Body("gpt-3.5-turbo", list);

        RequestEntity<Body> httpEntity = new RequestEntity<>(body, httpHeaders, HttpMethod.POST, uri);

        ResponseEntity<ChatResponse> responseEntity = restTemplate.exchange(httpEntity, ChatResponse.class);

        // ChatGPT 응답을 요약
        ChatResponse chatResponse = responseEntity.getBody();
        String summarizedResponse = summarizeResponse(chatResponse.getChoices());

        return ResponseEntity.ok(summarizedResponse);
    }

    private String summarizeResponse(List<ChatResponse.Choice> choices) {
        StringBuilder summary = new StringBuilder();

        // 선택된 메시지 중에서 처음 3문장을 요약에 포함
        int sentenceCount = 0;
        for (ChatResponse.Choice choice : choices) {
            String content = choice.getMessage().getContent();
            String[] sentences = content.split("\n");

            for (String sentence : sentences) {
                if (sentenceCount < 3) {
                    summary.append(sentence).append("\n");
                    sentenceCount++;
                } else {
                    break;
                }
            }

            if (sentenceCount >= 3) {
                break;
            }
        }

        return summary.toString();
    }

    @AllArgsConstructor
    @Data
    static class Body {
        String model;
        List<Message> messages;
    }

    @AllArgsConstructor
    @Data
    static class Message {
        String role;
        String content;
    }

}